package br.zup.transacao.transacao.listatransacoes;

import br.zup.transacao.transacao.model.Cartao;
import br.zup.transacao.transacao.model.CartaoRepository;
import br.zup.transacao.transacao.model.Transacao;
import br.zup.transacao.transacao.model.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/listatransacao")
public class ListaTransacaoController {
    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private CartaoRepository cartaoRepository;

    @GetMapping("/{idCartao}")
    public ResponseEntity<?> detalhar(@PathVariable(name = "idCartao") String idCartao,
                                        @PageableDefault(sort = "efetivadaEm", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao){
        Optional<Cartao> optionalCartao = cartaoRepository.findById(idCartao);

        if(!optionalCartao.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Page<Transacao> transacoesPage = transacaoRepository.findByCartaoId(optionalCartao.get().getId(),paginacao);
        
        List<TransacaoRequest> transacoes = new ArrayList<>();
        transacoesPage.forEach(transacao -> {
            transacoes.add(new TransacaoRequest(transacao));
        });

        return ResponseEntity.ok().body(transacoes);
    }
}
