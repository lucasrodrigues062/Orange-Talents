package br.com.zup.grpc

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class TipoDeChaveTest {

    @Nested
    inner class ChaveAleatoriaTest {

        @Test
        fun `deve ser valido quando a chave for nula ou em branco`() {
            val tipoDeChave = TipoDeChave.ALEATORIA
            assertTrue(tipoDeChave.valida(null))
            assertTrue(tipoDeChave.valida(""))
        }

        @Test
        fun `nao deve ser valido quando chave possuir um valor`() {
            val tipoDeChave = TipoDeChave.ALEATORIA
            assertFalse(tipoDeChave.valida("123"))
        }

    }

    inner class ChaveCpfTest {
        @Test
        fun `deve ser valido quando cpf for valido`(){
            val tipoDeChave = TipoDeChave.CPF
            assertTrue(tipoDeChave.valida("28078087004"))
        }
        @Test
        fun `nao deve ser valido quando for invalido, nulo ou em branco`(){
            val tipoDeChave = TipoDeChave.CPF

            assertFalse(tipoDeChave.valida("28078087000"))
            assertFalse(tipoDeChave.valida(""))
            assertFalse(tipoDeChave.valida(null))

        }
    }

    inner class ChaveTelefoneTest {
        @Test
        fun `deve ser valido quando telefone for valido`() {
            val tipoDeChave = TipoDeChave.TELEFONE

            assertTrue(tipoDeChave.valida("+5583998506398"))
        }
        @Test
        fun `nao deve ser valido quando telefone for invalido, nulo ou em branco`(){
            val tipoDeChave = TipoDeChave.TELEFONE
            assertFalse(tipoDeChave.valida("846685"))
            assertFalse(tipoDeChave.valida(""))
            assertFalse(tipoDeChave.valida(null))
        }

    }

    inner class ChaveEmailTest {
        @Test
        fun`deve ser valido quando email for valido`(){
            val tipoDeChave = TipoDeChave.EMAIL
            assertTrue(tipoDeChave.valida("zup@zup.com.br"))
        }
        @Test
        fun`nao deve ser valido quando email for invalido, nulo ou em branco`(){
            val tipoDeChave = TipoDeChave.EMAIL
            assertFalse(tipoDeChave.valida("846685"))
            assertFalse(tipoDeChave.valida(""))
            assertFalse(tipoDeChave.valida(null))
        }
    }
}