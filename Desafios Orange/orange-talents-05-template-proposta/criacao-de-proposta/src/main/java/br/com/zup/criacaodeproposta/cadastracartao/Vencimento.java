package br.com.zup.criacaodeproposta.cadastracartao;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Vencimento {

    @Id
    private String id;
    private Integer dia;
    private LocalDateTime dataDeCriacao;
 
    

    private Vencimento(){}


    public Vencimento(String id, Integer dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }


    public String getId() {
        return this.id;
    }

    public Integer getDia() {
        return this.dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return this.dataDeCriacao;
    }
    

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", dia='" + getDia() + "'" +
            ", dataDeCriacao='" + getDataDeCriacao() + "'" +
            "}";
    }



}
