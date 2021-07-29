package br.com.zup.pix.detalha

import br.com.zup.grpc.KeyManagerGrpcFactory
import br.com.zup.pix.ConsultaChaveResponse
import br.com.zup.pix.KeyManagerConsultaGrpcServiceGrpc
import br.com.zup.pix.TipoDeChave
import br.com.zup.pix.TipoDeConta
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
class DetalheChaveControllerTest {
    @field:Inject
    lateinit var detalheChaveClient: KeyManagerConsultaGrpcServiceGrpc.KeyManagerConsultaGrpcServiceBlockingStub

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Test
    fun `deve detalhar uma chave pix`() {
        val clientId = UUID.randomUUID().toString()
        val pixId = UUID.randomUUID().toString()

        given(detalheChaveClient.consultaChave(Mockito.any())).willReturn(geraConsultaChaveResponse(clientId, pixId))
        val request = HttpRequest.GET<Any>("/api/clientes/$clientId/pix/$pixId")
        val response = client.toBlocking().exchange(request, Any::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())
    }

    private fun geraConsultaChaveResponse(clientId: String, pixId: String) : ConsultaChaveResponse{
        return ConsultaChaveResponse.newBuilder()
            .setClientId(clientId)
            .setPixId(pixId)
            .setChavePix(ConsultaChaveResponse.ChavePix.newBuilder()
                .setTipo(TipoDeChave.EMAIL)
                .setChave("teste@teste.com.br")
                .setConta(ConsultaChaveResponse.ChavePix.DadosBancarios.newBuilder()
                    .setTipo(TipoDeConta.CONTA_CORRENTE)
                    .setInstituicao("ITAU")
                    .setTitular("Zup Edu")
                    .setCpf("75382715033")
                    .setAgencia("1234")
                    .setNumeroConta("123456")
                    .build())
                .setCriadaEm(LocalDateTime.now().let {
                    val criadaEm = it.atZone(ZoneId.of("UTC")).toInstant()
                    Timestamp.newBuilder()
                        .setSeconds(criadaEm.epochSecond)
                        .setNanos(criadaEm.nano)
                        .build()
                })
                .build())
            .build()
    }

    @Factory
    @Replaces(factory = KeyManagerGrpcFactory::class)
    internal class MockitoFactory {
        @Singleton
        fun detalhesMock() = Mockito.mock(KeyManagerConsultaGrpcServiceGrpc.KeyManagerConsultaGrpcServiceBlockingStub::class.java)
    }
}