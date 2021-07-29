package br.com.zup.mercadolivre.pergunta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.mercadolivre.novaCompra.Compra;

@Service
public class Emails {
    @Autowired
    private Mailer mailer;



    public void novaPergunta(PerguntaProduto pergunta){
        
        mailer.send("<html>...</html>","Nova Pergunta..." ,pergunta.getUsuario().getLogin(), "perguntas@zup.com.br" ,pergunta.getProduto().getUsuario().getLogin());
    
    }

    public void novaCompra(Compra compra){
        mailer.send("<html>...</html>","Nova Compra..." ,compra.getUser().getLogin(), "comprass@zup.com.br" ,compra.getProduto().getUsuario().getLogin());
    }

    public void pagamentoRealizado(Compra compra) {
        mailer.send("<html>...</html>","Pagamento Aprovado..." ,compra.getUser().getLogin(), "comprass@zup.com.br" ,compra.getProduto().getUsuario().getLogin());
    }

    
    public void pagamentoNaoRealizado(Compra compra) {
        mailer.send("<html>...</html>","Pagmento Nao foi Aprovado..." ,compra.getProduto().getUsuario().getLogin(), "comprass@zup.com.br" ,compra.getUser().getLogin());
    }

}
