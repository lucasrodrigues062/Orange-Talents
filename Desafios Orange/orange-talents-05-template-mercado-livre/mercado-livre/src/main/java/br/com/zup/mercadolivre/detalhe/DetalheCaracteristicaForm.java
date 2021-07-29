package br.com.zup.mercadolivre.detalhe;

import br.com.zup.mercadolivre.produto.CaracteristicasProduto;

public class DetalheCaracteristicaForm {

    private String descricao;
    private String nome;

    public DetalheCaracteristicaForm(CaracteristicasProduto c) {
        this.nome = c.getNome();
        this.descricao = c.getDescricao();
        
    }

    public String getDescricao() {
        return descricao;
    }


    public String getNome() {
        return nome;
    }

    
    
}
