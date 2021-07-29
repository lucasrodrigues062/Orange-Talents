package br.zup.transacao.transacao.listatransacoes;

import br.zup.transacao.transacao.model.Cartao;

public class CartaoRequest {
    private String id;
    private String email;

    public CartaoRequest(Cartao cartao) {
        this.id = cartao.getId();
        this.email = cartao.getEmail();
    }
    public CartaoRequest(){}

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
