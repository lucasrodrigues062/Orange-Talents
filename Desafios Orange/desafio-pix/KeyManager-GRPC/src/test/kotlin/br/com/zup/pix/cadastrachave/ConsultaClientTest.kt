package br.com.zup.pix.cadastrachave

import br.com.zup.pix.cadastrachavepix.ConsultaContaResponse
import br.com.zup.pix.cadastrachavepix.Instituicao
import br.com.zup.pix.cadastrachavepix.Titular
import br.com.zup.pix.chavepix.TipoDeChave
import br.com.zup.pix.chavepix.entidades.DadosBancarios
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

@MicronautTest(transactional = false)
internal class ConsultaClientTest {

    @Test
    fun`deve gerar uma ConsultaResponse`(){
        with(geraConsultaContaResponse()){
            assertEquals(this, geraConsultaContaResponse())
        }
    }

    private fun geraConsultaContaResponse(): ConsultaContaResponse{
        return ConsultaContaResponse(
            tipo = "CPF",
            instituicao = Instituicao(
                nome = "ITAU",
                ispb = DadosBancarios.ITAU_ISPB
            ),
            agencia = "1234",
            numero = "654321",
            titular = Titular(
                nome = "Zup Edu",
                cpf = "26951023050"

            )
        )
    }
}