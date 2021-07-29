package br.com.zup.criacaodeproposta.carteiradigital;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "carteiraDigital", url = "${api.cadastracartao}")
public interface CarteiraDigitalSistemaExterno {

    @RequestMapping(value = "/api/cartoes/{idCartao}/carteiras", method = RequestMethod.POST, consumes = "application/json")
    ResponseEntity<RetornoCarteira> associaCarteira(@PathVariable(name = "idCartao") String idCartao, @RequestBody CarteiraDigitalDto carteiraDigitalDto);
}
