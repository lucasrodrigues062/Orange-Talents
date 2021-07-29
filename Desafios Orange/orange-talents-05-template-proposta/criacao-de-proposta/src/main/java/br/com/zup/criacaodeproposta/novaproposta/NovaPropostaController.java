package br.com.zup.criacaodeproposta.novaproposta;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.zup.criacaodeproposta.metricas.Metricas;
import io.micrometer.core.instrument.MeterRegistry;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Metric;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.criacaodeproposta.consultadadosfinanceiro.ConsultaFinanceira;
import br.com.zup.criacaodeproposta.consultadadosfinanceiro.FormConsultaRestricao;
import br.com.zup.criacaodeproposta.tratamentodeerros.Erro;

@RestController
@RequestMapping("/api/proposta")
public class NovaPropostaController {
    
    private PropostaRepository propostaRepository;
    private ConsultaFinanceira consultaFinanceira;
    @Autowired
    private Metricas metricas;

    public final Tracer tracer;


    public NovaPropostaController(PropostaRepository propostaRepository, ConsultaFinanceira consultaFinanceira, Tracer tracer){
        this.propostaRepository = propostaRepository;
        this.consultaFinanceira = consultaFinanceira;
        this.tracer = tracer;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cria(@RequestBody @Valid PropostaDto request, UriComponentsBuilder builder){
        Span activeSpan = tracer.activeSpan();
        activeSpan.setBaggageItem("user.email", request.getEmail());
        activeSpan.setTag("user.email", request.getEmail());
        activeSpan.log("Iniciado processo de requisicao");
        Optional<List<Proposta>> optional = propostaRepository.findByDocumento(Proposta.limpaString(request.getDocumento()) );
        if(optional.get().size() > 0){
            Erro erro = new Erro("documento", "Ja existe proposta para esse Documento");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
        }                
        Proposta proposta = propostaRepository.save(request.toModel());
        metricas.meuContador();
        FormConsultaRestricao restricao = consultaFinanceira.consultaRestricao(new FormConsultaRestricao(proposta));
        proposta.atualizaEstadoProposta(restricao);
        

        URI uri = builder.path("/api/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).body(new PropostaDto(proposta));
    }
}
