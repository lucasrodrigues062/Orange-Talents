package br.com.zup.pix.lista

import br.com.zup.pix.KeyManagerListaGrpServiceGrpc
import br.com.zup.pix.ListaChaveRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import java.util.*

@Controller("/api/clientes/{clientId}")
class ListaChaveController (
    val listaChaveClient: KeyManagerListaGrpServiceGrpc.KeyManagerListaGrpServiceBlockingStub
        ){

    @Get("/pix")
    fun lista(clientId: UUID): HttpResponse<Any> {
        val response = listaChaveClient.lista(ListaChaveRequest.newBuilder()
            .setClientId(clientId.toString())
            .build())
        val chaves = response.chavesList.map { ListaChaveResponse(it) }
        return HttpResponse.ok(chaves)
    }
}