package br.com.zup.pix.remove

import br.com.zup.grpc.KeyManagerGrpcFactory
import br.com.zup.pix.KeyManagerRemoveGrpcServiceGrpc
import br.com.zup.pix.RemoveChaveResponse
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Replaces
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import java.util.*

import javax.inject.Inject
import javax.inject.Singleton

@MicronautTest
internal class RemoveChaveControllerTest {
    @field:Inject
    lateinit var remove: KeyManagerRemoveGrpcServiceGrpc.KeyManagerRemoveGrpcServiceBlockingStub

    @field:Inject
    @field:Client("/")
    lateinit var client: io.micronaut.http.client.HttpClient

    @Test
    internal fun `deve remover uma chave`() {
        val clientId = UUID.randomUUID().toString()
        val pixId = UUID.randomUUID().toString()
        val responseGrpc = RemoveChaveResponse.newBuilder()
            .setResponseMessage("Chave Excluida com sucesso")
            .build()

        given(remove.removeChave(any())).willReturn(responseGrpc)

        val request = HttpRequest.DELETE<Any>("/api/clientes/$clientId/pix/$pixId")
        val response = client.toBlocking().exchange(request, Any::class.java)

        assertEquals(HttpStatus.OK, response.status)

    }




    @Factory
    @Replaces(factory = KeyManagerGrpcFactory::class)
    internal class RemoveFactory {
        @Singleton
        fun removeChave() = Mockito.mock(KeyManagerRemoveGrpcServiceGrpc.KeyManagerRemoveGrpcServiceBlockingStub::class.java)
    }
}