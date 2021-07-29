package br.com.zup.pix.chavepix.cadastra

import br.com.zup.pix.ChavePixRequest
import br.com.zup.pix.ChavePixResponse
import br.com.zup.pix.KeyManagerCadastraGrpcServiceGrpc
import br.com.zup.pix.compartilhado.handlers.ErrorHandler
import io.grpc.stub.StreamObserver
import org.slf4j.LoggerFactory

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ErrorHandler
class ChaveController(@Inject val cadastraChaveService: CadastraChaveService): KeyManagerCadastraGrpcServiceGrpc.KeyManagerCadastraGrpcServiceImplBase() {

    private val logger = LoggerFactory.getLogger(ChaveController::class.java)

    override fun cadastraChave(request: ChavePixRequest?, responseObserver: StreamObserver<ChavePixResponse>?) {

        val chaveDto = request?.toModel()

        val chavePix = cadastraChaveService.cadastraChavePix(chaveDto!!)

        responseObserver?.onNext(ChavePixResponse.newBuilder()
            .setClientId(chavePix.codigoCliente.toString())
            .setChaveId(chavePix.id.toString())
            .build())
        responseObserver?.onCompleted()
    }

}
