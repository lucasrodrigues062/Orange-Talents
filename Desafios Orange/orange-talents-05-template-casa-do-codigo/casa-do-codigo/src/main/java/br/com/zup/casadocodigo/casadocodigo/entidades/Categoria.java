package br.com.zup.casadocodigo.casadocodigo.entidades;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Categoria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    private final LocalDateTime dataCriacao = LocalDateTime.now();

    @Deprecated
    private Categoria(){}

    public Categoria(String nome){
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }
    @Column(unique = true, nullable = false)
    public String getNome() {
        return nome;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
