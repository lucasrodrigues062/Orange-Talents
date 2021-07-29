package br.com.zup.pix.cadastrachave

import br.com.zup.pix.ChavePixRequest
import br.com.zup.pix.KeyManagerCadastraGrpcServiceGrpc
import br.com.zup.pix.TipoDeChave
import br.com.zup.pix.cadastrachavepix.ConsultaCliente
import br.com.zup.pix.cadastrachavepix.ConsultaContaResponse
import br.com.zup.pix.cadastrachavepix.Instituicao
import br.com.zup.pix.cadastrachavepix.Titular
import br.com.zup.pix.chavepix.TipoDeConta
import br.com.zup.pix.compartilhado.violations
import br.com.zup.pix.chavepix.entidades.ChavePix
import br.com.zup.pix.chavepix.entidades.DadosBancarios
import br.com.zup.pix.chavepix.entidades.repositorios.ChavePixRepository
import br.com.zup.pix.chavepix.services.*

import io.grpc.ManagedChannel
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import io.micronaut.grpc.server.GrpcServerChannel
import io.micronaut.http.HttpResponse
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.hamcrest.MatcherAssert.assertThat

import org.hamcrest.Matchers.containsInAnyOrder

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import java.time.LocalDateTime
import java.util.*
import javax.inject.Inject

@MicronautTest(transactional = false)
internal class CadastraChavePixTest(
    val chavePixRepository: ChavePixRepository,
    val grpcClient: KeyManagerCadastraGrpcServiceGrpc.KeyManagerCadastraGrpcServiceBlockingStub

) {

    @Inject
    lateinit var clientBCB: ClientBCB

    @Inject
    lateinit var consultaCliente: ConsultaCliente

    companion object {
      val CLIENT_ID = UUID.randomUUID()
    }


    @BeforeEach
    fun setup() {
        chavePixRepository.deleteAll()
    }

    @Test
    fun `deve cadastrar uma chave pix - Email`() {
        `when`(consultaCliente.consultaConta(idCliente = CLIENT_ID.toString(), tipo = "CONTA_CORRENTE"))
            .thenReturn(HttpResponse.ok(itauResponse()))

        `when`(clientBCB.cadastra(criaBcbCadastraRequest(BcbTipodeChave.EMAIL, chave = "teste@email.com.br")))
            .thenReturn(HttpResponse.created(criaBcbCadastraResponse(BcbTipodeChave.EMAIL,  chave = "teste@email.com.br")))

        val response = grpcClient.cadastraChave(ChavePixRequest.newBuilder()
            .setCodigoInterno(CLIENT_ID.toString())
            .setTipoDeChave(TipoDeChave.EMAIL)
            .setValorDaChave("teste@email.com.br")
            .setTipoDeConta(br.com.zup.pix.TipoDeConta.CONTA_CORRENTE)
            .build())

        with(response) {
            assertEquals(CLIENT_ID.toString(), clientId)
            assertNotNull(chaveId)
        }
    }

    @Test
    fun `deve cadastrar uma chave pix - CPF`() {
        `when`(consultaCliente.consultaConta(idCliente = CLIENT_ID.toString(), tipo = "CONTA_CORRENTE"))
            .thenReturn(HttpResponse.ok(itauResponse()))

        `when`(clientBCB.cadastra(criaBcbCadastraRequest(BcbTipodeChave.CPF,  chave = "26951023050")))
            .thenReturn(HttpResponse.created(criaBcbCadastraResponse(BcbTipodeChave.CPF,  chave = "26951023050")))

        val response = grpcClient.cadastraChave(ChavePixRequest.newBuilder()
            .setCodigoInterno(CLIENT_ID.toString())
            .setTipoDeChave(TipoDeChave.CPF)
            .setValorDaChave("26951023050")
            .setTipoDeConta(br.com.zup.pix.TipoDeConta.CONTA_CORRENTE)
            .build())

        with(response) {
            assertEquals(CLIENT_ID.toString(), clientId)
            assertNotNull(chaveId)
        }
    }
    @Test
    fun `deve cadastrar uma chave pix - TELEFONE`() {
        `when`(consultaCliente.consultaConta(idCliente = CLIENT_ID.toString(), tipo = "CONTA_CORRENTE"))
            .thenReturn(HttpResponse.ok(itauResponse()))


        `when`(clientBCB.cadastra(criaBcbCadastraRequest(BcbTipodeChave.PHONE, chave = "+5584998506034" )))
            .thenReturn(HttpResponse.created(criaBcbCadastraResponse(BcbTipodeChave.PHONE, chave = "+5584998506034")))

        val response = grpcClient.cadastraChave(ChavePixRequest.newBuilder()
            .setCodigoInterno(CLIENT_ID.toString())
            .setTipoDeChave(TipoDeChave.TELEFONE)
            .setValorDaChave("+5584998506034")
            .setTipoDeConta(br.com.zup.pix.TipoDeConta.CONTA_CORRENTE)
            .build())

        with(response) {
            assertEquals(CLIENT_ID.toString(), clientId)
            assertNotNull(chaveId)
        }
    }
    @Test
    fun `deve cadastrar uma chave pix - ALEATORIA`() {
        `when`(consultaCliente.consultaConta(idCliente = CLIENT_ID.toString(), tipo = "CONTA_CORRENTE"))
            .thenReturn(HttpResponse.ok(itauResponse()))
        var chaveAleatoria = UUID.randomUUID().toString()
        println(criaBcbCadastraRequest(BcbTipodeChave.RANDOM, chave = chaveAleatoria))
        `when`(clientBCB.cadastra(criaBcbCadastraRequest(BcbTipodeChave.RANDOM, chave = "")))
            .thenReturn(HttpResponse.created(criaBcbCadastraResponse(BcbTipodeChave.RANDOM, chave = chaveAleatoria)))

        val response = grpcClient.cadastraChave(ChavePixRequest.newBuilder()
            .setCodigoInterno(CLIENT_ID.toString())
            .setTipoDeChave(TipoDeChave.ALEATORIA)
            .setTipoDeConta(br.com.zup.pix.TipoDeConta.CONTA_CORRENTE)
            .build())

        with(response) {
            assertEquals(CLIENT_ID.toString(), clientId)
            assertNotNull(chaveId)
        }
    }
    @Test
    fun `nao deve gerar chave duplicada - CPF`(){
        chavePixRepository.save(
            chave(
            tipoDeChave = br.com.zup.pix.chavepix.TipoDeChave.CPF,chave = "26951023050", clientId = CLIENT_ID
        ))

        val erro = assertThrows<StatusRuntimeException> {
            grpcClient.cadastraChave(ChavePixRequest.newBuilder()
                .setCodigoInterno(CLIENT_ID.toString())
                .setTipoDeChave(TipoDeChave.CPF)
                .setValorDaChave("26951023050")
                .setTipoDeConta(br.com.zup.pix.TipoDeConta.CONTA_CORRENTE)
                .build())
        }
        with(erro) {
            assertEquals(Status.ALREADY_EXISTS.code, status.code)
            assertEquals("Chave 26951023050 ja existe na base de dados", status.description)
        }
    }

    @Test
    fun `nao deve gerar chave quando cliente nao for encontrado`(){
        `when`(consultaCliente.consultaConta(idCliente = CLIENT_ID.toString(), tipo = "CONTA_CORRENTE"))
            .thenReturn(HttpResponse.notFound())

        val erro = assertThrows<StatusRuntimeException> {
            grpcClient.cadastraChave(ChavePixRequest.newBuilder()
                .setCodigoInterno(CLIENT_ID.toString())
                .setTipoDeChave(TipoDeChave.EMAIL)
                .setValorDaChave("teste@teste.com")
                .setTipoDeConta(br.com.zup.pix.TipoDeConta.CONTA_CORRENTE)
                .build())
        }

        with(erro) {
            assertEquals(Status.FAILED_PRECONDITION.code, status.code)
            assertEquals("Cliente nao encontrado no banco de dados", status.description)
        }
    }
    @Test
    fun `nao deve gerar chave com requisicao vazia`() {
        val erro = assertThrows<StatusRuntimeException> {
            grpcClient.cadastraChave(ChavePixRequest.newBuilder().build())
        }

        with(erro) {
            assertEquals(Status.INVALID_ARGUMENT.code, status.code)
            assertEquals("Dados inválidos", status.description)
            assertThat( violations(), containsInAnyOrder(
                Pair("codigoInterno", "UUID não possui um formato válido"),
                Pair("codigoInterno", "não deve estar em branco"),
                Pair("tipoDeConta", "não deve ser nulo"),
                Pair("tipoDeChave", "não deve ser nulo"),
            ))
        }
    }
    @Test
    fun `nao deve gerar chave com dados invalidos - CPF`(){

        val erro = assertThrows<StatusRuntimeException> {
            grpcClient.cadastraChave(ChavePixRequest.newBuilder()
                .setCodigoInterno(CLIENT_ID.toString())
                .setTipoDeChave(TipoDeChave.CPF)
                .setValorDaChave("60794595072")
                .setTipoDeConta(br.com.zup.pix.TipoDeConta.CONTA_POUPANCA)
                .build())
        }

        // validação
        with(erro) {
            assertEquals(Status.INVALID_ARGUMENT.code, status.code)
            assertEquals("Dados inválidos", status.description)
            assertThat(violations(), containsInAnyOrder(
                Pair("chave", "Chave tipo Inválida (CPF : validatedValue.valorDaChave )"),
            ))
        }
    }
    @Test
    fun `nao deve gerar chave com dados invalidos - TELEFONE`(){

        val erro = assertThrows<StatusRuntimeException> {
            grpcClient.cadastraChave(ChavePixRequest.newBuilder()
                .setCodigoInterno(CLIENT_ID.toString())
                .setTipoDeChave(TipoDeChave.TELEFONE)
                .setValorDaChave("84998506287")
                .setTipoDeConta(br.com.zup.pix.TipoDeConta.CONTA_POUPANCA)
                .build())
        }

        // validação
        with(erro) {
            assertEquals(Status.INVALID_ARGUMENT.code, status.code)
            assertEquals("Dados inválidos", status.description)
            assertThat(violations(), containsInAnyOrder(
                Pair("chave", "Chave tipo Inválida (TELEFONE : validatedValue.valorDaChave )"),
            ))
        }
    }
    @Test
    fun `nao deve gerar chave com dados invalidos - EMAIL`(){

        val erro = assertThrows<StatusRuntimeException> {
            grpcClient.cadastraChave(ChavePixRequest.newBuilder()
                .setCodigoInterno(CLIENT_ID.toString())
                .setTipoDeChave(TipoDeChave.EMAIL)
                .setValorDaChave("zup.com.br")
                .setTipoDeConta(br.com.zup.pix.TipoDeConta.CONTA_POUPANCA)
                .build())
        }


        with(erro) {
            assertEquals(Status.INVALID_ARGUMENT.code, status.code)
            assertEquals("Dados inválidos", status.description)
            assertThat(violations(), containsInAnyOrder(
                Pair("chave", "Chave tipo Inválida (EMAIL : validatedValue.valorDaChave )"),
            ))
        }
    }
    @Test
    fun `nao deve gerar chave com dados invalidos - ALEATORIA`(){

        val erro = assertThrows<StatusRuntimeException> {
            grpcClient.cadastraChave(ChavePixRequest.newBuilder()
                .setCodigoInterno(CLIENT_ID.toString())
                .setTipoDeChave(TipoDeChave.ALEATORIA)
                .setValorDaChave("zup@zup.com.br")
                .setTipoDeConta(br.com.zup.pix.TipoDeConta.CONTA_POUPANCA)
                .build())
        }


        with(erro) {
            assertEquals(Status.INVALID_ARGUMENT.code, status.code)
            assertEquals("Dados inválidos", status.description)
            assertThat(violations(), containsInAnyOrder(
                Pair("chave", "Chave tipo Inválida (ALEATORIA : validatedValue.valorDaChave )"),
            ))
        }
    }

    @Test
    fun `nao deve gerar chave quando nao conseguir comunicar com o banco central`(){
        `when`(consultaCliente.consultaConta(idCliente = CLIENT_ID.toString(), tipo = "CONTA_CORRENTE"))
            .thenReturn(HttpResponse.ok(itauResponse()))
        `when`(clientBCB.cadastra(criaBcbCadastraRequest(BcbTipodeChave.EMAIL, chave = "teste@email.com.br")))
            .thenReturn(HttpResponse.badRequest())

        val erro = assertThrows<StatusRuntimeException> {
            grpcClient.cadastraChave(ChavePixRequest.newBuilder()
                .setCodigoInterno(CLIENT_ID.toString())
                .setTipoDeChave(TipoDeChave.EMAIL)
                .setValorDaChave("teste@email.com.br")
                .setTipoDeConta(br.com.zup.pix.TipoDeConta.CONTA_CORRENTE)
                .build())
        }
        with(erro){
            assertEquals(Status.FAILED_PRECONDITION.code, status.code)
            assertEquals("Não foi possível cadastrar chave no Banco Central", status.description)
        }
    }


    @MockBean(ConsultaCliente::class)
    fun consultaClienteItau(): ConsultaCliente? {
        return Mockito.mock(ConsultaCliente::class.java)
    }

    @MockBean(ClientBCB::class)
    fun clientBCB(): ClientBCB? {
        return Mockito.mock(ClientBCB::class.java)
    }

    @Factory
    class Clients {
    @Bean
        fun blockingStub(@GrpcChannel(GrpcServerChannel.NAME) channel: ManagedChannel): KeyManagerCadastraGrpcServiceGrpc.KeyManagerCadastraGrpcServiceBlockingStub? {
            return KeyManagerCadastraGrpcServiceGrpc.newBlockingStub(channel)

        }
    }


    private fun itauResponse (): ConsultaContaResponse {
        return ConsultaContaResponse(
        tipo = "CONTA_CORRENTE",
        instituicao = Instituicao(nome = "ITAU", ispb = DadosBancarios.ITAU_ISPB ),
        agencia = "1234",
        numero = "654321",
        titular = Titular("Zup Edu", "26951023050")
        )
    }

    private fun criaBcbCadastraRequest(tipodeChave: BcbTipodeChave, chave: String): BcbCadastraChaveRequest{
        return BcbCadastraChaveRequest(
            keyType = tipodeChave,
            key = chave,
            bankAccount = criaBcbContaBancaria(),
            owner = criaBcbProprietario()
        )
    }
    private fun criaBcbCadastraResponse(tipodeChave: BcbTipodeChave, chave: String): BcbCadastraChaveResponse{
        return BcbCadastraChaveResponse(
            keyType = tipodeChave,
            key = chave,
            bankAccount = criaBcbContaBancaria(),
            owner = criaBcbProprietario(),
            createdAt = LocalDateTime.now()

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

    private fun chave(
        tipoDeChave: br.com.zup.pix.chavepix.TipoDeChave,
        chave: String = UUID.randomUUID().toString(),
        clientId: UUID = UUID.randomUUID()
    ): ChavePix{
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
                numero = "654321"

            )
        )
    }
}