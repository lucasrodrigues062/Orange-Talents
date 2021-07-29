package br.com.zup.casadocodigo.casadocodigo.form;

import br.com.zup.casadocodigo.casadocodigo.entidades.Autor;
import br.com.zup.casadocodigo.casadocodigo.entidades.Categoria;
import br.com.zup.casadocodigo.casadocodigo.entidades.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DetalheLivroForm {

    private String titulo;
    private String isbn;
    private Integer numeroDePaginas;
    private BigDecimal preco;
    private String resumo;
    private String sumario;
    private LocalDate dataPublicacao;
    private DetalheAutorForm detalheAutorForm;
    private DetalheCategoriaForm detalheCategoriaForm;

    public DetalheLivroForm (Livro livro, Categoria categoria, Autor autor){
        this.titulo = livro.getTitulo();
        this.isbn = livro.getIsbn();
        this.numeroDePaginas = livro.getNumeroDePaginas();
        this.preco = livro.getPreco();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.dataPublicacao = livro.getDataPublicacao();
        this.detalheAutorForm = new DetalheAutorForm(autor);
        this.detalheCategoriaForm = new DetalheCategoriaForm(categoria);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public DetalheAutorForm getDetalheAutorForm() {
        return detalheAutorForm;
    }

    public DetalheCategoriaForm getDetalheCategoriaForm() {
        return detalheCategoriaForm;
    }
}
