package br.com.zup.criacaodeproposta.cadastracartao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zup.criacaodeproposta.consultadadosfinanceiro.FormConsultaRestricao;
import br.com.zup.criacaodeproposta.novaproposta.EstadoProposta;
import br.com.zup.criacaodeproposta.novaproposta.Proposta;
import br.com.zup.criacaodeproposta.novaproposta.PropostaRepository;
@Component
@EnableScheduling
public class AssociaCartao {
    


    @Scheduled(cron = "0/15 * * * * * ")
    public void associar(){
        try {
            logger.info("Iniciado o agendamento");
            List<Proposta> propostas = propostaRepository.findByEstadoPropostaAndCartaoIsNull(EstadoProposta.ELEGIVEL);
            if(propostas.size() <= 0) {
                logger.info("Nao existem propostas para atualizar");
                return;
            }

            propostas.forEach(proposta -> {
                FormConsultaRestricao consultaRestricao = new FormConsultaRestricao(proposta);
                FormCartao formCartao = cartaoRequest.criaCartao(consultaRestricao);

                Cartao cartao = cartaoRepository.save(formCartao.toModel(propostaRepository));

                logger.info("Cartao numero: " + cartao.getId().substring(1,4) + "***" + cartao.getId().substring(cartao.getId().length() - 4, cartao.getId().length()) +
                        ", Proposta de Id: " + cartao.getProposta().getId() + " Cadastrado com Sucesso!!" );
            });

            logger.info("Concluido o metodo");
        } catch (Exception exception) {
            logger.error("Nao foi possivel comunicar com o servidor");
        }


    }

    public AssociaCartao(CartaoRequest cartaoRequest, CartaoRepository cartaoRepository, PropostaRepository propostaRepository ){
        this.cartaoRequest = cartaoRequest;
        this.cartaoRepository = cartaoRepository;
        this.propostaRepository = propostaRepository;
    }

    private CartaoRequest cartaoRequest;
    private CartaoRepository cartaoRepository;    
    private PropostaRepository propostaRepository;
    private Logger logger = LoggerFactory.getLogger(AssociaCartao.class);
}
