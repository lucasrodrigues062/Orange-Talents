package br.com.zup.pix.cadastra


import br.com.zup.grpc.TipoDeChave
import br.com.zup.grpc.TipoDeConta
import br.com.zup.grpc.ValidaChavePix
import br.com.zup.pix.ChavePixRequest
import io.micronaut.core.annotation.Introspected
import java.util.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Introspected
@ValidaChavePix
class ChavePixDto (
    @field:NotNull
    val tipoDeConta: TipoDeConta,
    @field:Size(max = 77)
    val chave: String?,
    val tipoDeChave: TipoDeChave

        ){

    fun toGrpcModel(clientId: UUID): ChavePixRequest{
        return ChavePixRequest.newBuilder()
            .setCodigoInterno(clientId.toString())
            .setTipoDeConta(br.com.zup.pix.TipoDeConta.valueOf(tipoDeConta.name))
            .setTipoDeChave(br.com.zup.pix.TipoDeChave.valueOf(tipoDeChave.name))
            .setValorDaChave(chave ?: "")
            .build()
    }

    override fun toString(): String {
        return "ChavePixDto(tipoDeConta=$tipoDeConta, chave=$chave, tipoDeChave=$tipoDeChave)"
    }


}