package br.com.zup.criacaodeproposta.carteiradigital;

public class RetornoCarteira {
    private String resultado;
    private String id;

    public RetornoCarteira(String resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }

    public RetornoCarteira() {
    }
}
