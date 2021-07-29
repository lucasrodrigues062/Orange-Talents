package br.com.zup.criacaodeproposta.cadastracartao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zup.criacaodeproposta.consultadadosfinanceiro.FormConsultaRestricao;

@FeignClient(name= "cartaoRequest", url="${api.cadastracartao}")
public interface CartaoRequest {

    @RequestMapping(value="/api/cartoes", method=RequestMethod.POST, consumes = "application/json")
    FormCartao criaCartao(FormConsultaRestricao formConsultaRestricao);
    
}
