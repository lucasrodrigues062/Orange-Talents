package br.com.zup.casadocodigo.casadocodigo.dto;

import br.com.zup.casadocodigo.casadocodigo.entidades.Categoria;
import br.com.zup.casadocodigo.casadocodigo.validacao.UniqueValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CategoriaDto {
    @NotNull
    @NotBlank @UniqueValue( domainClass = Categoria.class, fieldName = "nome", message = "Ja existe categoria com esse nome")
    private String nome;

    public CategoriaDto(Categoria categoria){
        this.nome = categoria.getNome();
    }
    @Deprecated
    public CategoriaDto(){};

    public Categoria toCategoria(){
        return new Categoria(this.nome);
    }

    public String getNome() {
        return nome;
    }
}
