package br.com.zup.grpc

import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.micronaut.http.HttpRequest

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.hateoas.JsonError
import io.micronaut.http.server.exceptions.ExceptionHandler
import javax.inject.Singleton

@Singleton
class ErrorHandler : ExceptionHandler<StatusRuntimeException, HttpResponse<Any>> {
    override fun handle(request: HttpRequest<*>?, exception: StatusRuntimeException?): HttpResponse<Any> {
        val statusCode = exception!!.status.code
        val description = exception.status.description ?: ""
        val (httpStatus, message) = when (statusCode) {
            Status.Code.UNKNOWN -> Pair(HttpStatus.INTERNAL_SERVER_ERROR, "Erro desconhecido do lado do servidor GRPC")
            Status.Code.INVALID_ARGUMENT -> Pair(HttpStatus.BAD_REQUEST, "Dados da requisição estão inválidos")
            Status.Code.NOT_FOUND -> Pair(HttpStatus.NOT_FOUND, description)
            Status.Code.ALREADY_EXISTS -> Pair(HttpStatus.UNPROCESSABLE_ENTITY, description)

            else -> {
                Pair(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível realizar a requisição - description: ${description} - statusCode: ${statusCode}")
            }
        }
        return HttpResponse.status<JsonError>(httpStatus).body(JsonError(message))
    }

}