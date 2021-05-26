package br.com.zup.mercadolivre.pergunta;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

public class PerguntaDto {
	
	@NotNull @NotBlank
	private String titulo;
	private LocalDateTime dataPergunta;
	private Long idUsuario;
	private Long idProduto;
	
	private PerguntaDto() {}

	public PerguntaDto(@NotNull @NotBlank String titulo, LocalDateTime dataPergunta, @NotNull Long idUsuario, @NotNull Long idProduto) {
		super();
		this.titulo = titulo;
		this.dataPergunta = dataPergunta;
		this.idUsuario = idUsuario;
		this.idProduto = idProduto;
	}

	public String getTitulo() {
		return titulo;
	}

	public LocalDateTime getDataPergunta() {
		return dataPergunta;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public Long getIdProduto() {
		return idProduto;
	}
	
	public PerguntaProduto toPergunta(Usuario usuario, Produto produto) {
		
		return new PerguntaProduto(this.titulo, usuario, produto);
	}
	
	public PerguntaDto (PerguntaProduto pergunta) {
		this.titulo = pergunta.getTitulo();
		this.dataPergunta = pergunta.getDataPergunta();
		this.idUsuario = pergunta.getUsuario().getId();
		this.idProduto = pergunta.getProduto().getId();
	}

}
