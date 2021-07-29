package br.zup.transacao.transacao.kafka;

import br.zup.transacao.transacao.model.Transacao;
import br.zup.transacao.transacao.model.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerTransacao {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void ouvir(EventoTransacao eventoTransacao){
        System.out.println(eventoTransacao);
        Transacao transacao = transacaoRepository.save(eventoTransacao.toModel());
        System.out.println(transacao);

    }
}
