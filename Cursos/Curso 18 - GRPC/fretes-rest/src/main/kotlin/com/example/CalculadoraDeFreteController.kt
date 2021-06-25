package com.example

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import javax.inject.Inject

@Controller
class CalculadoraDeFreteController(@Inject val gRpcClient: FreteServiceGrpc.FreteServiceBlockingStub) {
    @Get("/api/fretes")
    fun calcula(@QueryValue cep: String): FreteResponse {
        val request = CalculaFreteRequest.newBuilder()
            .setCep(cep)
            .build()
        val calculaFrete = gRpcClient.calculaFrete(request)
        return FreteResponse(
            cep = calculaFrete.cep,
            valor = calculaFrete.valor
        )
    }
}

data class FreteResponse(val cep: String, val valor: Double) {

}
