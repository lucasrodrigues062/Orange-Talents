package br.com.zup.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.categoria.CategoriaRepository;
import br.com.zup.mercadolivre.usuario.Usuario;
import br.com.zup.mercadolivre.validacao.ExistsId;

public class ProdutoDto {
	
	@NotBlank
	private String nome;
	@Positive
	private BigDecimal valor;
	@PositiveOrZero
	private BigDecimal qtd;
	@Size(min=3) @Valid
	private List<CaracteristicasDTO> caracteristicas = new ArrayList<>();
	
	@Length(max = 1000, min = 1)
	private String descricao;
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoria;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataCadastro;
	
	
	
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public ProdutoDto(@NotBlank String nome, @Positive BigDecimal valor, @PositiveOrZero BigDecimal qtd,
			@Size(min = 3) @Valid Set<CaracteristicasDTO> caracteristicas,
			@Length(max = 1000, min = 1) String descricao, Long idCategoria) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.qtd = qtd;
		this.caracteristicas.addAll(caracteristicas);
		this.descricao = descricao;
		this.idCategoria = idCategoria;
	}

	public Produto toProduto(CategoriaRepository repository, Usuario usuario) {
		Categoria categoria = repository.findById(idCategoria).get();
							
		return new Produto(nome, valor, qtd, descricao, categoria, caracteristicas, usuario);
	}
	
	@SuppressWarnings("unused")
	private ProdutoDto (){};
	
	
	public ProdutoDto(Produto produto) {
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.qtd = produto.getQtd();
		this.descricao = produto.getDescricao();
		this.idCategoria = produto.getCategoria().getId();
		produto.getCaracteristicas().forEach(c -> this.caracteristicas.add(new CaracteristicasDTO(c)));
		this.dataCadastro = produto.getDataCadastro();
	}

	public String getNome() {
		return nome;
	}




	public BigDecimal getValor() {
		return valor;
	}




	public BigDecimal getQtd() {
		return qtd;
	}




	public String getDescricao() {
		return descricao;
	}




	public Long getIdCategoria() {
		return idCategoria;
	}

	
	public List<CaracteristicasDTO> getCaracteristicas() {
		return caracteristicas;
	}

	@Override
	public String toString() {
		return "ProdutoDto [nome=" + nome + ", valor=" + valor + ", qtd=" + qtd + ", caracteristicas=" + caracteristicas
				+ ", descricao=" + descricao + ", idCategoria=" + idCategoria + "]";
	}
	
	
	
	
}
