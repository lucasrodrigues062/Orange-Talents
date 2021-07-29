package br.com.zup.pix.compartilhado

import br.com.zup.pix.chavepix.TipoDeChave
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class TipoDeChaveTest {
    @Nested
    inner class CPF {
        @Test
        fun `deve ser um CPF valido`(){
            with(TipoDeChave.CPF){
                assertTrue( valida("14250854078"))
            }
        }
        @Test
        fun`nao deve ser um CPF valido`(){
            with(TipoDeChave.CPF){
                assertFalse( valida("14250854079"))
            }
        }
        @Test
        fun`nao deve ser informado CPF nulo`(){
            with(TipoDeChave.CPF){
                assertFalse(valida(""))
                assertFalse(valida(null))
            }
        }
    }

    @Nested
    inner class CELULAR {
        @Test
        fun `deve ser um TELEFONE valido`(){
            with(TipoDeChave.TELEFONE){
                assertTrue( valida("+5584998506358"))
            }
        }
        @Test
        fun`nao deve ser um TELEFONE valido`(){
            with(TipoDeChave.TELEFONE){
                assertFalse( valida("998506358"))
            }
        }
        @Test
        fun`nao deve ser informado TELEFONE nulo`(){
            with(TipoDeChave.TELEFONE){
                assertFalse(valida(""))
                assertFalse(valida(null))
            }
        }
    }

    @Nested
    inner class ALEATORIA {
        @Test
        fun`deve gerar uma chave aleatoria`() {
            with(TipoDeChave.ALEATORIA) {
                assertTrue(valida(null))
                assertTrue(valida(""))
            }
        }

        @Test
        fun`nao deve gerar uma chave aleatoria`() {
            with(TipoDeChave.ALEATORIA) {
                assertFalse(valida("chave"))

            }
        }
    }

    @Nested
    inner class EMAIL {
        @Test
        fun `deve ser um email valido`() {
            with(TipoDeChave.EMAIL) {
                assertTrue(valida("zup@zup.com.br"))
            }
        }

        @Test
        fun`nao deve ser um email valido`(){
            with(TipoDeChave.EMAIL) {
                assertFalse(valida("zupzup.com"))

            }
        }
        @Test
        fun `nao deve ser informado email nulo`() {
            with(TipoDeChave.EMAIL){
                assertFalse(valida(null))
                assertFalse(valida(""))
            }
        }
    }
}