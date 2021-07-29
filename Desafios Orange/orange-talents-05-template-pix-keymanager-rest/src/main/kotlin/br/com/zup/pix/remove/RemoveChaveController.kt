package br.com.zup.pix.remove

import br.com.zup.pix.KeyManagerRemoveGrpcServiceGrpc
import br.com.zup.pix.RemoveChaveRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import java.util.*

@Controller("/api/clientes/{clientId}")
class RemoveChaveController(
    val removeChave: KeyManagerRemoveGrpcServiceGrpc.KeyManagerRemoveGrpcServiceBlockingStub
) {

    @Delete("/pix/{pixId}")
    fun exclui(
        clientId: UUID,
        pixId: UUID
    ): HttpResponse<Any> {
        removeChave.removeChave(
            RemoveChaveRequest.newBuilder()
                .setClientId(clientId.toString())
                .setChaveId(pixId.toString())
                .build()
        )
        return HttpResponse.ok()
    }

}