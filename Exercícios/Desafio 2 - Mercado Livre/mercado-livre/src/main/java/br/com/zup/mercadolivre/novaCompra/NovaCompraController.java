package br.com.zup.mercadolivre.novaCompra;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.mercadolivre.pergunta.Emails;
import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.produto.ProdutoRepository;
import br.com.zup.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("/api/compra")
public class NovaCompraController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CompraRepository CompraRepository;
    @Autowired
    private Emails emails;

    @PostMapping
    @Transactional
    public ResponseEntity<?> novaCompra(@RequestBody @Valid NovaCompraDto dto, @AuthenticationPrincipal Usuario user,
            UriComponentsBuilder builder) throws BindException {

        Optional<Produto> optional = produtoRepository.findById(dto.getIdProduto());
        if (optional.isPresent()) {
            Produto produto = optional.get();
            boolean temEstoque = produto.diminuiQtd(dto.getQtd());
            if (temEstoque) {
                Compra novaCompra = CompraRepository.save(new Compra(produto, dto.getQtd(), user, dto.getGateway()));
                String url = "";
                if (novaCompra.getGateway().equals(Gateway.PAGSEGURO)) {

                    String uri = builder.path("/retorno/pagseguro/{id}").buildAndExpand(novaCompra.getId()).toString();
                    url = "pagseguro.com?returnId=" + novaCompra.getId() + "&redirectUrl=" + uri;

                    return ResponseEntity.status(HttpStatus.FOUND).body(url);
                }
                if (novaCompra.getGateway().equals(Gateway.PAYPAL)) {
                    String uri = builder.path("/retorno/paypal/{id}").buildAndExpand(novaCompra.getId()).toString();

                    url = "paypal.com?buyerId=" + novaCompra.getId() + "&redirectUrl=" + uri;
                    return ResponseEntity.status(HttpStatus.FOUND).body(url);
                }
                emails.novaCompra(novaCompra);
                return ResponseEntity.badRequest().build();
            } else {
                BindException estoqueInsuficiente = new BindException(dto, "NovaCompraDto");
                estoqueInsuficiente.reject("1", "Infelizmente nao temos estoque disponivel desse produto");
                throw estoqueInsuficiente;
            }

        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
