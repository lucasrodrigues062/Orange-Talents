package br.com.zup.casadocodigo.casadocodigo.form;

import br.com.zup.casadocodigo.casadocodigo.entidades.Categoria;

public class DetalheCategoriaForm {

    private String nome;

    public DetalheCategoriaForm(Categoria categoria){
        this.nome = categoria.getNome();
    }

    public String getNome() {
        return nome;
    }
}
