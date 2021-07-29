package br.com.zup.pix.detalha

import br.com.zup.pix.ConsultaChaveRequest
import br.com.zup.pix.KeyManagerConsultaGrpcServiceGrpc
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import java.util.*

@Controller("/api/clientes/{clientId}")
class DetalhaChaveController (
    val detalhaChaveClient: KeyManagerConsultaGrpcServiceGrpc.KeyManagerConsultaGrpcServiceBlockingStub
        ){

    @Get("/pix/{pixId}")
    fun detalha(
        clientId: UUID,
        pixId: UUID
    ): HttpResponse<Any> {

        val responseChave = detalhaChaveClient.consultaChave(ConsultaChaveRequest.newBuilder()
            .setPixId(
                ConsultaChaveRequest.ConsultaPorId.newBuilder()
                    .setClientId(clientId.toString())
                    .setPixId(pixId.toString())
                    .build())
            .build())
        //println(DetalheChaveResponse(responseChave))
        return HttpResponse.ok(DetalheChaveResponse(responseChave))

    }
}

