package br.com.zup.criacaodeproposta.consultadadosfinanceiro;

import br.com.zup.criacaodeproposta.novaproposta.Proposta;

public class FormConsultaRestricao {

    public String documento;
    public String nome;
    public Long idProposta;
    public Resultado resultadoSolicitacao;

    public FormConsultaRestricao(Proposta proposta){
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId();
        
    }


    public String getDocumento() {
        return this.documento;
    }

    public String getNome() {
        return this.nome;
    }

    public Long getIdProposta() {
        return this.idProposta;
    }

    public Resultado getResultadoSolicitacao() {
        return this.resultadoSolicitacao;
    }


    public FormConsultaRestricao(String documento, String nome, Long idProposta, Resultado resultadoSolicitacao) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
        this.resultadoSolicitacao = resultadoSolicitacao;
    }

    private FormConsultaRestricao(){};


    @Override
    public String toString() {
        return "{" +
            " documento='" + getDocumento() + "'" +
            ", nome='" + getNome() + "'" +
            ", idProposta='" + getIdProposta() + "'" +
            ", resultadoSolicitacao='" + getResultadoSolicitacao() + "'" +
            "}";
    }
    
}
