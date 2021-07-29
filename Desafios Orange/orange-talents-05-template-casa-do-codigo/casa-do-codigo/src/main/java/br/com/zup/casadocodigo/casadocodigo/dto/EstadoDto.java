package br.com.zup.casadocodigo.casadocodigo.dto;

import br.com.zup.casadocodigo.casadocodigo.entidades.Estado;
import br.com.zup.casadocodigo.casadocodigo.entidades.Pais;
import br.com.zup.casadocodigo.casadocodigo.validacao.ExistsId;
import br.com.zup.casadocodigo.casadocodigo.validacao.UniqueValue;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class EstadoDto {

    @NotNull @NotBlank
    @UniqueValue(domainClass = Estado.class, fieldName = "nome")
    private String nome;
    @NotNull @ExistsId(domainClass = Pais.class, fieldName = "id")
    private Long idPais;

    public EstadoDto(String nome, Long idPais) {
        this.nome = nome;
        this.idPais = idPais;
    }

    public EstadoDto(Estado estado) {
        this.nome = estado.getNome();
        this.idPais = estado.getPais().getId();
    }

    public Estado toEstado(EntityManager manager){
        Pais pais = manager.find(Pais.class, this.idPais);
        return new Estado(this.nome, pais);
    }

    private EstadoDto(){}

    public String getNome() {
        return nome;
    }

    public Long getIdPais() {
        return idPais;
    }
}
