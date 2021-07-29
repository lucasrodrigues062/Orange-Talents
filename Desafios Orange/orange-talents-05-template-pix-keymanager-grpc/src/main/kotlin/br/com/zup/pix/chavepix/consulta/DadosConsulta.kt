package br.com.zup.pix.chavepix.consulta

import br.com.zup.pix.ConsultaChaveRequest
import br.com.zup.pix.compartilhado.validacao.ValidUUID
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

enum class TipoConsulta() {

    CONSULTA_POR_CLIENTE,
    CONSULTA_POR_CHAVE;

    companion object {
        fun geraDadosConsulta(tipoConsulta: TipoConsulta, consultaChaveRequest: ConsultaChaveRequest):DadosConsulta {
           return when (tipoConsulta){
                CONSULTA_POR_CHAVE -> DadosConsultaChave(
                    chave = consultaChaveRequest.chave,
                    tipoConsulta = tipoConsulta)
               CONSULTA_POR_CLIENTE -> DadosConsultaPix(
                   tipoConsulta = tipoConsulta,
                   clientId = consultaChaveRequest.pixId.clientId,
                   pixId = consultaChaveRequest.pixId.pixId
               )

           }
        }
    }

}

abstract class DadosConsulta (
    @field:NotNull
    open val tipoConsulta: TipoConsulta
    ) {

}


data class DadosConsultaChave(
    @field:NotNull
    override val tipoConsulta: TipoConsulta,
    @field:NotBlank @field:NotNull
    val chave: String
    ) : DadosConsulta(tipoConsulta) {

}
data class DadosConsultaPix(
    @field:NotNull
    override val tipoConsulta: TipoConsulta,
    @field:NotNull @field:NotBlank @field:ValidUUID
    val clientId: String,
    @field:NotNull @field:NotBlank @field:ValidUUID
    val pixId: String
): DadosConsulta(tipoConsulta )
