package com.example

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import javax.inject.Singleton

@Factory
class GrpcClienteFactory {

    @Singleton
    fun fretesClientStub(@GrpcChannel("fretes") channel: ManagedChannel):FreteServiceGrpc.FreteServiceBlockingStub? {

        return FreteServiceGrpc.newBlockingStub(channel)

    }
}