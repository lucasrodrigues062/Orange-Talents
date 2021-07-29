package br.com.zup.mercadolivre.opiniao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

@Entity
public class OpiniaoProduto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Integer nota;
	@Column(nullable = false)
	private String titulo;
	@Column(nullable = false, length = 500)
	private String Descricao;
	@NotNull 
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	@NotNull 
	@ManyToOne
	@JoinColumn(name="produto_id")
	private Produto produto;
	
	private OpiniaoProduto() {}

	public OpiniaoProduto(Integer nota, String titulo, String descricao, @NotNull Usuario usuario,
			@NotNull Produto produto) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		Descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public Integer getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return Descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}
	
}
