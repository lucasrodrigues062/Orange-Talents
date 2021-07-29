package br.com.zup.mercadolivre.novaCompra;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.validacao.ExistsId;

public class NovaCompraDto {

    @Positive @NotNull
    private Integer qtd;
    @NotNull @ExistsId(domainClass = Produto.class, fieldName = "id")
    private Long idProduto;
    @NotNull
    private Gateway gateway;


    public Integer getQtd() {
        return this.qtd;
    }

    public Long getIdProduto() {
        return this.idProduto;
    }

    public Gateway getGateway() {
        return this.gateway;
    }

    public NovaCompraDto(Integer qtd, Long idProduto, Gateway gateway) {
        this.qtd = qtd;
        this.idProduto = idProduto;
        this.gateway = gateway;
    }

    private NovaCompraDto() {
    }

    @Override
    public String toString() {
        return "{" +
            " qtd='" + getQtd() + "'" +
            ", idProduto='" + getIdProduto() + "'" +
            "}";
    }


}

