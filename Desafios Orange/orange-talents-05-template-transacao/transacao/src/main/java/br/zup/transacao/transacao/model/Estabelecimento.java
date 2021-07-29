package br.zup.transacao.transacao.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Estabelecimento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cidade;
    private String endereco;
    @OneToMany(mappedBy = "estabelecimento")
    private List<Transacao> transacoes;

    public Long getId() {
        return id;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public Estabelecimento(String nome, String cidade, String endereco) {
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
    }

    public Estabelecimento() {
    }

    @Override
    public String toString() {
        return "Estabelecimento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cidade='" + cidade + '\'' +
                ", endereco='" + endereco + '\'' +
                ", transacoes=" + transacoes +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEndereco() {
        return endereco;
    }
}
