package br.com.zup.pix.chavepix.remove

import br.com.zup.pix.KeyManagerRemoveGrpcServiceGrpc
import br.com.zup.pix.RemoveChaveRequest
import br.com.zup.pix.RemoveChaveResponse
import br.com.zup.pix.compartilhado.handlers.ErrorHandler
import io.grpc.stub.StreamObserver
import javax.inject.Inject


@ErrorHandler
class RemoveChaveController(@Inject private val removeChaveService: RemoveChaveService): KeyManagerRemoveGrpcServiceGrpc.KeyManagerRemoveGrpcServiceImplBase() {
    override fun removeChave(request: RemoveChaveRequest?, responseObserver: StreamObserver<RemoveChaveResponse>?) {

        removeChaveService.remove(clientId = request?.clientId, chaveId = request?.chaveId)

        responseObserver?.onNext(RemoveChaveResponse.newBuilder()
            .setResponseMessage("Chave excluida com sucesso")
            .build())

        responseObserver?.onCompleted()
    }
}