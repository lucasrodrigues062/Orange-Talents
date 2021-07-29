package br.com.zup.pix.consulta

import br.com.zup.pix.ConsultaChaveRequest
import br.com.zup.pix.KeyManagerConsultaGrpcServiceGrpc
import br.com.zup.pix.chavepix.TipoDeChave
import br.com.zup.pix.chavepix.TipoDeConta
import br.com.zup.pix.chavepix.entidades.ChavePix
import br.com.zup.pix.chavepix.entidades.DadosBancarios
import br.com.zup.pix.chavepix.entidades.repositorios.ChavePixRepository
import br.com.zup.pix.chavepix.services.*
import br.com.zup.pix.compartilhado.violations
import io.grpc.ManagedChannel
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.grpc.stub.AbstractBlockingStub
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import io.micronaut.grpc.server.GrpcServerChannel
import io.micronaut.http.HttpResponse
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsInAnyOrder
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

import java.time.LocalDateTime
import java.util.*
import javax.inject.Inject

@MicronautTest(transactional = false)
class ConsultaChavePixTest(
    val chavePixRepository: ChavePixRepository,
    val grpcClient: KeyManagerConsultaGrpcServiceGrpc.KeyManagerConsultaGrpcServiceBlockingStub
) {

    @Inject
    lateinit var clientBCB: ClientBCB
    companion object{
        val CLIENTE_ID = UUID.randomUUID()
        val CHAVE_ALEATORIA = UUID.randomUUID()
    }

    @BeforeEach
    fun setup(){
        chavePixRepository.save( chave(tipoDeChave = TipoDeChave.EMAIL, chave = "teste@email.com", clientId = CLIENTE_ID))

    }

    @AfterEach
    fun clean() {
        chavePixRepository.deleteAll()
    }

    @Test
    fun `deve consultar chave por pixId e clientId`(){
        val chavepix = chavePixRepository.findByChave("teste@email.com").get()

        val response = grpcClient.consultaChave(ConsultaChaveRequest.newBuilder()
            .setPixId(ConsultaChaveRequest.ConsultaPorId.newBuilder()
                .setPixId(chavepix.id.toString())
                .setClientId(chavepix.codigoCliente.toString())
                .build())
            .build())

        with(response){
            assertEquals(chavepix.id.toString(), this.pixId)
            assertEquals(chavepix.codigoCliente.toString(), this.clientId)
            assertEquals(chavepix.tipoDeChave.name, this.chavePix.tipo.name)
            assertEquals(chavepix.chave, this.chavePix.chave)
        }
    }

    @Test
    fun `nao deve carregar chave com request vazia`() {

        val response = assertThrows<StatusRuntimeException> {
            grpcClient.consultaChave(ConsultaChaveRequest.newBuilder()
                .setPixId(ConsultaChaveRequest.ConsultaPorId.newBuilder()
                    .setPixId("")
                    .setClientId("")
                    .build())
                .build())
        }

        with(response){
            assertEquals(Status.INVALID_ARGUMENT.code, status.code)
            assertEquals("Dados inválidos", status.description)
            assertThat(violations(), containsInAnyOrder(
                Pair("pixId", "não deve estar em branco"),
                Pair("clientId", "não deve estar em branco"),
                Pair("pixId", "UUID não possui um formato válido"),
                Pair("clientId", "UUID não possui um formato válido")
            ))
        }
    }

    @Test
    fun `nao deve carregar chave, quando o cliente nao existir`(){
        val pixId = UUID.randomUUID().toString()
        val clientId = UUID.randomUUID().toString()

        val response = assertThrows<StatusRuntimeException> {
            grpcClient.consultaChave(ConsultaChaveRequest.newBuilder()
                .setPixId(ConsultaChaveRequest.ConsultaPorId.newBuilder()
                    .setPixId(pixId)
                    .setClientId(clientId)
                    .build())
                .build())
        }
        with(response){
            assertEquals(Status.NOT_FOUND.code, status.code)
            assertEquals("Chave Pix não encontrada ou não pertence a esse cliente", status.description)
        }
    }

    @Test
    fun `deve carregar chave, quando chave existir na base de dados`(){
        val chavePix = chavePixRepository.findByChave("teste@email.com").get()

        val response = grpcClient.consultaChave(ConsultaChaveRequest.newBuilder()
            .setChave("teste@email.com")
            .build())
        with(response){
            assertEquals(chavePix.id.toString(), this.pixId)
            assertEquals(chavePix.codigoCliente.toString(), this.clientId)
            assertEquals(chavePix.tipoDeChave.name, this.chavePix.tipo.name)
            assertEquals(chavePix.chave, this.chavePix.chave)
        }

    }

    @Test
    fun `deve carregar chave, quando nao existir na base, mas existe no BCB`(){
       val reponseBcb = criaBcbChaveDetalhesResponse(BcbTipodeChave.EMAIL, "ze.da.farinha@zup.com.br")
       `when`(clientBCB.buscaPorChave(key = "ze.da.farinha@zup.com.br"))
           .thenReturn(HttpResponse.ok(reponseBcb))

        val response = grpcClient.consultaChave(ConsultaChaveRequest.newBuilder()
            .setChave("ze.da.farinha@zup.com.br")
            .build()
        )
        with(response){
            assertEquals("null", this.pixId)
            assertEquals("null", this.clientId)
            assertEquals(reponseBcb.keyType.name, this.chavePix.tipo.name)
            assertEquals(reponseBcb.key, this.chavePix.chave)
        }
    }

    @Test
    fun `nao deve trazer chave, quando nao existir nem local nem no BCB`() {
        `when`(clientBCB.buscaPorChave(key = "joao.da.cebola@zup.com.br"))
            .thenReturn(HttpResponse.notFound())

        val response = assertThrows<StatusRuntimeException> {
            grpcClient.consultaChave(ConsultaChaveRequest.newBuilder()
                .setChave("joao.da.cebola@zup.com.br")
                .build())
        }
        with(response) {
            assertEquals(Status.NOT_FOUND.code, status.code)
            assertEquals("Chave não encontrada no BCB", status.description)
        }
    }

    @Test
    fun `nao deve trazer chave, com valor da chave vazia`(){
        val response = assertThrows<StatusRuntimeException> {
            grpcClient.consultaChave(ConsultaChaveRequest.newBuilder()
                .setChave("")
                .build())
        }
        with(response) {
            assertEquals(Status.INVALID_ARGUMENT.code, status.code)
            assertEquals("Dados inválidos", status.description)
            assertThat(violations(), containsInAnyOrder(
                Pair("chave","não deve estar em branco")
            ))
        }
    }

    @Test
    fun `nao deve consultar, com request vazia`(){
        val response = assertThrows<StatusRuntimeException> {
            grpcClient.consultaChave(ConsultaChaveRequest.newBuilder().build())
        }

        with(response){
            assertEquals(Status.INVALID_ARGUMENT.code, status.code)
            assertEquals("Chave Pix Invalida ou Nao Informada!", status.description)
        }
    }

    @Factory
    class Clients {
        @Bean
        fun blockingStub(@GrpcChannel(GrpcServerChannel.NAME) channel: ManagedChannel): KeyManagerConsultaGrpcServiceGrpc.KeyManagerConsultaGrpcServiceBlockingStub{
            return KeyManagerConsultaGrpcServiceGrpc.newBlockingStub(channel)
        }
    }

    @MockBean(ClientBCB::class)
    fun clientBcb(): ClientBCB{
        return mock(ClientBCB::class.java)
    }

    private fun chave(
        tipoDeChave: br.com.zup.pix.chavepix.TipoDeChave,
        chave: String = UUID.randomUUID().toString(),
        clientId: UUID = UUID.randomUUID()
    ): ChavePix {
        return ChavePix(
            codigoCliente = clientId,
            tipoDeChave = tipoDeChave,
            chave = chave,
            tipoDeConta = TipoDeConta.CONTA_CORRENTE,
            conta = DadosBancarios(
                instituicao = "ITAU",
                titular = "Zup Edu",
                cpf = "26951023050",
                agencia = "1234",
                numero = "123456"

            )
        )
    }
    private fun criaBcbProprietario(): BcbProprietario {
        return BcbProprietario(
            type = BcbProprietario.BcbTipoProprietario.NATURAL_PERSON,
            name = "Zup Edu",
            taxIdNumber = "26951023050"

        )
    }

    private fun criaBcbContaBancaria(): BcbContaBancaria {
        return BcbContaBancaria(
            participant = DadosBancarios.ITAU_ISPB,
            branch = "1234",
            accountNumber = "654321",
            accountType = BcbTipoDeConta.CACC
        )


    }

    private fun criaBcbChaveDetalhesResponse(tipodeChave: BcbTipodeChave, chave: String): BcbChaveDetalhesResponse {
        return BcbChaveDetalhesResponse(
            keyType = tipodeChave,
            key = chave,
            bankAccount = criaBcbContaBancaria(),
            owner = criaBcbProprietario(),
            createdAt = LocalDateTime.now()

        )
    }
}