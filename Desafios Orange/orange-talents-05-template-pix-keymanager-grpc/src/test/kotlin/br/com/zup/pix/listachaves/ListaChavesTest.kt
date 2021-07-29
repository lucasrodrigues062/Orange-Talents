package br.com.zup.pix.listachaves

import br.com.zup.pix.KeyManagerListaGrpServiceGrpc
import br.com.zup.pix.ListaChaveRequest
import br.com.zup.pix.chavepix.TipoDeChave
import br.com.zup.pix.chavepix.TipoDeConta
import br.com.zup.pix.chavepix.entidades.ChavePix
import br.com.zup.pix.chavepix.entidades.DadosBancarios
import br.com.zup.pix.chavepix.entidades.repositorios.ChavePixRepository
import io.grpc.ManagedChannel
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import io.micronaut.grpc.server.GrpcServerChannel
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsInAnyOrder
import org.hamcrest.Matchers.hasSize
import org.junit.After
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

@MicronautTest(transactional = false)
class ListaChavesTest(
    val chavePixRepository: ChavePixRepository,
    val grpcClient: KeyManagerListaGrpServiceGrpc.KeyManagerListaGrpServiceBlockingStub
) {

    companion object{
        val CLIENT_ID = UUID.randomUUID()
        val CHAVE_ALEATORIA = UUID.randomUUID()
        val CHAVE_EMAIL = "teste@email.com"
        val CHAVE_CPF = "26951023050"
    }

    @BeforeEach
    fun setup(){
        chavePixRepository.save(chave(TipoDeChave.ALEATORIA, chave = CHAVE_ALEATORIA.toString(), CLIENT_ID))
        chavePixRepository.save(chave(TipoDeChave.EMAIL, chave = CHAVE_EMAIL, CLIENT_ID))
        chavePixRepository.save(chave(TipoDeChave.CPF, chave = CHAVE_CPF, CLIENT_ID))
    }
    @AfterEach
    fun clean(){
        chavePixRepository.deleteAll()
    }

    @Test
    fun `deve listar todas as chaves do cliente`(){
        val response = grpcClient.lista(ListaChaveRequest.newBuilder()
            .setClientId(CLIENT_ID.toString())
            .build())

        with(response.chavesList){
            assertThat(this, hasSize(3))
            assertThat(
                this.map { Pair(it.tipo, it.chave) }.toList(),
                containsInAnyOrder(
                    Pair(br.com.zup.pix.TipoDeChave.ALEATORIA, CHAVE_ALEATORIA.toString()),
                    Pair(br.com.zup.pix.TipoDeChave.EMAIL, CHAVE_EMAIL),
                    Pair(br.com.zup.pix.TipoDeChave.CPF, CHAVE_CPF)
                )
            )
        }


    }

    @Test
    fun `nao deve gerar chave de cliente inexistente`(){
        val response = grpcClient.lista(ListaChaveRequest.newBuilder()
            .setClientId(UUID.randomUUID().toString())
            .build())

        assertEquals(0,response.chavesCount)
    }

    @Test
    fun `nao deve listar chaves de requisicao invalida`(){
        val response = assertThrows<StatusRuntimeException> {
            grpcClient.lista(ListaChaveRequest.newBuilder()
                .setClientId("")
                .build())
        }
        with(response){
            assertEquals(Status.INVALID_ARGUMENT.code, status.code)
            assertEquals("O clientId não pode ser nulo ou em branco", status.description)
        }
    }

    @Factory
    class Clientes {
        @Bean
        fun blockingStub(@GrpcChannel(GrpcServerChannel.NAME) channel: ManagedChannel): KeyManagerListaGrpServiceGrpc.KeyManagerListaGrpServiceBlockingStub?{
            return KeyManagerListaGrpServiceGrpc.newBlockingStub(channel)
        }
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
}