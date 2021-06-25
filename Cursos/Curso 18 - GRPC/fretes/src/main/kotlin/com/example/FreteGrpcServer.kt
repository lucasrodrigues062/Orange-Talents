package com.example

import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.grpc.stub.StreamObserver
import org.slf4j.LoggerFactory
import java.lang.IllegalArgumentException
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class FreteGrpcServer: FreteServiceGrpc.FreteServiceImplBase() {
    private val logger = LoggerFactory.getLogger(FreteGrpcServer::class.java)
    override fun calculaFrete(request: CalculaFreteRequest?, responseObserver: StreamObserver<CalculaFreteResponse>?) {

        logger.info("Calculando frete para request: $request")

        val cep = request?.cep
        if (cep == null || cep.isBlank()){
            val erro = StatusRuntimeException(Status.INVALID_ARGUMENT.withDescription("Cep deve ser informado"))
            responseObserver?.onError(erro)
        }
        if(!cep!!.matches("[0-9]{5}-[0-9]{3}".toRegex())){
            val e = Status.INVALID_ARGUMENT
                .withDescription("Cep Invalido")
                .augmentDescription("formato deve ser 99999-999")
                .asRuntimeException()
            responseObserver?.onError(e)
        }

        val response = CalculaFreteResponse.newBuilder()
            .setCep(request!!.cep)
            .setValor(Random.nextDouble(from = 0.0, until = 140.0))
            .build()
        logger.info("Frete calcualdo: $response")

        responseObserver!!.onNext(response)
        responseObserver.onCompleted()

    }
}