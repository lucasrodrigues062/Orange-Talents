package br.com.zup.mercadolivre.opiniao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

public class OpiniaoDto {
	@NotNull @Min(1) @Max(5)
	private Integer nota;
	@NotBlank @NotNull
	private String titulo;
	@NotBlank @NotNull @Size(max=500)
	private String descricao;
	
	private Long idUsuario;
	private Long idProduto;


	public OpiniaoDto(@NotNull @Size(min = 1, max = 5) Integer nota, @NotBlank @NotNull String titulo,
			@NotBlank @NotNull @Size(max = 500) String descricao) {
		
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;

	}

	private OpiniaoDto() {
		
	}
	
	public Integer getNota() {
		return nota;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	
	
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public OpiniaoProduto toOpiniaoProduto(Produto produto, Usuario usuario) {
		return new OpiniaoProduto(this.nota, this.titulo, this.descricao, usuario, produto);
	}
	
	public OpiniaoDto (OpiniaoProduto op) {
		this.nota = op.getNota();
		this.titulo = op.getTitulo();
		this.descricao = op.getDescricao();
		this.idUsuario = op.getUsuario().getId();
		this.idProduto = op.getProduto().getId();
	}
}
