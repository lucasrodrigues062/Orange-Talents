package br.com.zup.criacaodeproposta.carteiradigital;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CadastraCarteiraSistemaExterno {

    private CarteiraDigitalSistemaExterno carteiraDigitalSistemaExterno;
    private Logger logger = LoggerFactory.getLogger(CadastraCarteiraSistemaExterno.class);

    public String cadastra(CarteiraDigitalDto carteiraDigitalDto, String idCartao){
        try {
            logger.info("iniciando cadastro sistema externo");
            ResponseEntity<RetornoCarteira> response = carteiraDigitalSistemaExterno.associaCarteira(idCartao,carteiraDigitalDto);
            logger.info("comunicacao realizada com sucesso");
            if(response.getBody().getResultado().equals("ASSOCIADA")){
                return response.getBody().getId();
            } else {
                return null;
            }

        } catch (Exception e){
            logger.error("Nao foi possivel comunicar com sistema externo");
            e.printStackTrace();
        }

        return null;
    }



    public CadastraCarteiraSistemaExterno(CarteiraDigitalSistemaExterno carteiraDigitalSistemaExterno) {
        this.carteiraDigitalSistemaExterno = carteiraDigitalSistemaExterno;
    }
}
