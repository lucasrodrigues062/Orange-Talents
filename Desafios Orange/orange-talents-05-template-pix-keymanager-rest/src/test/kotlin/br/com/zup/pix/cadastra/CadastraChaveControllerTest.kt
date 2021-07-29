package br.com.zup.pix.cadastra

import br.com.zup.grpc.KeyManagerGrpcFactory
import br.com.zup.grpc.TipoDeChave
import br.com.zup.grpc.TipoDeConta
import br.com.zup.pix.ChavePixResponse
import br.com.zup.pix.KeyManagerCadastraGrpcServiceGrpc
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Replaces
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito

import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@MicronautTest
internal class CadastraChaveControllerTest {
    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @field:Inject
    lateinit var registra: KeyManagerCadastraGrpcServiceGrpc.KeyManagerCadastraGrpcServiceBlockingStub

    @Test
    fun `deve cadastrar uma nova chave pix`() {
        val clientId = UUID.randomUUID().toString()
        val pixId = UUID.randomUUID().toString()

        val responseGrpc = ChavePixResponse.newBuilder()
            .setChaveId(pixId)
            .setClientId(clientId)
            .build()

        given(registra.cadastraChave(Mockito.any())).willReturn(responseGrpc)

        val novaChave = ChavePixDto(
            tipoDeChave = TipoDeChave.EMAIL,
            chave = "teste@tes.com.br",
            tipoDeConta = TipoDeConta.CONTA_CORRENTE
        )

        val request = HttpRequest.POST("/api/clientes/$clientId/pix", novaChave)
        val response = client.toBlocking().exchange(request,ChavePixDto::class.java)

        assertEquals(HttpStatus.CREATED,response.status)
        assertTrue(response.headers.contains("Location"))
        assertTrue(response.header("Location").contains(pixId))
    }


    @Factory
    @Replaces(factory = KeyManagerGrpcFactory::class)
    internal class MockitoFactory {
        @Singleton
        fun stubMock() =
            Mockito.mock(KeyManagerCadastraGrpcServiceGrpc.KeyManagerCadastraGrpcServiceBlockingStub::class.java)
    }
}