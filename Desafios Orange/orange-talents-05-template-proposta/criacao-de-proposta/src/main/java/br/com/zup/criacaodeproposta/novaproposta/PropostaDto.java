package br.com.zup.criacaodeproposta.novaproposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.lang.NonNull;

import br.com.zup.criacaodeproposta.validacao.CpfCnpjValidator;
import br.com.zup.criacaodeproposta.validacao.UniqueValue;

public class PropostaDto {

    @CpfCnpjValidator @NotBlank @NotNull @UniqueValue(domainClass = Proposta.class, fieldName = "documento")
    private String documento;
    @NotNull @NotBlank @Email
    private String email;
    @NotNull @NotBlank
    private String nome;
    @NotBlank @NotNull
    private String endereco;
    @NotNull @Min(0)
    private BigDecimal salario;
    private EstadoProposta estadoProposta;


    public Proposta toModel(){
        return new Proposta(documento,email, nome, endereco, salario);
    }

    @Deprecated 
    private PropostaDto(){}

    public PropostaDto(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = Proposta.limpaString(documento);
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public PropostaDto(Proposta proposta){
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.estadoProposta = proposta.getStatusProposta();
    }

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

    public EstadoProposta getEstadoProposta() {
        return this.estadoProposta;
    }

}
