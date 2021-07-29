package br.com.zup.mercadolivre.novaCompra.retornoCompra;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.zup.mercadolivre.novaCompra.Compra;
@Service
public class Ranking {

    public void atualizar(Compra compra) {
        RestTemplate rest = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(),"idVendedor", compra.getProduto().getUsuario().getId());

        rest.postForEntity("http://localhost:8080/ranking", request, String.class);

    }

}
