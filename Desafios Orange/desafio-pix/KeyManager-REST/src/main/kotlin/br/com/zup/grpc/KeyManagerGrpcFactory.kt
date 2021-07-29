package br.com.zup.grpc

import br.com.zup.pix.KeyManagerCadastraGrpcServiceGrpc
import br.com.zup.pix.KeyManagerConsultaGrpcServiceGrpc
import br.com.zup.pix.KeyManagerListaGrpServiceGrpc
import br.com.zup.pix.KeyManagerRemoveGrpcServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import javax.inject.Singleton

@Factory
class KeyManagerGrpcFactory(@GrpcChannel("keyManager") val channel: ManagedChannel) {

    @Singleton
    fun cadastraChave() = KeyManagerCadastraGrpcServiceGrpc.newBlockingStub(channel)
    @Singleton
    fun removeChave() = KeyManagerRemoveGrpcServiceGrpc.newBlockingStub(channel)
    @Singleton
    fun listaChave() = KeyManagerListaGrpServiceGrpc.newBlockingStub(channel)
    @Singleton
    fun consultaChave() = KeyManagerConsultaGrpcServiceGrpc.newBlockingStub(channel)
}