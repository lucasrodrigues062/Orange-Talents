package br.com.zup.mercadolivre.validacao;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.categoria.CategoriaDto;
import br.com.zup.mercadolivre.categoria.CategoriaRepository;


public class CategoriaMaeValidator implements Validator {

	private CategoriaRepository repository;

	public CategoriaMaeValidator(CategoriaRepository repository) {
		this.repository = repository;
	}


	@Override
	public boolean supports(Class<?> clazz) {
		return CategoriaDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}

		CategoriaDto categoriaDto = (CategoriaDto) target;

		if(categoriaDto.getIdCategoriaMae() != null) {
			Optional<Categoria> categoria = repository.findByNomeAndCategoriaMaeId(categoriaDto.getNome(), categoriaDto.getIdCategoriaMae());						
			if(categoria.isPresent()) {
				errors.rejectValue("idCategoriaMae", null, "Ja existe categoria com esse nome para essa categoria Mae");
			} 

			Optional<Categoria> mae = repository.findById(categoriaDto.getIdCategoriaMae());
			if(!mae.isPresent()) {
				errors.rejectValue("idCategoriaMae", null, "Id Inexistente");
			}
			
			
		} 
		else {

			Optional<Categoria> categoriaSemMae = repository.findByNomeAndCategoriaMaeIsNull(categoriaDto.getNome());
			
			if(categoriaSemMae.isPresent()) {
				
				errors.rejectValue("idCategoriaMae", null, "Ja existe categoria com esse nome");
			}

		}

	}



}

