package br.com.zup.pix.entidades

import br.com.zup.pix.chavepix.TipoDeChave
import br.com.zup.pix.chavepix.TipoDeConta
import br.com.zup.pix.chavepix.entidades.ChavePix
import br.com.zup.pix.chavepix.entidades.DadosBancarios
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.*

@MicronautTest(transactional = false)
internal class ChavePixTest {


    @Test
    fun`deve ser chave ALEATORIA`(){
        with(geraChavePix(TipoDeChave.ALEATORIA, UUID.randomUUID().toString(), UUID.randomUUID())){
            assertTrue(this.chaveAleatoria())
        }
    }


    @Test
    fun`nao deve ser chave ALEATORIA`(){
        with(geraChavePix(TipoDeChave.EMAIL, "teste@email.com.br", UUID.randomUUID())){
            assertFalse(this.chaveAleatoria())
        }
    }

    private fun geraChavePix(
        tipoDeChave: TipoDeChave,
        chave: String,
        clientId: UUID
    ) : ChavePix {
        return ChavePix(
            codigoCliente= clientId,
            tipoDeChave = tipoDeChave,
            chave = chave,
            tipoDeConta = TipoDeConta.CONTA_CORRENTE,
            conta = DadosBancarios(
                instituicao = "ITAU",
                titular = "Zup Edu",
                cpf = "26951023050",
                agencia = "1234",
                numero = "654321"

            )
        )
    }
}