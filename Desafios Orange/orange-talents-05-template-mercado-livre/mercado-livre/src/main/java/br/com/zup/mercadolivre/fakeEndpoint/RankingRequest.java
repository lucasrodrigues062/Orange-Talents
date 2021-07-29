package br.com.zup.mercadolivre.fakeEndpoint;

public class RankingRequest {

    private Long idCompra;
    private Long idVendedor;


    public Long getIdCompra() {
        return this.idCompra;
    }

    public Long getIdVendedor() {
        return this.idVendedor;
    }



    public RankingRequest(Long idCompra, Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }

    private RankingRequest() {
    }

}
