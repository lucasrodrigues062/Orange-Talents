package br.com.zup.casadocodigo.casadocodigo.entidades;

import br.com.zup.casadocodigo.casadocodigo.validacao.UniqueValue;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Livro {

    @Id @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @Column(nullable = false, length = 500)
    private String resumo;
    @Column(columnDefinition = "TEXT")
    private String sumario;
    @Column(nullable = false)
    private BigDecimal preco;
    private Integer numeroDePaginas;
    @Column(unique = true)
    private String isbn;
    private LocalDate dataPublicacao;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    private Livro(){}

    public Livro(@NotBlank @NotNull String titulo, @NotBlank @NotNull @Length(max = 500) String resumo, @NotBlank @NotNull BigDecimal preco, @NotBlank @NotNull Integer numeroDePaginas, @NotBlank @NotNull String isbn,
                 @NotBlank @NotNull Categoria categoria, @NotBlank @NotNull Autor autor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.preco = preco;
        this.numeroDePaginas = numeroDePaginas;
        this.isbn = isbn;
        this.categoria = categoria;
        this.autor = autor;
    }

    public Livro(@NotBlank @NotNull String titulo, @NotBlank @NotNull @Length(max = 500) String resumo, String sumario, @NotBlank @NotNull BigDecimal preco, @NotBlank @NotNull Integer numeroDePaginas, @NotBlank @NotNull String isbn,
                 LocalDate dataPublicacao, @NotBlank @NotNull Categoria categoria, @NotBlank @NotNull Autor autor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroDePaginas = numeroDePaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
        this.autor = autor;
    }

    public void setSumario(String sumario) {
        this.sumario = sumario;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Long getId() {
        return id;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public Autor getAutor() {
        return autor;
    }
}
