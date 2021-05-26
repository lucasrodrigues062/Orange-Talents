package br.com.zup.mercadolivre.novaCompra.retornoCompra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.novaCompra.Compra;
import br.com.zup.mercadolivre.novaCompra.Gateway;
import br.com.zup.mercadolivre.validacao.ExistsId;
import br.com.zup.mercadolivre.validacao.UniqueValue;

public class PagamentoPagSeguroDto {
    
    @NotBlank @UniqueValue(domainClass = Pagamento.class, fieldName = "idTransacao")
    private String idTransacao;
    @NotNull
    private StatusPagamento statusPagamento;

    public String getIdTransacao() {
        return this.idTransacao;
    }

    public StatusPagamento getStatusPagamento() {
        return this.statusPagamento;
    }

    private PagamentoPagSeguroDto() {
    }


    public PagamentoPagSeguroDto(String idTransacao, StatusPagamento statusPagamento) {
        this.idTransacao = idTransacao;
        this.statusPagamento = statusPagamento;
    }


    @Override
    public String toString() {
        return "{" +
            " idTransacao='" + getIdTransacao() + "'" +
            ", statusPagamento='" + getStatusPagamento() + "'" +
            "}";
    }

    public Pagamento toPagamento(Compra compra){
        return new Pagamento(this.idTransacao,Gateway.PAGSEGURO,statusPagamento, compra);
    }
}
