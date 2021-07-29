package br.com.zup.casadocodigo.casadocodigo.dto;

import br.com.zup.casadocodigo.casadocodigo.entidades.Cliente;
import br.com.zup.casadocodigo.casadocodigo.entidades.Estado;
import br.com.zup.casadocodigo.casadocodigo.entidades.Pais;
import br.com.zup.casadocodigo.casadocodigo.validacao.EstadoValidator;
import br.com.zup.casadocodigo.casadocodigo.validacao.ExistsId;
import br.com.zup.casadocodigo.casadocodigo.validacao.UniqueValue;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClienteDto {

    private Long id;
    @NotNull @NotBlank @Email @UniqueValue(domainClass = Cliente.class, fieldName = "email")
    private String email;
    @NotNull @NotBlank
    private String nome;
    @NotNull @NotBlank
    private String sobrenome;
    @NotNull @NotBlank @UniqueValue(domainClass = Cliente.class, fieldName = "documento")
    private String documento;
    @NotNull @NotBlank
    private String endereco;
    @NotNull @NotBlank
    private String complemento;
    @NotNull @NotBlank
    private String cidade;
    @NotNull @ExistsId(domainClass = Pais.class, fieldName = "id")
    private Long idPais;
    @ExistsId(domainClass = Estado.class, fieldName = "id")
    private Long idEstado;
    @Length(min=10, max=20)
    private String telefone;
    @Length(min=8, max = 9)
    private String cep;

    public Cliente toCliente(EntityManager manager){
        Pais pais = manager.find(Pais.class, this.idPais);
        Estado estado = manager.find(Estado.class, this.idEstado);
        return new Cliente(this.email, this.nome, this.sobrenome, this.documento, this.endereco,
                this.complemento, this.cidade, pais, estado,this.telefone,this.cep);
    }

    public ClienteDto(String email, String nome, String sobrenome, String documento, String endereco, String complemento, String cidade, Long idPais, Long idEstado, String telefone, String cep) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.idPais = idPais;
        this.idEstado = idEstado;
        this.telefone = telefone;
        this.cep = cep;
    }

    public ClienteDto(Cliente cliente){
        this.email = cliente.getEmail();
        this.nome = cliente.getNome();
        this.sobrenome = cliente.getSobrenome();
        this.documento = cliente.getDocumento();
        this.endereco = cliente.getEndereco();
        this.complemento = cliente.getComplemento();
        this.cidade = cliente.getCidade();
        this.idPais = cliente.getPais().getId();
        this.idEstado = cliente.getEstado().getId();
        this.telefone = cliente.getTelefone();
        this.cep = cliente.getCep();

    }
    private ClienteDto(){};

    public void setId(Long id) {
        this.id = id;
    }

    public boolean CpfCnpjValidator(){
        CPFValidator cpfValidator = new CPFValidator();
        CNPJValidator cnpjValidator = new CNPJValidator();
        cpfValidator.initialize(null);
        cnpjValidator.initialize(null);

        return cpfValidator.isValid(documento,null) || cnpjValidator.isValid(documento,null);
    }

    @Override
    public String toString() {
        return "ClienteDto{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", documento='" + documento + '\'' +
                ", endereco='" + endereco + '\'' +
                ", complemento='" + complemento + '\'' +
                ", cidade='" + cidade + '\'' +
                ", idPais=" + idPais +
                ", idEstado=" + idEstado +
                ", telefone='" + telefone + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public Long getIdPais() {
        return idPais;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }
}
