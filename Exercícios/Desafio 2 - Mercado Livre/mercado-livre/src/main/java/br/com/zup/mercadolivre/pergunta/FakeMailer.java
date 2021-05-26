package br.com.zup.mercadolivre.pergunta;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FakeMailer implements Mailer{

    @Override
    public void send(String body, String subject, String nameFrom, String from, String nameTo) {
        System.out.println(body);
        System.out.println(subject);
        System.out.println(nameFrom);
        System.out.println(from);
        System.out.println(nameTo);
        
    }
    
}
