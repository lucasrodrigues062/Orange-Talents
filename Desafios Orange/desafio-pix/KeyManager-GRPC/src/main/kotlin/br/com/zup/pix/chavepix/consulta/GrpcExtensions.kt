package br.com.zup.pix.chavepix.consulta

import br.com.zup.pix.ConsultaChaveRequest
import java.lang.IllegalArgumentException

import javax.validation.ConstraintViolationException
import javax.validation.Validator

fun ConsultaChaveRequest.toModel(validator: Validator): DadosConsulta {

    val dadosConsulta = when(filtroCase!!){
        ConsultaChaveRequest.FiltroCase.PIXID -> TipoConsulta.geraDadosConsulta(TipoConsulta.CONSULTA_POR_CLIENTE, this)
        ConsultaChaveRequest.FiltroCase.CHAVE -> TipoConsulta.geraDadosConsulta(TipoConsulta.CONSULTA_POR_CHAVE, this)
        ConsultaChaveRequest.FiltroCase.FILTRO_NOT_SET -> throw IllegalArgumentException("Chave Pix Invalida ou Nao Informada!")
    }
    val violations = validator.validate(dadosConsulta)
    if (violations.isNotEmpty()){
        throw ConstraintViolationException(violations)
    }

    return dadosConsulta
}