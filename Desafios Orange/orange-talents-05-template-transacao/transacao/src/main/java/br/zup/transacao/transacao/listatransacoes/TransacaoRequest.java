package br.zup.transacao.transacao.listatransacoes;

import br.zup.transacao.transacao.model.Estabelecimento;
import br.zup.transacao.transacao.model.Transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoRequest {

    private BigDecimal valor;
    private EstabelecimentoRequest estabelecimento;
    private CartaoRequest cartao;
    private LocalDateTime efetivadaEm;

    public TransacaoRequest(Transacao transacao) {
        this.valor = transacao.getValor();
        this.estabelecimento = new EstabelecimentoRequest(transacao.getEstabelecimento());
        this.cartao = new CartaoRequest(transacao.getCartao());
        this.efetivadaEm = transacao.getEfetivadaEm();

    }

    public BigDecimal getValor() {
        return valor;
    }

    public EstabelecimentoRequest getEstabelecimento() {
        return estabelecimento;
    }

    public CartaoRequest getCartao() {
        return cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }
}
