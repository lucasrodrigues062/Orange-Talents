package br.com.zup.mercadolivre.usuario;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Perfil implements GrantedAuthority{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;

	public Perfil(String nome){
		this.nome = nome;
	}

	@Override
	public String getAuthority() {
		
		return nome;
	}

	public Long getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}


}
