package br.com.zup.mercadolivre.produto;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.mercadolivre.categoria.CategoriaRepository;
import br.com.zup.mercadolivre.usuario.Usuario;
import br.com.zup.mercadolivre.validacao.CaracteristicaDuplicadaValidator;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {
	
		
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ImagemProdutoRepository imagemRepository;
	@Autowired
	private UploaderFake uploaderFake;
	
	@InitBinder(value = "ProdutoDto")
	public void init(WebDataBinder bind) {
		bind.addValidators(new CaracteristicaDuplicadaValidator());
	}
	
	@PostMapping
	public ResponseEntity<ProdutoDto> cadastra(@RequestBody @Valid ProdutoDto produtoDto, UriComponentsBuilder builder, @AuthenticationPrincipal Usuario usuario) {				
		@SuppressWarnings("unused")
		Produto produto = produtoRepository.save(produtoDto.toProduto(categoriaRepository, usuario));
		URI uri = builder.path("/api/produto/{id}").buildAndExpand(produto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new ProdutoDto(produto));
	}

	
	@PostMapping("/{id}/imagem")
	@Transactional
	public ResponseEntity<Object> adicionaImagem(@PathVariable(name = "id") Long id, @Valid ImagemDto imagemDto, @AuthenticationPrincipal Usuario usuario) {
		
		Optional<Produto> optional = produtoRepository.findById(id);
		if(optional.isPresent()) {
			Produto produto = optional.get();
			
			if(usuario.getId() != produto.getUsuario().getId()) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}
			
			Set<String> links = uploaderFake.envia(imagemDto.getImagens());
			List<ImagemProduto> imagens = imagemRepository.saveAll(imagemDto.toImagemProduto(produto, links));
			
			return ResponseEntity.ok().build();
			
		} else {
			return ResponseEntity.badRequest().build();
		}
		

		
		
		
	}
}
