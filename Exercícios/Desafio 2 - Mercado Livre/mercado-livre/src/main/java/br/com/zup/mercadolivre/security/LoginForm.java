package br.com.zup.mercadolivre.security;

import br.com.zup.mercadolivre.usuario.Usuario;
import br.com.zup.mercadolivre.validacao.ExistsId;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginForm {

		@ExistsId(domainClass = Usuario.class, fieldName = "login")
		private String email;
		@NotBlank @NotNull
		private String senha;
		
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getSenha() {
			return senha;
		}
		public void setSenha(String senha) {
			this.senha = senha;
		}
		public UsernamePasswordAuthenticationToken converter() {

			return new UsernamePasswordAuthenticationToken(email, senha);
		}
}
