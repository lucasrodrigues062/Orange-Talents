package br.com.zup.pix.chavepix.services

import br.com.zup.pix.chavepix.TipoDeChave
import br.com.zup.pix.chavepix.TipoDeConta
import br.com.zup.pix.chavepix.entidades.ChavePix
import br.com.zup.pix.chavepix.entidades.DadosBancarios
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client
import java.time.LocalDateTime

@Client("\${api.consulta.bcb}")
interface ClientBCB {
    @Post("/api/v1/pix/keys", produces = arrayOf(MediaType.APPLICATION_XML), consumes = arrayOf(MediaType.APPLICATION_XML))
    fun cadastra(@Body request: BcbCadastraChaveRequest): HttpResponse<BcbCadastraChaveResponse>

    @Delete("/api/v1/pix/keys/{key}", produces = arrayOf(MediaType.APPLICATION_XML), consumes = arrayOf(MediaType.APPLICATION_XML))
    fun exclui(@PathVariable key: String, @Body request: BcbExcluiChaveRequest): HttpResponse<BcbExcluiChaveResponse>

    @Get("/api/v1/pix/keys/{key}", consumes = [MediaType.APPLICATION_XML])
    fun buscaPorChave(@PathVariable key: String): HttpResponse<BcbChaveDetalhesResponse>
}

data class BcbChaveDetalhesResponse (
    val keyType: BcbTipodeChave,
    val key: String,
    val bankAccount: BcbContaBancaria,
    val owner: BcbProprietario,
    val createdAt: LocalDateTime
    )



data class BcbCadastraChaveResponse(
    val keyType: BcbTipodeChave,
    val key: String,
    val bankAccount: BcbContaBancaria,
    val owner: BcbProprietario,
    val createdAt: LocalDateTime
)



data class BcbCadastraChaveRequest(
    val keyType: BcbTipodeChave,
    val key: String,
    val bankAccount: BcbContaBancaria,
    val owner: BcbProprietario
) {
    companion object{
        fun criaRequest(chave: ChavePix): BcbCadastraChaveRequest{
            return BcbCadastraChaveRequest(
                keyType = BcbTipodeChave.converte(chave.tipoDeChave),
                key = chave.chave,
                bankAccount = BcbContaBancaria(
                    participant = DadosBancarios.ITAU_ISPB,
                    branch = chave.conta.agencia,
                    accountNumber = chave.conta.numero,
                    accountType = BcbTipoDeConta.converte(chave.tipoDeConta)
                ),
                owner = BcbProprietario(
                    type = BcbProprietario.BcbTipoProprietario.NATURAL_PERSON,
                    name = chave.conta.titular,
                    taxIdNumber = chave.conta.cpf
                )

            )
        }
    }

}

data class BcbProprietario (
    val type: BcbTipoProprietario,
    val name: String,
    val taxIdNumber: String
        ){
    enum class BcbTipoProprietario{
        NATURAL_PERSON,
        LEGAL_PERSON
    }

}

data class BcbContaBancaria(
    val participant: String,
    val branch:String,
    val accountNumber:String,
    val accountType: BcbTipoDeConta
) {

}

public enum class BcbTipoDeConta {
    CACC,
    SVGS;
companion object{

    fun converte(tipoDeConta: TipoDeConta): BcbTipoDeConta{
        return when (tipoDeConta) {
            TipoDeConta.CONTA_CORRENTE -> CACC
            TipoDeConta.CONTA_POUPANCA -> SVGS
        }
    }
    fun toTipoDeConta(bcbTipoDeConta: BcbTipoDeConta): TipoDeConta{
        return when(bcbTipoDeConta){
            CACC -> TipoDeConta.CONTA_CORRENTE
            SVGS -> TipoDeConta.CONTA_POUPANCA
        }
    }

}

}

enum class BcbTipodeChave {
    CPF,
    PHONE,
    EMAIL,
    RANDOM;
    companion object{
        fun converte(tipoDeChave: TipoDeChave): BcbTipodeChave {

            return when(tipoDeChave){
                TipoDeChave.CPF -> CPF
                TipoDeChave.TELEFONE -> PHONE
                TipoDeChave.EMAIL -> EMAIL
                TipoDeChave.ALEATORIA -> RANDOM
            }
        }
        fun toTipoDeChave(bcbTipodeChave: BcbTipodeChave): TipoDeChave{
            return when(bcbTipodeChave){
                CPF -> TipoDeChave.CPF
                PHONE -> TipoDeChave.TELEFONE
                EMAIL -> TipoDeChave.EMAIL
                RANDOM -> TipoDeChave.ALEATORIA
            }
        }
    }

}

data class BcbExcluiChaveResponse(
    val key: String,
    val participant: String,
    val deletedAt: LocalDateTime
)

data class BcbExcluiChaveRequest(
    val key: String,
    val participant: String = DadosBancarios.ITAU_ISPB
)
