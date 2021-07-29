package br.com.zup.criacaodeproposta.bloqueiacartao;

import br.com.zup.criacaodeproposta.cadastracartao.Cartao;
import br.com.zup.criacaodeproposta.cadastracartao.CartaoRepository;
import br.com.zup.criacaodeproposta.tratamentodeerros.Erro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/bloqueio")
public class BloqueiaCartaoController {
    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private BloqueioCartaoRepository bloqueioCartaoRepository;
    @Autowired
    private AvisaBloqueioRequest avisaBloqueio;


    @PostMapping("/{idCartao}")
    public ResponseEntity<?> bloqueia(@PathVariable(name = "idCartao") String idCartao, @RequestHeader(value = "User-Agent") String userAgent, HttpServletRequest request){

        Optional<Cartao> cartaoOptional = cartaoRepository.findById(idCartao);
        if(!cartaoOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        if(cartaoOptional.get().getStatusCartao().equals(StatusCartao.BLOQUEADO)){
            Erro erro = new Erro("cartao", "Cartao ja se encontra bloqueado");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
        }
            BloqueioCartao bloqueioCartao = bloqueioCartaoRepository.save(new BloqueioCartao(request.getRemoteAddr(),userAgent, cartaoOptional.get()));

        avisaBloqueio.atualizaBloqueio(cartaoOptional.get());

        return ResponseEntity.ok().build();
    }
}
