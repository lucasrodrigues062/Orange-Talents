package br.com.zup.pix.chavepix.consulta

import br.com.zup.pix.ConsultaChaveResponse
import br.com.zup.pix.chavepix.TipoDeChave
import br.com.zup.pix.chavepix.TipoDeConta
import br.com.zup.pix.chavepix.entidades.ChavePix
import br.com.zup.pix.chavepix.entidades.DadosBancarios
import br.com.zup.pix.chavepix.services.BcbChaveDetalhesResponse
import br.com.zup.pix.chavepix.services.BcbTipoDeConta
import br.com.zup.pix.chavepix.services.BcbTipodeChave
import br.com.zup.pix.chavepix.services.Instituicoes
import com.google.protobuf.Timestamp
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

data class ChaveInfo (
    val pixId: UUID? = null,
    val clienteId: UUID? = null,
    val tipoDeChave: TipoDeChave,
    val chave: String,
    val tipoDeConta: TipoDeConta,
    val conta: DadosBancarios,
    val registradaEm: LocalDateTime = LocalDateTime.now()
    ){

    companion object{
        fun fromChavePix(chavePix: ChavePix): ChaveInfo{
            return ChaveInfo(
                pixId = chavePix.id,
                clienteId = chavePix.codigoCliente,
                tipoDeChave = chavePix.tipoDeChave,
                chave = chavePix.chave,
                tipoDeConta = chavePix.tipoDeConta,
                conta = chavePix.conta,
                registradaEm = chavePix.resgistradaEm
            )
        }

        fun fromBcbChaveDetalheResponse(bcbResponse: BcbChaveDetalhesResponse): ChaveInfo{
            return ChaveInfo(
                tipoDeChave = BcbTipodeChave.toTipoDeChave(bcbResponse.keyType),
                chave = bcbResponse.key,
                tipoDeConta = BcbTipoDeConta.toTipoDeConta(bcbResponse.bankAccount.accountType),
                conta = DadosBancarios(
                    instituicao = Instituicoes.nome(bcbResponse.bankAccount.participant),
                    titular = bcbResponse.owner.name,
                    cpf = bcbResponse.owner.taxIdNumber,
                    agencia = bcbResponse.bankAccount.branch,
                    numero = bcbResponse.bankAccount.accountNumber
                )
            )
        }
    }

    fun toConsultaChavePixResponse(): ConsultaChaveResponse {
        return ConsultaChaveResponse.newBuilder()
            .setClientId(this.clienteId.toString())
            .setPixId(this.pixId.toString())
            .setChavePix(
                ConsultaChaveResponse.ChavePix.newBuilder()
                    .setTipo(br.com.zup.pix.TipoDeChave.valueOf(this.tipoDeChave.name))
                    .setChave(this.chave)
                    .setConta(ConsultaChaveResponse.ChavePix.DadosBancarios.newBuilder()
                        .setTipo(br.com.zup.pix.TipoDeConta.valueOf(this.tipoDeConta.name))
                        .setInstituicao(this.conta.instituicao)
                        .setTitular(this.conta.titular)
                        .setCpf(this.conta.cpf)
                        .setAgencia(this.conta.agencia)
                        .setNumeroConta(this.conta.numero)
                        .build()
                    )
                    .setCriadaEm(this.registradaEm.let {
                        val criadaEm = it.atZone(ZoneId.of("UTC")).toInstant()
                        Timestamp.newBuilder()
                            .setSeconds(criadaEm.epochSecond)
                            .setNanos(criadaEm.nano)
                            .build()
                    })
                    .build()
            )
            .build()

    }

}
