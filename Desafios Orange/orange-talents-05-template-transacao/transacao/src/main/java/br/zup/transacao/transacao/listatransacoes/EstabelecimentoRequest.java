package br.zup.transacao.transacao.listatransacoes;

import br.zup.transacao.transacao.model.Estabelecimento;

public class EstabelecimentoRequest {
    private String nome;
    private String cidade;
    private String endereco;

    public EstabelecimentoRequest(Estabelecimento estabelecimento){
        this.cidade = estabelecimento.getCidade();
        this.nome = estabelecimento.getNome();
        this.endereco =estabelecimento.getEndereco();
    }
    private EstabelecimentoRequest(){}

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
