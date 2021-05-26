package br.com.zup.mercadolivre.produto;

import br.com.zup.mercadolivre.opiniao.OpiniaoProduto;

public class OpiniaoForm {

    /*            "nota": 1,
            "titulo": "vendedor nao entregou",
            "descricao": "Vendedor, sacana, nao entregou o produto",
            "idUsuario": 1,
            "idProduto": 1 */
    private Integer nota;
    private String titulo;
    private String descricao;

    public OpiniaoForm(OpiniaoProduto o){
        this.nota = o.getNota();
        this.titulo = o.getTitulo();
        this.descricao = o.getDescricao();
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }


    
    
}
