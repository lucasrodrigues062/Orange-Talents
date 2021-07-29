package br.com.zup.casadocodigo.casadocodigo.validacao;

import br.com.zup.casadocodigo.casadocodigo.dto.AutorDto;
import br.com.zup.casadocodigo.casadocodigo.entidades.Autor;
import br.com.zup.casadocodigo.casadocodigo.repositorios.AutorRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;


@Component
public class EmailDuplicadoValidator implements Validator {

    private AutorRepository autorRepository;
    public EmailDuplicadoValidator(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AutorDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        AutorDto request = (AutorDto) o;
        Optional<Autor> optional = autorRepository.findByEmail(request.getEmail());
        if(optional.isPresent()){
            errors.rejectValue("email", null, "O email informado já se encontra no nosso cadastro: " + request.getEmail());
        }
    }
}
