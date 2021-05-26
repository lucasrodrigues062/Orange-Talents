package br.com.zup.mercadolivre.security;


import br.com.zup.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/autenticacao")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;
	
		@PostMapping
		public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form){

			UsernamePasswordAuthenticationToken dadosLogin = form.converter();
			
			
			try {
				System.out.println(dadosLogin.toString());
				Authentication authentication = authManager.authenticate(dadosLogin);
				System.out.println(dadosLogin.toString());
				String token = tokenService.gerarToken(authentication);
				
				return ResponseEntity.ok(new TokenDto(token,"Bearer"));
			} catch (AuthenticationException e) {

				return ResponseEntity.badRequest().build();
			}
			
			
		}
}
