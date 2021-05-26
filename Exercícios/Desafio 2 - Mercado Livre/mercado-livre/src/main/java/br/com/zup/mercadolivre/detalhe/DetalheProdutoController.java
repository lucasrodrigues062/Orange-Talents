package br.com.zup.mercadolivre.detalhe;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.produto.ProdutoRepository;

@RestController
@RequestMapping("/api/produto")
public class DetalheProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    
    @GetMapping("/{idProduto}")
    public ResponseEntity<DetalheProdutoForm> detalhaProduto(@PathVariable(name = "idProduto") Long idProduto){

        Optional<Produto> optional = produtoRepository.findById(idProduto);
        if(optional.isPresent()){
            Produto produto = optional.get();

            return ResponseEntity.ok().body(new DetalheProdutoForm(produto));
        }

        return ResponseEntity.notFound().build();
    }

}
