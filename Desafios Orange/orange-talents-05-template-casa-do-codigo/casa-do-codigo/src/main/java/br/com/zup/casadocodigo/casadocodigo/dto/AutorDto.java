package br.com.zup.casadocodigo.casadocodigo.dto;

import br.com.zup.casadocodigo.casadocodigo.entidades.Autor;
import br.com.zup.casadocodigo.casadocodigo.validacao.UniqueValue;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AutorDto {
    @NotNull @NotBlank
    private String nome;
    @NotNull @NotBlank @Email @UniqueValue(domainClass = Autor.class, fieldName = "email", message = "Email duplicado")
    private String email;
    @NotNull @NotBlank @Length(max = 400)
    private String descricao;

    public AutorDto(Autor autor){
        this.nome = autor.getNome();
        this.email = autor.getEmail();
        this.descricao = autor.getDescricao();
    }
    public static Page<AutorDto> toPage(Page<Autor> autores){
        return autores.map(AutorDto::new);
    }
    public Autor toAutor(){
        return new Autor(this.nome, this.email, this.descricao);
    }

    public AutorDto(){}

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDescricao() {
        return descricao;
    }
}
