package br.com.zup.detalheautor

import br.com.zup.novoautor.AutorRepository
import io.micronaut.http.HttpResponse


import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue


@Controller("/autores")
class DetalheAutorController (val autorRepository: AutorRepository){

    @Get
    fun lista(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {
        if(email.isBlank()){
            val autores = autorRepository.findAll();

            val autoresResponse = autores.map { autor -> DetalheAutorResponse(autor) }

            return HttpResponse.ok(autoresResponse)
        }

        val optionalAutor = autorRepository.findByEmail(email)

        if(optionalAutor.isEmpty){
            return HttpResponse.notFound()
        }

        return HttpResponse.ok(DetalheAutorResponse(optionalAutor.get()))
    }
}