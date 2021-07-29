package br.com.zup.pix.chavepix.entidades

import javax.persistence.Embeddable

@Embeddable
class DadosBancarios (
    val instituicao: String,
    val titular: String,

    val cpf: String,
    val agencia: String,
    val numero: String
        ){

    companion object{
        public val ITAU_ISPB:String = "60701190"
    }
}