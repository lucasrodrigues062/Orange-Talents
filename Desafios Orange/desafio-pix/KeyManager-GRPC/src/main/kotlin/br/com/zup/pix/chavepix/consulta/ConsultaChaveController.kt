package br.com.zup.pix.chavepix.consulta

import br.com.zup.pix.ConsultaChaveRequest
import br.com.zup.pix.ConsultaChaveResponse
import br.com.zup.pix.KeyManagerConsultaGrpcServiceGrpc

import br.com.zup.pix.compartilhado.handlers.ErrorHandler
import io.grpc.stub.StreamObserver
import javax.inject.Inject
import javax.inject.Singleton

import javax.validation.Validator

@ErrorHandler
@Singleton
class ConsultaChaveController(
    @Inject val consultaChaveService: ConsultaChaveService,
    @Inject val validator: Validator

) : KeyManagerConsultaGrpcServiceGrpc.KeyManagerConsultaGrpcServiceImplBase(){

    override fun consultaChave(
        request: ConsultaChaveRequest?,
        responseObserver: StreamObserver<ConsultaChaveResponse>?
    ) {
        val dadosConsulta = request?.toModel(validator)
        val chaveResponse = consultaChaveService.consultaChave(dadosConsulta!!)

        responseObserver?.onNext(chaveResponse.toConsultaChavePixResponse())
        responseObserver?.onCompleted()
    }
}