package br.zup.transacao.transacao.kafka;

import br.zup.transacao.transacao.model.Transacao;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EventoTransacao {

    private String id;
    private BigDecimal valor;
    private EstabelecimentoDto estabelecimento;
    @JsonAlias("cartao")
    private CartaoDto cartao;
    private LocalDateTime efetivadaEm;

    public Transacao toModel(){
        return new Transacao(this.id, this.valor, this.estabelecimento.toModel(), this.cartao.toModel(), this.efetivadaEm);
    }
    @Override
    public String toString() {
        return "EventoTransacao{" +
                "id='" + id + '\'' +
                ", valor=" + valor +
                ", estabelecimento=" + estabelecimento +
                ", cartao=" + cartao +
                ", efetivadaEm=" + efetivadaEm +
                '}';
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public EstabelecimentoDto getEstabelecimento() {
        return estabelecimento;
    }

    public CartaoDto getCartao() {
        return cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    public EventoTransacao() {
    }

    public EventoTransacao(String id, BigDecimal valor, EstabelecimentoDto estabelecimento, CartaoDto cartaoDto, LocalDateTime efetivadaEm) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartaoDto;
        this.efetivadaEm = efetivadaEm;
    }

}
