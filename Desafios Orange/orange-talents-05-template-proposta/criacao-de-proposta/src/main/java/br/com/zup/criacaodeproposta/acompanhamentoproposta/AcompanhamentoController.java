package br.com.zup.criacaodeproposta.acompanhamentoproposta;

import java.util.Optional;

import io.micrometer.core.annotation.Timed;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.criacaodeproposta.novaproposta.Proposta;
import br.com.zup.criacaodeproposta.novaproposta.PropostaRepository;

@RestController
@RequestMapping("/api/proposta")
public class AcompanhamentoController {

    private PropostaRepository repository;
    public final Tracer tracer;

    @Timed(value = "detalhe.proposta.request", histogram = false,  extraTags = {"emissora", "Mastercard", "banco", "itau"})
    @GetMapping("/{idProposta}")
    public ResponseEntity<?> consulta(@PathVariable(name = "idProposta") Long idProposta){
        Span activeSpan = tracer.activeSpan();
        String userEmail = activeSpan.getBaggageItem("user.email");
        activeSpan.setBaggageItem("user.email", userEmail);

        Optional<Proposta> optional = repository.findById(idProposta);
        if(optional.isPresent()){
            
            return ResponseEntity.ok().body(new FormDetalheProposta(optional.get()));
        }

        return ResponseEntity.notFound().build();

        
    }


    public AcompanhamentoController(PropostaRepository repository, Tracer tracer){
        this.repository = repository;
        this.tracer = tracer;
    }

}
