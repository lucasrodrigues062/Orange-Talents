package br.com.zup.pix.compartilhado.handlers

import io.grpc.BindableService
import io.grpc.stub.StreamObserver
import io.micronaut.aop.MethodInvocationContext
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.Mockito.notNull
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class ExceptionHandlerInterceptorTest {

    @Mock
    lateinit var context: MethodInvocationContext<BindableService,Any?>

    val interceptor = ExceptionHandlerInterceptor(resolver = ExceptionHandlerResolver(handlers = emptyList()))
    @Test
    fun `deve capturar a excecao lancada pelo execucao do metodo, e gerar um erro na resposta gRPC`(@Mock streamObserver: StreamObserver<*>) {
        with (context) {
            `when`(proceed()).thenThrow(RuntimeException("deu xablau!"))
            `when`(parameterValues).thenReturn(arrayOf(null, streamObserver))
        }

        interceptor.intercept(context)

        verify(streamObserver).onError(notNull())
    }

    @Test
    fun `se o metodo nao gerar nenhuma excecao, deve apenas retornar a mesma resposta`() {
        val expected = "tranqueira"

        `when`(context.proceed()).thenReturn(expected)

        assertEquals(expected, interceptor.intercept(context))
    }

}