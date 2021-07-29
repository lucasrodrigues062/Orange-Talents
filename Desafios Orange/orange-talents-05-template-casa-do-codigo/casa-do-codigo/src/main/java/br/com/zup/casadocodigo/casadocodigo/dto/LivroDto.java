package br.com.zup.casadocodigo.casadocodigo.dto;

import br.com.zup.casadocodigo.casadocodigo.entidades.Autor;
import br.com.zup.casadocodigo.casadocodigo.entidades.Categoria;
import br.com.zup.casadocodigo.casadocodigo.entidades.Livro;
import br.com.zup.casadocodigo.casadocodigo.validacao.ExistsId;
import br.com.zup.casadocodigo.casadocodigo.validacao.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LivroDto {
    @NotNull @NotBlank @UniqueValue(domainClass = Livro.class, fieldName = "titulo")
    private String titulo;
    @NotBlank @NotNull @Length(max = 500)
    private String resumo;
    private String sumario;
    @DecimalMin(value = "20.0", inclusive = false)
    private BigDecimal preco;
    @Min(value = 100)
    private Integer numeroDePaginas;
    @NotNull @NotBlank @UniqueValue(domainClass = Livro.class, fieldName = "isbn")
    private String isbn;
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataPublicacao;
    @NotNull @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;
    @NotNull @ExistsId(domainClass = Autor.class, fieldName = "id")
    private Long idAutor;

    public  Livro toLivro(EntityManager manager) {
        Autor autor = manager.find(Autor.class, this.idAutor);
        Categoria categoria = manager.find(Categoria.class, this.idCategoria);

        return new Livro(this.titulo, this.resumo, this.sumario, this.preco, this.numeroDePaginas,this.isbn,this.dataPublicacao,categoria,autor);
    }

    public LivroDto(Livro livro){
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.preco = livro.getPreco();
        this.numeroDePaginas = livro.getNumeroDePaginas();
        this.isbn = livro.getIsbn();
        this.dataPublicacao = livro.getDataPublicacao();
        this.idAutor = livro.getAutor().getId();
        this.idCategoria = livro.getCategoria().getId();


    }

    public LivroDto(String titulo, String resumo, String sumario, BigDecimal preco, Integer numeroDePaginas, String isbn, LocalDate dataPublicacao, Long idCategoria, Long idAutor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroDePaginas = numeroDePaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.idCategoria = idCategoria;
        this.idAutor = idAutor;
    }

    private LivroDto() {
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public Long getIdAutor() {
        return idAutor;
    }
}
