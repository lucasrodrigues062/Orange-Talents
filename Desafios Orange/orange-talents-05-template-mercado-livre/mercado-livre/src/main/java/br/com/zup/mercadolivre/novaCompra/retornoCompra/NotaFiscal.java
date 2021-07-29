package br.com.zup.mercadolivre.novaCompra.retornoCompra;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.zup.mercadolivre.novaCompra.Compra;

@Service
public class NotaFiscal {

    public void gerar(Compra compra) {
        RestTemplate rest = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(),"idUsuario", compra.getUser().getId());

        rest.postForEntity("http://localhost:8080/nf", request, String.class);

    }


}
