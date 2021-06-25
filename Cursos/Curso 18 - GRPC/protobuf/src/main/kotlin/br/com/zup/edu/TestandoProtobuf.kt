package br.com.zup.edu

import java.io.FileInputStream
import java.io.FileOutputStream

fun main() {
    val request = FuncionarioRequest.newBuilder()
        .setNome("Lucas Rodrigues")
        .setCpf("094.237.498-55")
        .setSalario(2000.50)
        .setAtivo(true)
        .setCargo(Cargo.QA)
        .addEnderecos(FuncionarioRequest.Endereco.newBuilder()
            .setLogradouro("Rua das Ostras")
            .setCep("00000-000")
            .setComplemento("Casa 69")
            .build())
        .build()
    println(request)

    request.writeTo(FileOutputStream("funcionario-request.bin"))

    val request2 = FuncionarioRequest.newBuilder()
        .mergeFrom(FileInputStream("funcionario-request.bin"))
    request2.setCargo(Cargo.GERENTE)

    println(request2)
}