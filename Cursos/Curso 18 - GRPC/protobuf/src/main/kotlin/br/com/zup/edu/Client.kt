package br.com.zup.edu

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder

fun main() {
    val channel = ManagedChannelBuilder.forAddress("localhost", 50051)
        .usePlaintext()
        .build()

    val request = FuncionarioRequest.newBuilder()
        .setNome("Lucas Rodrigues")
        .setCpf("094.237.498-55")
        .setSalario(2000.50)
        .setIdade(29)
        .setAtivo(true)
        .setCargo(Cargo.QA)
        .addEnderecos(FuncionarioRequest.Endereco.newBuilder()
            .setLogradouro("Rua das Ostras")
            .setCep("00000-000")
            .setComplemento("Casa 69")
            .build())
        .build()
    val client = FuncionarioServiceGrpc.newBlockingStub(channel)
    val response = client.cadastrar(request)

    println(response)
}