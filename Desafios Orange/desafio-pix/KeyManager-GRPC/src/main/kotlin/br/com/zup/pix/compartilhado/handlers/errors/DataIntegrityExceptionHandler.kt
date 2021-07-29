package br.com.zup.pix.compartilhado.handlers.errors
import br.com.zup.pix.compartilhado.handlers.ExceptionHandler

import io.grpc.Status
import io.micronaut.context.MessageSource
import io.micronaut.context.MessageSource.MessageContext
import org.hibernate.exception.ConstraintViolationException
import javax.inject.Inject
import javax.inject.Singleton

class DataIntegrityExceptionHandler(@Inject var messageSource: MessageSource) :
    ExceptionHandler<ConstraintViolationException> {

    override fun handle(e: ConstraintViolationException): ExceptionHandler.StatusWithDetails {

        val constraintName = e.constraintName
        if (constraintName.isNullOrBlank()) {
            return internalServerError(e)
        }

        val message = messageSource.getMessage("data.integrity.error.$constraintName", MessageContext.DEFAULT)
        return message
            .map { alreadyExistsError(it, e) } // TODO: dealing with many types of constraint errors
            .orElse(internalServerError(e))
    }

    override fun supports(e: Exception): Boolean {
        return e is ConstraintViolationException
    }

    private fun alreadyExistsError(message: String?, e: ConstraintViolationException) =
        ExceptionHandler.StatusWithDetails(
            Status.ALREADY_EXISTS
                .withDescription(message)
                .withCause(e)
        )

    private fun internalServerError(e: ConstraintViolationException) =
        ExceptionHandler.StatusWithDetails(
            Status.INTERNAL
                .withDescription("Unexpected internal server error")
                .withCause(e)
        )
}