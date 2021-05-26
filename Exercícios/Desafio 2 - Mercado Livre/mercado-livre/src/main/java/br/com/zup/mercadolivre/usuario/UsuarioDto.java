package br.com.zup.mercadolivre.usuario;


import br.com.zup.mercadolivre.validacao.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


public class UsuarioDto {
    @Email @NotBlank @NotNull @UniqueValue(domainClass = Usuario.class, fieldName = "login")
    private String login;
    @Length(min = 6)
    private String senha;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataCadastro;


    public Usuario toUsuario(){
        return new Usuario(this.login, this.senha);
    }

    private UsuarioDto() {
    }

    public UsuarioDto(Usuario usuario){
        this.login = usuario.getLogin();
        this.senha = "******";
        this.dataCadastro = usuario.getDataCadastro();
    }

    public UsuarioDto(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return "UsuarioDto{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
