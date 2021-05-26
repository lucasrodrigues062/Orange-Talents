package br.com.zup.mercadolivre.novaCompra.retornoCompra;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.zup.mercadolivre.novaCompra.Compra;
import br.com.zup.mercadolivre.novaCompra.Gateway;

@Entity
public class Pagamento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String idTransacao;
    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private Gateway gateway;
    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;
    
    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;
    private LocalDateTime dataTransacao;

    private Pagamento(){}
    public Pagamento(String idTransacao, Gateway gateway, StatusPagamento statusPagamento,  Compra compra) {
        
        this.idTransacao = idTransacao;
        this.gateway = gateway;
        this.statusPagamento = statusPagamento;
        this.compra = compra;
        this.dataTransacao = LocalDateTime.now();
    }


    public Long getId() {
        return this.id;
    }

    public String getIdTransacao() {
        return this.idTransacao;
    }

    public Gateway getGateway() {
        return this.gateway;
    }

    public StatusPagamento getStatusPagamento() {
        return this.statusPagamento;
    }

    public Compra getCompra() {
        return this.compra;
    }

    public LocalDateTime getDataTransacao() {
        return this.dataTransacao;
    }



    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", idTransacao='" + getIdTransacao() + "'" +
            ", gateway='" + getGateway() + "'" +
            ", statusPagamento='" + getStatusPagamento() + "'" +
            ", compra='" + getCompra() + "'" +
            ", dataTransacao='" + getDataTransacao() + "'" +
            "}";
    }
    
}
