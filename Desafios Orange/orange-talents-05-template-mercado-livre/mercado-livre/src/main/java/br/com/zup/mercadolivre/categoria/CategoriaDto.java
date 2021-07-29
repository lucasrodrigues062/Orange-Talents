package br.com.zup.mercadolivre.categoria;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CategoriaDto {

	
	private Long idCategoriaMae;
	@NotNull @NotBlank 
	private String nome;

	private CategoriaDto(){}

	public CategoriaDto(Categoria categoria){

		this.nome = categoria.getNome();
		if(categoria.getCategoriaMae() != null) {
			this.idCategoriaMae = categoria.getCategoriaMae().getId();
		}

	}

	public CategoriaDto(Long idCategoriaMae, String nome) {
		this.idCategoriaMae = idCategoriaMae;
		this.nome = nome;
	}

	public Long getIdCategoriaMae() {
		return idCategoriaMae;
	}

	public String getNome() {
		return nome;
	}


	public Categoria toCategoria(EntityManager manager) {
		if(this.idCategoriaMae == null) {
			return new Categoria(this.nome, null);
		}

		Categoria mae = manager.find(Categoria.class, this.idCategoriaMae);

		return new Categoria(this.nome, mae);
	}


}
