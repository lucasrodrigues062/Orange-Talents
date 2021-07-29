package br.com.zup.criacaodeproposta.avisodeviagem;

import br.com.zup.criacaodeproposta.cadastracartao.Cartao;
import br.com.zup.criacaodeproposta.cadastracartao.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/avisoviagem")
public class AvisoDeViagemController {
    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private AvisoViagemRepository avisoViagemRepository;
    @Autowired
    private AvisoSistema avisoSistema;

    @PostMapping("/{idCartao}")
    public ResponseEntity<?> aviso(@PathVariable(name = "idCartao") String idCartao, @RequestHeader(value = "User-Agent") String userAgent,
                                   HttpServletRequest request, @RequestBody @Valid AvisoViagemDto avisoDto){
        Optional<Cartao> cartaoOptional = cartaoRepository.findById(idCartao);
        if(!cartaoOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        boolean avisoEnviado = avisoSistema.enviaAviso(new AvisoViagemSistemaExterno(avisoDto), cartaoOptional.get().getId());
        if(!avisoEnviado){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }

        AvisoViagem avisoViagem = avisoViagemRepository.save(new AvisoViagemDto(cartaoOptional.get().getId(), avisoDto.getDestino(),avisoDto.getDataTermino(),request.getRemoteAddr(),userAgent)
                                    .toModel(cartaoOptional.get()));



        return ResponseEntity.ok().build();
    }

}
