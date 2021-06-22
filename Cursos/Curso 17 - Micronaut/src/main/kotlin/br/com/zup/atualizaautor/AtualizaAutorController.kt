package br.com.zup.atualizaautor

import br.com.zup.detalheautor.DetalheAutorResponse
import br.com.zup.novoautor.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put

@Controller("/autores/{id}")
class AtualizaAutorController(val autorRepository: AutorRepository) {
    @Put
    fun atualizaAutor(@PathVariable id:Long, descricao: String): HttpResponse<Any>{
        val optionalAutor = autorRepository.findById(id)
        if(optionalAutor.isEmpty){
            return HttpResponse.notFound()
        }
        val autor = optionalAutor.get()
        autor.descricao = descricao
        autorRepository.update(autor)
        return HttpResponse.ok(DetalheAutorResponse(autor))


    }
}