package br.com.zup.criacaodeproposta.cadastracartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.*;

import br.com.zup.criacaodeproposta.bloqueiacartao.StatusCartao;
import br.com.zup.criacaodeproposta.novaproposta.Proposta;


@Entity
public class Cartao {

    @Id @Column(unique = true)
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private BigDecimal limite;
    @OneToOne @JoinColumn(name = "proposta_id")
    private Proposta proposta;
    @OneToOne(cascade = CascadeType.MERGE)    
    private Vencimento vencimento;
    @Enumerated(EnumType.STRING)
    private StatusCartao statusCartao;

    public void atualizaStatusCartao(StatusCartao statusCartao){
        this.statusCartao = statusCartao;
    }
    private Cartao(){}

    public StatusCartao getStatusCartao() {
        return statusCartao;
    }

    public Cartao(String id, LocalDateTime emitidoEm, String titular, BigDecimal limite, Vencimento vencimento, Proposta proposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.vencimento = vencimento;
        this.proposta = proposta;
        this.statusCartao = StatusCartao.AUTORIZADO;
    }


    public String getId() {
        return this.id;
    }

    public LocalDateTime getEmitidoEm() {
        return this.emitidoEm;
    }

    public String getTitular() {
        return this.titular;
    }

    public BigDecimal getLimite() {
        return this.limite;
    }

    public Vencimento getVencimento() {
        return this.vencimento;
    }

    public Proposta getProposta() {
        return this.proposta;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", emitidoEm='" + getEmitidoEm() + "'" +
            ", titular='" + getTitular() + "'" +
            ", limite='" + getLimite() + "'" +
            ", vencimento='" + getVencimento() + "'" +
            ", proposta='" + getProposta() + "'" +
            "}";
    }


    // {
    //     "id": "3703-4085-8216-6151", **
    //     "emitidoEm": "2021-06-03T10:08:51.28082", **
    //     "titular": "string", **
    //     "bloqueios": [],
    //     "avisos": [],
    //     "carteiras": [],
    //     "parcelas": [],
    //     "limite": 2554, **
    //     "renegociacao": null,
    //     "vencimento": ** {
    //       "id": "64280146-5ea4-4860-80fd-0a0ba2984ea0",
    //       "dia": 30,
    //       "dataDeCriacao": "2021-06-03T10:08:51.280891"
    //     },
    //     "idProposta": "1"
    //   }
    
}
