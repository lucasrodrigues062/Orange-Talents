package br.com.zup.mercadolivre.fakeEndpoint;

public class NfRequest {

    private Long idCompra;
    private Long idUsuario;


    @Override
    public String toString() {
        return "{" +
            " idCompra='" + getIdCompra() + "'" +
            ", idUsuario='" + getIdUsuario() + "'" +
            "}";
    }


    public Long getIdCompra() {
        return this.idCompra;
    }

    public Long getIdUsuario() {
        return this.idUsuario;
    }

    public NfRequest(Long idCompra, Long idUsuario) {
        this.idCompra = idCompra;
        this.idUsuario = idUsuario;
    }

    private NfRequest() {
    }

}
