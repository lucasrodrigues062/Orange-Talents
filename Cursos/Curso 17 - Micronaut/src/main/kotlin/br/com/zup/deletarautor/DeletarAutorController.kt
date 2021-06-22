package br.com.zup.deletarautor

import br.com.zup.novoautor.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable

@Controller("/autores/{id}")
class DeletarAutorController(val autorRepository: AutorRepository) {

    @Delete
    fun excluiAutor(@PathVariable id: Long) : HttpResponse<Any> {
        val optionalAutor = autorRepository.findById(id)
        if(optionalAutor.isEmpty){
            return HttpResponse.notFound()
        }
        autorRepository.delete(optionalAutor.get())
        return HttpResponse.ok()
    }
}