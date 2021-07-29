package br.com.zup.mercadolivre.opiniao;

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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.produto.ProdutoRepository;
import br.com.zup.mercadolivre.usuario.Usuario;

@RestController
public class OpiniaoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private OpiniaoProdutoRepository opiniaoRepository;
	
	
	@PostMapping("/api/produto/{id}/opiniao")
	@Transactional
	public ResponseEntity<OpiniaoDto> adicionaOpiniai(@PathVariable(name = "id") Long id, @Valid @RequestBody OpiniaoDto dto, @AuthenticationPrincipal Usuario usuario, UriComponentsBuilder builder) {
		
		Optional<Produto> optional = produtoRepository.findById(id);
		if(optional.isPresent()) {
			Produto produto = optional.get();
			OpiniaoProduto opiniaoProduto = this.opiniaoRepository.save(dto.toOpiniaoProduto(produto, usuario));
			URI uri = builder.path("/api/produto/"+id+"/opiniao/{id}").buildAndExpand(opiniaoProduto.getId()).toUri();
			
			return ResponseEntity.created(uri).body(new OpiniaoDto(opiniaoProduto));
			
		} else {
			return ResponseEntity.notFound().build();
		}
		
		
}
}