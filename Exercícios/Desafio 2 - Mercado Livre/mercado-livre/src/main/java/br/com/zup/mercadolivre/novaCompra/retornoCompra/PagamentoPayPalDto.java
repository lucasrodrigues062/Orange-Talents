package br.com.zup.mercadolivre.novaCompra.retornoCompra;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.novaCompra.Compra;
import br.com.zup.mercadolivre.novaCompra.Gateway;
import br.com.zup.mercadolivre.validacao.UniqueValue;

public class PagamentoPayPalDto {
      
    @NotBlank @UniqueValue(domainClass = Pagamento.class, fieldName = "idTransacao")
    private String idTransacao;
    @NotNull @Min(0) @Max(1)
    private Integer statusPagamento;

    public String getIdTransacao() {
        return this.idTransacao;
    }

    public Integer getStatusPagamento() {
        return this.statusPagamento;
    }

    private PagamentoPayPalDto() {
    }


    public PagamentoPayPalDto(String idTransacao, Integer statusPagamento) {
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
        StatusPagamento statusPagamento = this.statusPagamento == 0 ? StatusPagamento.ERRO : StatusPagamento.SUCESSO;
        return new Pagamento(this.idTransacao,Gateway.PAYPAL,statusPagamento, compra);
    }

}
