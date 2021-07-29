package br.com.zup.criacaodeproposta.cadastracartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.zup.criacaodeproposta.novaproposta.Proposta;
import br.com.zup.criacaodeproposta.novaproposta.PropostaRepository;
import br.com.zup.criacaodeproposta.validacao.UniqueValue;

public class FormCartao {
    @UniqueValue(domainClass = Cartao.class, fieldName = "id")
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private BigDecimal limite;
    private Long idProposta;

    private FormVencimento vencimento;


    public FormCartao() {
    }

    public Cartao toModel(PropostaRepository propostaRepository){
        Proposta proposta = propostaRepository.getOne(idProposta);

        return new Cartao(this.id, this.emitidoEm,this.titular,this.limite, vencimento.toModel(), proposta);
    }

    public FormCartao(Cartao cartao){
        this.id = cartao.getId();
        this.emitidoEm = cartao.getEmitidoEm();
        this.titular = cartao.getTitular();
        this.limite = cartao.getLimite();
        this.idProposta = cartao.getProposta().getId();
        this.vencimento = new FormVencimento(cartao.getVencimento());
    }

    public FormCartao(String id, LocalDateTime emitidoEm, String titular, BigDecimal limite, Long idProposta, FormVencimento vencimento) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.idProposta = idProposta;
        this.vencimento = vencimento;
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

    public Long getIdProposta() {
        return this.idProposta;
    }

    public FormVencimento getVencimento() {
        return this.vencimento;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", emitidoEm='" + getEmitidoEm() + "'" +
            ", titular='" + getTitular() + "'" +
            ", limite='" + getLimite() + "'" +
            ", idProposta='" + getIdProposta() + "'" +
            ", vencimento='" + getVencimento() + "'" +
            "}";
    }
    
}
