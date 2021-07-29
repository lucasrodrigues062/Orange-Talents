package br.com.zup.mercadolivre.pergunta;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

@Entity
public class PerguntaProduto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String titulo;
	private LocalDateTime dataPergunta = LocalDateTime.now();
	@ManyToOne @NotNull
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	@ManyToOne @NotNull
	@JoinColumn(name = "produto_id")
	private Produto produto;
	public Long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public LocalDateTime getDataPergunta() {
		return dataPergunta;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public Produto getProduto() {
		return produto;
	}
	
	public PerguntaProduto(String titulo, @NotNull Usuario usuario, @NotNull Produto produto) {
		
		this.titulo = titulo;
		this.usuario = usuario;
		this.produto = produto;
	}
	
	private PerguntaProduto(){}
	
	
}
