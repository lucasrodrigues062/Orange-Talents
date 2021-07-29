package br.com.zup.criacaodeproposta.cadastracartao;

import java.time.LocalDateTime;

public class FormVencimento {

    private String id;
    private Integer dia;
    private LocalDateTime dataDeCriacao;


    public String getId() {
        return this.id;
    }

    public Integer getDia() {
        return this.dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return this.dataDeCriacao;
    }

    public FormVencimento(Vencimento vencimento){
        this.id = vencimento.getId();
        this.dia = vencimento.getDia();
        this.dataDeCriacao = vencimento.getDataDeCriacao();
    }

    public FormVencimento(String id, Integer dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    private FormVencimento() {
    }

    public Vencimento toModel() {
        return new Vencimento(id, dia, dataDeCriacao);
    }

}

