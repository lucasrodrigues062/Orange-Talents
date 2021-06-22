package br.com.zup.novoautor

import br.com.zup.detalheautor.DetalheAutorResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.created
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated

import javax.validation.Valid

@Validated
@Controller("/novoautor")
class CadastraAutorController(val autorRepository: AutorRepository) {

    @Post
    fun cadastra(@Body @Valid request: NovoAutorRequest): HttpResponse<Any> {
        val autor = autorRepository.save(request.toModel())

        println("$autor   autor id= ${autor.id}")
        val uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf(Pair("id", autor.id)))

        return HttpResponse.created<Any?>(uri).body(DetalheAutorResponse(autor))
    }
}