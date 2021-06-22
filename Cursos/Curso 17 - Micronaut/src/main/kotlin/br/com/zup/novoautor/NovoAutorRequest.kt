package br.com.zup.novoautor

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Introspected
data class NovoAutorRequest(
    @field:NotBlank val nome: String,
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank @field:Size(max = 400) @field:NotNull val descricao: String,
    val cep: String,
    val numero: String
) {

    fun toModel(): Autor {
        return Autor(nome, email, descricao)
    }
}
