package br.com.zup.casadocodigo.casadocodigo.validacao;

import br.com.zup.casadocodigo.casadocodigo.dto.AutorDto;
import br.com.zup.casadocodigo.casadocodigo.dto.CategoriaDto;
import br.com.zup.casadocodigo.casadocodigo.entidades.Autor;
import br.com.zup.casadocodigo.casadocodigo.entidades.Categoria;
import br.com.zup.casadocodigo.casadocodigo.repositorios.CategoriaRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class NomeDuplicadoValidator implements Validator {

    private CategoriaRepository categoriaRepository;
    public NomeDuplicadoValidator(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoriaDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        CategoriaDto request = (CategoriaDto) o;
        Optional<Categoria> optional = categoriaRepository.findByNome(request.getNome());
        if(optional.isPresent()){
            errors.rejectValue("nome", null, "O nome informado já se encontra no nosso cadastro: " + request.getNome());
        }
    }
}
