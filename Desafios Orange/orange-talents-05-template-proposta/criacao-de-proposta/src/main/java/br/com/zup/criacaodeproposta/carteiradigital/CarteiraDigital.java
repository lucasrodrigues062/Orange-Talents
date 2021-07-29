package br.com.zup.criacaodeproposta.carteiradigital;

import br.com.zup.criacaodeproposta.cadastracartao.Cartao;

import javax.persistence.*;

@Entity
public class CarteiraDigital {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idCarteira;
    @ManyToOne @JoinColumn(name = "cartao_id")
    private Cartao cartao;
    private TipoCarteira tipoCarteira;

    public CarteiraDigital(String idCarteira, Cartao cartao, TipoCarteira tipoCarteira) {
        this.idCarteira = idCarteira;
        this.cartao = cartao;
        this.tipoCarteira = tipoCarteira;
    }
    public CarteiraDigital(){

    }
    public Long getId() {
        return id;
    }

    public String getIdCarteira() {
        return idCarteira;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public TipoCarteira getCarteiraDigital() {
        return tipoCarteira;
    }
}
