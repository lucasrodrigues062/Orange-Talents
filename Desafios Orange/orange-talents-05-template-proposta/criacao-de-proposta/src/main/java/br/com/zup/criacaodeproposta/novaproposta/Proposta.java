package br.com.zup.criacaodeproposta.novaproposta;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.criacaodeproposta.cadastracartao.Cartao;
import br.com.zup.criacaodeproposta.consultadadosfinanceiro.FormConsultaRestricao;
import br.com.zup.criacaodeproposta.consultadadosfinanceiro.Resultado;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Proposta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private @NotBlank @NotNull String documento;
    @Column(nullable = false)
    private @NotNull @NotBlank @Email String email;
    @Column(nullable = false)
    private @NotNull @NotBlank String nome;
    @Column(nullable = false)
    private @NotBlank @NotNull String endereco;
    @Column(nullable = false)
    private @NotNull @Min(0) BigDecimal salario;
    @Enumerated(EnumType.STRING)
    private EstadoProposta estadoProposta;
    @OneToOne(mappedBy = "proposta")
    private Cartao cartao;

    public Proposta(@NotBlank @NotNull String documento, @NotNull @NotBlank @Email String email,
            @NotNull @NotBlank String nome, @NotBlank @NotNull String endereco, @NotNull @Min(0) BigDecimal salario) {
                this.documento = encrypt(limpaString(documento));
                this.email = email;
                this.nome = nome;
                this.endereco = endereco;
                this.salario = salario;
    }

    @Deprecated
    public Proposta(){}

    public Long getId() {
        return this.id;
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

    public static String limpaString(String string){
        return string.replace(".", "").replace("-", "").replace("/", "").trim();
    }

    public EstadoProposta getStatusProposta() {
        return this.estadoProposta;
    }

    public EstadoProposta getEstadoProposta() {
        return this.estadoProposta;
    }

    public Cartao getCartao() {
        return this.cartao;
    }

    public void atualizaEstadoProposta( FormConsultaRestricao restricao){
        
        if(restricao.getResultadoSolicitacao().equals(Resultado.COM_RESTRICAO)){
            this.estadoProposta = EstadoProposta.NAO_ELEGIVEL;
        }
        
        if(restricao.getResultadoSolicitacao().equals(Resultado.SEM_RESTRICAO)){
            this.estadoProposta = EstadoProposta.ELEGIVEL;
        }

    }

    private String encrypt(String string){
        return new BCryptPasswordEncoder().encode(string);
    }

}
