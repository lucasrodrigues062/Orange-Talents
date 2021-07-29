package br.com.zup.criacaodeproposta.avisodeviagem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.ConnectException;

@Component
public class AvisoSistema {


    private AvisaSistemaExterno avisaSistemaExterno;
    private Logger logger = LoggerFactory.getLogger(AvisoSistema.class);

    public boolean enviaAviso(AvisoViagemSistemaExterno avisoViagemSistemaExterno, String idCartao) {
        try {
            logger.info("Iniciando Comunicacao com sistema de avisos");
            ResponseEntity<String> response = avisaSistemaExterno.avisaViagem(avisoViagemSistemaExterno, idCartao);
            logger.info("Comunicacao realizada com sucesso");
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception exception){
            logger.error("Erro ao comunicar com o sistema de aviso");

        }

        return false;
    }

    public AvisoSistema(AvisaSistemaExterno avisaSistemaExterno) {
        this.avisaSistemaExterno = avisaSistemaExterno;

    }
}
