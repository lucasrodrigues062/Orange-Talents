package br.com.zup.mercadolivre.pergunta;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.produto.ProdutoRepository;
import br.com.zup.mercadolivre.usuario.Usuario;

@RestController
public class PerguntaController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private PerguntaProdutoRepository perguntaRepository;
	@Autowired
	private Emails emails;

	@PostMapping("/api/produto/{id}/pergunta")
	@Transactional
	public ResponseEntity<PerguntaDto> adicionaPergunta(@PathVariable(name = "id") Long id, @RequestBody @Valid PerguntaDto dto, @AuthenticationPrincipal Usuario usuario, UriComponentsBuilder builder) {
		Optional<Produto> optional = produtoRepository.findById(id);
		if(optional.isPresent()) {
			Produto produto = optional.get();
			PerguntaProduto pergunta = this.perguntaRepository.save(dto.toPergunta(usuario, produto));
			URI uri = builder.path("/api/produto/"+id+"/pergunta/{id}").buildAndExpand(pergunta.getId()).toUri();
			
			emails.novaPergunta(pergunta);
			
			return ResponseEntity.created(uri).body(new PerguntaDto(pergunta));
			
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
