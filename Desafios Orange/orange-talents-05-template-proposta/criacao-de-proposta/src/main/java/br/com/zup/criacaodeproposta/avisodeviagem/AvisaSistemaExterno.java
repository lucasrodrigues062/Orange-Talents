package br.com.zup.criacaodeproposta.avisodeviagem;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "avisaViagem", url = "${api.cadastracartao}")
public interface AvisaSistemaExterno {
    @RequestMapping(value = "/api/cartoes/{idCartao}/avisos", method = RequestMethod.POST, consumes = "application/json")
    ResponseEntity<String> avisaViagem(@RequestBody AvisoViagemSistemaExterno aviso, @PathVariable(name = "idCartao") String idCartao);
}
