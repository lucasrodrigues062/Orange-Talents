package br.com.zup.mercadolivre.validacao;

public class Erro {

    private String campo;
    private String mensagem;

    public Erro(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
