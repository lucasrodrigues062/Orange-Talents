package br.zup.transacao.transacao.kafka;

import br.zup.transacao.transacao.model.Estabelecimento;

public class EstabelecimentoDto {
    private String nome;
    private String cidade;
    private String endereco;

    public Estabelecimento toModel() {
        return new Estabelecimento(this.nome,this.cidade,this.endereco);
    }

    public EstabelecimentoDto(String nome, String cidade, String endereco) {
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
    }


    public EstabelecimentoDto() {
    }

    @Override
    public String toString() {
        return "Estabelecimento{" +
                "nome='" + nome + '\'' +
                ", cidade='" + cidade + '\'' +
                ", endereco='" + endereco + '\'' +
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
