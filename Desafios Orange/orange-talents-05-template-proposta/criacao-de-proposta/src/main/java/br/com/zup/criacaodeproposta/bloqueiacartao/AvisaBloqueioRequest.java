package br.com.zup.criacaodeproposta.bloqueiacartao;

import br.com.zup.criacaodeproposta.cadastracartao.Cartao;
import br.com.zup.criacaodeproposta.cadastracartao.CartaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AvisaBloqueioRequest {

    private Logger logger = LoggerFactory.getLogger(AvisaBloqueioRequest.class);
    private CartaoRepository cartaoRepository;
    private AvisaBloqueio avisaBloqueio;


    public void atualizaBloqueio(Cartao cartao){
        String sistema= "{\"sistemaResponsavel\":\"bloqueioDeCartao\"}";
        logger.info("Iniciando Requisicao para avisar sistema de bloqueio");
        ResponseEntity<String> response = avisaBloqueio.avisaBloqueio(cartao.getId(), sistema);
        if(response.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Resposta recebida do sistema de bloqueio");
            cartao.atualizaStatusCartao(StatusCartao.BLOQUEADO);
            cartaoRepository.save(cartao);

        } else {
            logger.info("Nao foi possivel bloquear o cartao no sistema legado");
            logger.info("Status code: " + response.getStatusCode());
        }

    }



    public AvisaBloqueioRequest(CartaoRepository cartaoRepository, AvisaBloqueio avisaBloqueio) {

        this.cartaoRepository = cartaoRepository;
        this.avisaBloqueio = avisaBloqueio;
    }
}
