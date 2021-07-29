package br.com.zup.criacaodeproposta.acompanhamentoproposta;

import java.math.BigDecimal;
import java.util.Objects;

import br.com.zup.criacaodeproposta.cadastracartao.FormCartao;
import br.com.zup.criacaodeproposta.novaproposta.Proposta;

public class FormDetalheProposta {

    
    private String documento;    
    private String email;    
    private String nome;    
    private String endereco;    
    private BigDecimal salario;    
    private String estadoProposta;
    private FormCartao cartao;
    

    public FormDetalheProposta(Proposta proposta){

        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.estadoProposta = proposta.getStatusProposta().toString();
        if(!Objects.isNull(proposta.getCartao())){
            this.cartao = new FormCartao(proposta.getCartao());
        }
        
        

    }

    private FormDetalheProposta(){}


    public String getDocumento() {
        return this.documento;
    }

    public String getEmail() {
        return this.email;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public BigDecimal getSalario() {
        return this.salario;
    }

    public String getEstadoProposta() {
        return this.estadoProposta;
    }

    public FormCartao getCartao() {
        return this.cartao;
    }

    @Override
    public String toString() {
        return "{" +
            " documento='" + getDocumento() + "'" +
            ", email='" + getEmail() + "'" +
            ", nome='" + getNome() + "'" +
            ", endereco='" + getEndereco() + "'" +
            ", salario='" + getSalario() + "'" +
            ", estadoProposta='" + getEstadoProposta() + "'" +
            ", cartao='" + getCartao() + "'" +
            "}";
    }
  
}
