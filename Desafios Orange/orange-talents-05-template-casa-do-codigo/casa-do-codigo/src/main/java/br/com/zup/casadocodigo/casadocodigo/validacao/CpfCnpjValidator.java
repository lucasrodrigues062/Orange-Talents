package br.com.zup.casadocodigo.casadocodigo.validacao;

import br.com.zup.casadocodigo.casadocodigo.dto.ClienteDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CpfCnpjValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ClienteDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        ClienteDto clienteDto = (ClienteDto) o;
        if(!clienteDto.CpfCnpjValidator()){
            errors.rejectValue("documento", null, "Documento em formato invalido");
        }
    }
}
