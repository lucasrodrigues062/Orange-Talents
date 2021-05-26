package br.com.zup.mercadolivre.validacao;

import java.util.HashSet;
import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zup.mercadolivre.produto.CaracteristicasDTO;
import br.com.zup.mercadolivre.produto.ProdutoDto;

public class CaracteristicaDuplicadaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return ProdutoDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	
		if(errors.hasErrors()) {
			return;
		}
		
		ProdutoDto produto = (ProdutoDto) target;
		
		Set<CaracteristicasDTO> caracteristicas = new HashSet<CaracteristicasDTO>();
		
		caracteristicas.addAll(produto.getCaracteristicas());
		
		if(produto.getCaracteristicas().size() > caracteristicas.size()) {
			errors.rejectValue("caracteristicas", null, "Existem Caracteristicas duplicadas");
		}
		

		
	}

}
