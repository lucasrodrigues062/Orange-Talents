package br.com.zup.criacaodeproposta.bloqueiacartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "bloqueioCartao", url = "${api.cadastracartao}")
public interface AvisaBloqueio {
    @RequestMapping(value = "/api/cartoes/{idCartao}/bloqueios", method = RequestMethod.POST, consumes = "application/json")
    ResponseEntity<String> avisaBloqueio(@PathVariable(name = "idCartao") String idCartao, @RequestBody String sistemaResponsavel);
}

