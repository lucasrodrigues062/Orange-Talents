package br.com.zup.casadocodigo.casadocodigo.entidades;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"pais_id","nome"}))
public class Estado {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "pais_id")
    private Pais pais;

    private Estado(){};

    public Estado(String nome, Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }

    public Pais getPais() {
        return pais;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }


}
