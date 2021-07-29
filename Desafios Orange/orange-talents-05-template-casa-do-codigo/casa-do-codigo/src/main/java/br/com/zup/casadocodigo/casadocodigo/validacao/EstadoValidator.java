package br.com.zup.casadocodigo.casadocodigo.validacao;

import br.com.zup.casadocodigo.casadocodigo.dto.ClienteDto;
import br.com.zup.casadocodigo.casadocodigo.entidades.Estado;
import br.com.zup.casadocodigo.casadocodigo.entidades.Pais;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class EstadoValidator implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> aClass) {
        return ClienteDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors()){
            return;
        }

        ClienteDto clienteDto = (ClienteDto) o;
        Pais pais = manager.find(Pais.class, clienteDto.getIdPais());
        Estado estado = manager.find(Estado.class, clienteDto.getIdEstado());



        if(estado.getPais().getId() != pais.getId()){
            errors.rejectValue("idEstado", null, "Estado nao pertence ao pais selecionado");

        }
    }
}
