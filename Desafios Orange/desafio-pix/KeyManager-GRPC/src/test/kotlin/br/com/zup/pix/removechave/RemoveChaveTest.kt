package br.com.zup.pix.removechave

import br.com.zup.pix.KeyManagerRemoveGrpcServiceGrpc
import br.com.zup.pix.RemoveChaveRequest
import br.com.zup.pix.chavepix.TipoDeChave
import br.com.zup.pix.chavepix.TipoDeConta
import br.com.zup.pix.chavepix.entidades.ChavePix
import br.com.zup.pix.chavepix.entidades.DadosBancarios
import br.com.zup.pix.chavepix.entidades.repositorios.ChavePixRepository
import br.com.zup.pix.chavepix.services.BcbExcluiChaveRequest
import br.com.zup.pix.chavepix.services.BcbExcluiChaveResponse
import br.com.zup.pix.chavepix.services.ClientBCB
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
internal class RemoveChaveTest (
    val chavePixRepository: ChavePixRepository,
    val grpcClient: KeyManagerRemoveGrpcServiceGrpc.KeyManagerRemoveGrpcServiceBlockingStub
    ){

    lateinit var CHAVE_PIX: ChavePix

    @Inject
    lateinit var clientBCB: ClientBCB

    @BeforeEach
    fun setup(){
        CHAVE_PIX = chavePixRepository.save(chave(
            tipoDeChave = TipoDeChave.EMAIL,
            chave = "teste@email.com.br",
            clientId = UUID.randomUUID()
        ))
    }
    @AfterEach
    fun limpeza() {
        chavePixRepository.deleteAll()
    }



    @Test
    fun `deve remover uma chave`() {

        `when`(clientBCB.exclui(key = "teste@email.com.br", BcbExcluiChaveRequest("teste@email.com.br")))
            .thenReturn(HttpResponse.ok(BcbExcluiChaveResponse(key = "teste@email.com", participant = DadosBancarios.ITAU_ISPB, deletedAt = LocalDateTime.now())))

        val response = grpcClient.removeChave(RemoveChaveRequest.newBuilder()
            .setChaveId(CHAVE_PIX.id.toString())
            .setClientId(CHAVE_PIX.codigoCliente.toString())
            .build())

        with(response){
            assertEquals("Chave excluida com sucesso", responseMessage )
        }
    }

    @Test
    fun `nao deve excluir chave caso nao comunique com o banco central`(){
        `when`(clientBCB.exclui(key = "teste@email.com.br", BcbExcluiChaveRequest("teste@email.com.br")))
            .thenReturn(HttpResponse.unprocessableEntity())

        val erro = assertThrows<StatusRuntimeException> {
            grpcClient.removeChave(RemoveChaveRequest.newBuilder()
                .setChaveId(CHAVE_PIX.id.toString())
                .setClientId(CHAVE_PIX.codigoCliente.toString())
                .build())
        }

        with(erro){
            assertEquals(Status.FAILED_PRECONDITION.code, status.code)
            assertEquals("Falha ao excluir Chave no Banco Central", status.description)
        }
    }

    @Test
    fun `nao deve remover quando chave for inexistente`(){
        val uuid = UUID.randomUUID().toString()

        val erro = assertThrows<StatusRuntimeException> {
            grpcClient.removeChave(RemoveChaveRequest.newBuilder()
                .setChaveId(uuid)
                .setClientId(CHAVE_PIX.codigoCliente.toString())
                .build())
        }

        with(erro){
            assertEquals(Status.NOT_FOUND.code, status.code)
            assertEquals("Chave nao encontrada", status.description)
        }
    }

    @Test
    fun `nao deve remover chave pertencente a outro cliente`(){
        val uuid = UUID.randomUUID().toString()

        val erro = assertThrows<StatusRuntimeException> {
            grpcClient.removeChave(RemoveChaveRequest.newBuilder()
                .setChaveId(CHAVE_PIX.id.toString())
                .setClientId(uuid.toString())
                .build())
        }

        with(erro){
            assertEquals(Status.NOT_FOUND.code, status.code)
            assertEquals("Chave nao encontrada", status.description)
        }
    }

    @Test
    fun `nao deve remover chave com request vazia`() {
        val erro = assertThrows<StatusRuntimeException> {
            grpcClient.removeChave(RemoveChaveRequest.newBuilder().build())
        }

        with(erro) {
            assertEquals(Status.INVALID_ARGUMENT.code, status.code)
            assertEquals("Dados inválidos", status.description)
            assertThat(violations(), containsInAnyOrder(
                Pair("chaveId", "não deve estar em branco"),
                Pair("clientId", "não deve estar em branco"),
                Pair("chaveId", "UUID não possui um formato válido"),
                Pair("clientId", "UUID não possui um formato válido"),
            ))
        }
    }

    @MockBean(ClientBCB::class)
    fun clientBcb(): ClientBCB?{
        return mock(ClientBCB::class.java)
    }

    @Factory
    class Clients {
        @Bean
        fun clientGrpc(@GrpcChannel(GrpcServerChannel.NAME) channel: ManagedChannel): KeyManagerRemoveGrpcServiceGrpc.KeyManagerRemoveGrpcServiceBlockingStub? {
            return KeyManagerRemoveGrpcServiceGrpc.newBlockingStub(channel)
        }
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
                numero = "123456"

            )
        )
    }
}