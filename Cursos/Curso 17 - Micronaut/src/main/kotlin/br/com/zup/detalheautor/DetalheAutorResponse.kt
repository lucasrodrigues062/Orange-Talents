package br.com.zup.detalheautor

import br.com.zup.novoautor.Autor
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

class DetalheAutorResponse (autor: Autor) {

    val idAutor = autor.id
    val nome = autor.nome
    val email = autor.email
    val descricao = autor.descricao
    @JsonFormat(pattern = "dd/MM/yyyy")
    val criadoEm: LocalDateTime = autor.criadoEm

}
