package br.com.zup.casadocodigo.casadocodigo.form;

import br.com.zup.casadocodigo.casadocodigo.entidades.Autor;

public class DetalheAutorForm {

    private String nome;
    private String descricao;

    public DetalheAutorForm(Autor autor){
        this.nome = autor.getNome();
        this.descricao = autor.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
