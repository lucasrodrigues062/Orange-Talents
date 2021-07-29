package br.com.zup.criacaodeproposta.avisodeviagem;

import br.com.zup.criacaodeproposta.cadastracartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
public class AvisoViagem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @JoinColumn(name = "cartao_id")
    private Cartao cartao;
    private String destino;
    private LocalDateTime dataAviso;
    private LocalDate dataTermino;
    private String ipAddr;
    private String userAgent;

    public AvisoViagem(Cartao cartao, @NotBlank @NotNull String destino, @Future @NotNull @NotBlank LocalDate dataTermino, String ipAddr, String userAgent) {
        this.cartao = cartao;
        this.destino = destino;
        this.dataTermino = dataTermino;
        this.ipAddr = ipAddr;
        this.userAgent = userAgent;
        this.dataAviso = LocalDateTime.now();
    }
    @Deprecated
    public AvisoViagem(){}

    public Long getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDateTime getDataAviso() {
        return dataAviso;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
