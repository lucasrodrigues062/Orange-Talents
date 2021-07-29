package br.com.zup.pix.lista

import br.com.zup.grpc.KeyManagerGrpcFactory
import br.com.zup.pix.*
import br.com.zup.pix.ListaChaveResponse
import com.google.protobuf.Timestamp
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Replaces
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@MicronautTest
class ListaChaveControllerTest {

    @field:Inject
    lateinit var listaChaveClient: KeyManagerListaGrpServiceGrpc.KeyManagerListaGrpServiceBlockingStub

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Test
    fun `deve retornar uma lista de chaves`(){
        val clientId = UUID.randomUUID().toString()
        val respostaGrpc = geraListaChaveResponse(clientId)

        given(listaChaveClient.lista(Mockito.any())).willReturn(respostaGrpc)

        val request = HttpRequest.GET<Any>("/api/clientes/$clientId/pix")

        val response = client.toBlocking().exchange(request, List::class.java)

        assertEquals(HttpStatus.OK,response.status)
        assertNotNull(response.body())
        assertEquals(response.body().size,4)

    }

    private fun geraListaChaveResponse(clientId: String) : ListaChaveResponse {
        val email = ListaChaveResponse.ChavePix.newBuilder()
            .setPixId(UUID.randomUUID().toString())
            .setTipo(TipoDeChave.EMAIL)
            .setChave("teste@teste.com.br")
            .setTipoDeConta(TipoDeConta.CONTA_CORRENTE)
            .setCriadaEm(LocalDateTime.now().let {
                val criadaEm = it.atZone(ZoneId.of("UTC")).toInstant()
                Timestamp.newBuilder().setSeconds(criadaEm.epochSecond).setNanos(criadaEm.nano).build()
            })
            .build()
        val cpf = ListaChaveResponse.ChavePix.newBuilder()
            .setPixId(UUID.randomUUID().toString())
            .setTipo(TipoDeChave.CPF)
            .setChave("75382715033")
            .setTipoDeConta(TipoDeConta.CONTA_CORRENTE)
            .setCriadaEm(LocalDateTime.now().let {
                val criadaEm = it.atZone(ZoneId.of("UTC")).toInstant()
                Timestamp.newBuilder().setSeconds(criadaEm.epochSecond).setNanos(criadaEm.nano).build()
            })
            .build()
        val telefone = ListaChaveResponse.ChavePix.newBuilder()
            .setPixId(UUID.randomUUID().toString())
            .setTipo(TipoDeChave.TELEFONE)
            .setChave("+5584999999999")
            .setTipoDeConta(TipoDeConta.CONTA_CORRENTE)
            .setCriadaEm(LocalDateTime.now().let {
                val criadaEm = it.atZone(ZoneId.of("UTC")).toInstant()
                Timestamp.newBuilder().setSeconds(criadaEm.epochSecond).setNanos(criadaEm.nano).build()
            })
            .build()
        val aleatoria = ListaChaveResponse.ChavePix.newBuilder()
            .setPixId(UUID.randomUUID().toString())
            .setTipo(TipoDeChave.ALEATORIA)
            .setChave(UUID.randomUUID().toString())
            .setTipoDeConta(TipoDeConta.CONTA_CORRENTE)
            .setCriadaEm(LocalDateTime.now().let {
                val criadaEm = it.atZone(ZoneId.of("UTC")).toInstant()
                Timestamp.newBuilder().setSeconds(criadaEm.epochSecond).setNanos(criadaEm.nano).build()
            })
            .build()

        return ListaChaveResponse.newBuilder()
            .setClientId(clientId)
            .addAllChaves(listOf(email,cpf,telefone,aleatoria))
            .build()
    }
    private fun geraListaChaveRequest(clientId: String): ListaChaveRequest {
        return ListaChaveRequest.newBuilder()
            .setClientId(clientId)
            .build()
    }
    @Factory
    @Replaces(factory = KeyManagerGrpcFactory::class)
    internal class MockFactory{
        @Singleton
        fun listaMock() = Mockito.mock(KeyManagerListaGrpServiceGrpc.KeyManagerListaGrpServiceBlockingStub::class.java)
    }
}