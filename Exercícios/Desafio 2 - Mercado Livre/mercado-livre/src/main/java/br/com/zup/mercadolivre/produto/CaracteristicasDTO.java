package br.com.zup.mercadolivre.produto;

import javax.validation.constraints.NotBlank;

public class CaracteristicasDTO {
	
	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;

	public CaracteristicasDTO(CaracteristicasProduto caracteristicas) {
		this.descricao = caracteristicas.getDescricao();
		this.nome = caracteristicas.getNome();
	}
	public CaracteristicasDTO(@NotBlank String nome, @NotBlank String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}
	public String getNome() {
		return nome;
	}
	public String getDescricao() {
		return descricao;
	}
	@Override
	public String toString() {
		return "CaracteristicasDTO [nome=" + nome + ", descricao=" + descricao + "]";
	}
	
	@SuppressWarnings("unused")
	private CaracteristicasDTO() {}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaracteristicasDTO other = (CaracteristicasDTO) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	public CaracteristicasProduto toCaracteristicasProduto(Produto produto) {
		return new CaracteristicasProduto(nome, descricao, produto);
	}
	
	

}
