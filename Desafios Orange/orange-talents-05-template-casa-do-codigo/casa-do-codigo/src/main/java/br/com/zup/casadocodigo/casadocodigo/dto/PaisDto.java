package br.com.zup.casadocodigo.casadocodigo.dto;

import br.com.zup.casadocodigo.casadocodigo.entidades.Pais;
import br.com.zup.casadocodigo.casadocodigo.validacao.UniqueValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PaisDto {
    @NotNull @NotBlank
    @UniqueValue(domainClass = Pais.class, fieldName = "nome")
    private String nome;


    public Pais toPais(){
        return new Pais(this.nome);
    }

    public PaisDto(Pais pais){
        this.nome = pais.getNome();
    }

    public PaisDto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    private PaisDto(){};
}
