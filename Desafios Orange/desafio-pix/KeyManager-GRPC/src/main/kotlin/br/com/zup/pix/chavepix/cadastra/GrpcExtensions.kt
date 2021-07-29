package br.com.zup.pix.chavepix.cadastra


import br.com.zup.pix.ChavePixRequest

import br.com.zup.pix.chavepix.TipoDeChave
import br.com.zup.pix.chavepix.TipoDeConta

fun ChavePixRequest.toModel(): ChavePixDto {
    return ChavePixDto(
        codigoInterno = codigoInterno,
        tipoDeChave = when (tipoDeChave) {
           br.com.zup.pix.TipoDeChave.DEFAULT_CHAVE -> null
            else -> TipoDeChave.valueOf(tipoDeChave.name)
        },
        valorDaChave = valorDaChave,
        tipoDeConta = when (tipoDeConta){
            br.com.zup.pix.TipoDeConta.DEFAULT_CONTA -> null
            else -> TipoDeConta.valueOf(tipoDeConta.name)
        }
    )
}