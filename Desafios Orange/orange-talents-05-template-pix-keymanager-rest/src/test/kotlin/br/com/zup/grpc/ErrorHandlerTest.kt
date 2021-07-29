package br.com.zup.grpc

import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.hateoas.JsonError
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ErrorHandlerTest {

    @Test
    fun `deve retornar status 404 quando GRPC informar not found`(){
        val mensagem = "nao encontrado"
        val exception = StatusRuntimeException(Status.NOT_FOUND
            .withDescription(mensagem))
        val response = ErrorHandler().handle(HttpRequest.GET<Any>("/"), exception)

        assertEquals(HttpStatus.NOT_FOUND, response.status)
        assertNotNull(response.body())
        assertEquals(mensagem,(response.body() as JsonError).message)
    }
    @Test
    fun `deve retornar 422 quando GRPC informar already exists`() {
        val mensagem = "chave ja existente"
        val exception = StatusRuntimeException(Status.ALREADY_EXISTS
            .withDescription(mensagem))
        val response = ErrorHandler().handle(HttpRequest.GET<Any>("/"), exception)

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.status)
        assertNotNull(response.body())
        assertEquals(mensagem,(response.body() as JsonError).message)
    }
    @Test
    fun `deve retornar 400 quando GRPC informar invalid argument`() {
        val mensagem = "Dados da requisição estão inválidos"
        val exception = StatusRuntimeException(Status.INVALID_ARGUMENT
            .withDescription(mensagem))
        val response = ErrorHandler().handle(HttpRequest.GET<Any>("/"), exception)

        assertEquals(HttpStatus.BAD_REQUEST, response.status)
        assertNotNull(response.body())
        assertEquals(mensagem,(response.body() as JsonError).message)
    }
    @Test
    fun `deve retornar 500 quando GRPC informar erro nao catalogado`() {

        val exception = StatusRuntimeException(Status.INTERNAL)
        val unknown = StatusRuntimeException(Status.UNKNOWN)
        val responseException = ErrorHandler().handle(HttpRequest.GET<Any>("/"), exception)
        val responseUnknown = ErrorHandler().handle(HttpRequest.GET<Any>("/"), unknown)

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseException.status)
        assertNotNull(responseException.body())
        assertTrue((responseException.body() as JsonError).message.contains("INTERNAL"))

        //println((responseUnknown.body() as JsonError).message)

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseUnknown.status)
        assertNotNull(responseUnknown.body())

        assertTrue((responseUnknown.body() as JsonError).message.contains("Erro desconhecido do lado do servidor GRPC"))
    }
}