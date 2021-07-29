package br.com.zup.pix.detalha

import br.com.zup.grpc.TipoDeConta
import br.com.zup.pix.ConsultaChaveResponse
import br.com.zup.pix.TipoDeChave
import com.fasterxml.jackson.annotation.JsonFormat
import io.micronaut.core.annotation.Introspected
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Introspected
class DetalheChaveResponse(responseChave: ConsultaChaveResponse) {

    val pixId = responseChave.pixId
    val tipoDeChave = TipoDeChave.valueOf(responseChave.chavePix.tipo.name)
    val chave = responseChave.chavePix.chave
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val criadaEm = responseChave.chavePix.criadaEm.let {
        LocalDateTime.ofInstant(Instant.ofEpochSecond(it.seconds, it.nanos.toLong()), ZoneOffset.UTC)
    }
    val dadosBancarios = DadosBancariosResponse(responseChave.chavePix.conta)


}

class DadosBancariosResponse(dados: ConsultaChaveResponse.ChavePix.DadosBancarios){
    val tipoDeConta = TipoDeConta.valueOf(dados.tipo.name)
    val instituicao = dados.instituicao
    val titular = dados.titular
    val cpf = dados.cpf
    val agencia = dados.agencia
    val conta = dados.numeroConta
}