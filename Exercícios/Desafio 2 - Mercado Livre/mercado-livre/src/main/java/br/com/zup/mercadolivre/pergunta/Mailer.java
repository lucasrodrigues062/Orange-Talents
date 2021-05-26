package br.com.zup.mercadolivre.pergunta;

public interface Mailer {

    void send(String body, String subject, String nameFrom, String from ,String nameTo);

}
