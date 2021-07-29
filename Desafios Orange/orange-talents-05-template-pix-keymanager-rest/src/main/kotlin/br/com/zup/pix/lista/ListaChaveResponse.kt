package br.com.zup.pix.lista

import br.com.zup.grpc.TipoDeChave
import br.com.zup.grpc.TipoDeConta
import br.com.zup.pix.ListaChaveResponse
import com.fasterxml.jackson.annotation.JsonFormat
import io.micronaut.core.annotation.Introspected
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Introspected
class ListaChaveResponse (chave: ListaChaveResponse.ChavePix){

    val id = chave.pixId
    val chave = chave.chave
    val tipoDeConta = TipoDeConta.valueOf(chave.tipoDeConta.name)
    val tipoDeChave = TipoDeChave.valueOf(chave.tipo.name)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val criadaEm = chave.criadaEm.let {
        LocalDateTime.ofInstant(Instant.ofEpochSecond(it.seconds, it.nanos.toLong()), ZoneOffset.UTC)
    }

}