package br.com.zup.criacaodeproposta.bloqueiacartao;

import br.com.zup.criacaodeproposta.cadastracartao.Cartao;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BloqueioCartao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataBloqueio;
    private String ipRequisicao;
    private String userAgent;
    @ManyToOne(cascade = CascadeType.MERGE) @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    public BloqueioCartao(String ipRequisicao, String userAgent, Cartao cartao) {
        this.dataBloqueio = LocalDateTime.now();
        this.ipRequisicao = ipRequisicao;
        this.userAgent = userAgent;
        this.cartao = cartao;
        cartao.atualizaStatusCartao(StatusCartao.AGUARDANDO_BLOQUEIO);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataBloqueio() {
        return dataBloqueio;
    }

    public String getIpRequisicao() {
        return ipRequisicao;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
