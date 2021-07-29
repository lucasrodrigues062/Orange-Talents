package br.com.zup.criacaodeproposta.carteiradigital;

public class CarteiraDigitalRequest {

    private String idCarteira;
    private String idCartao;
    private TipoCarteira tipoCarteira;

    public CarteiraDigitalRequest(CarteiraDigital carteiraDigital){
        this.idCarteira = carteiraDigital.getIdCarteira();
        this.idCartao = carteiraDigital.getCartao().getId();
        this.tipoCarteira = carteiraDigital.getCarteiraDigital();
    }

    public CarteiraDigitalRequest(){}

    public CarteiraDigitalRequest(String idCarteira, String idCartao, TipoCarteira tipoCarteira) {
        this.idCarteira = idCarteira;
        this.idCartao = idCartao;
        this.tipoCarteira = tipoCarteira;
    }

    public String getIdCarteira() {
        return idCarteira;
    }

    public String getIdCartao() {
        return idCartao;
    }

    public TipoCarteira getTipoCarteira() {
        return tipoCarteira;
    }
}
