package br.com.zup.pix.cadastra

import br.com.zup.pix.KeyManagerCadastraGrpcServiceGrpc
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import java.util.*
import javax.validation.Valid

@Validated
@Controller("/api/clientes/{clientId}")
class CadastraChaveController(
    val cadastraChavePixClient: KeyManagerCadastraGrpcServiceGrpc.KeyManagerCadastraGrpcServiceBlockingStub
) {

    @Post("/pix")
    fun cadastra(
        clientId: UUID,
        @Valid @Body request: ChavePixDto
    ) : HttpResponse<Any> {
        val response = cadastraChavePixClient.cadastraChave(request.toGrpcModel(clientId))
        return HttpResponse.created(location(clientId,response.chaveId))
    }

    private fun location(clientId: UUID, pixId: String) = HttpResponse.uri("/api/clientes/${clientId}/pix/${pixId}")
}