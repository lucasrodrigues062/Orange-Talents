package br.com.zup.criacaodeproposta.cadastrobiometria;

import br.com.zup.criacaodeproposta.cadastracartao.Cartao;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Base64;

@Entity
public class Biometria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final String fingerPrint;

    @ManyToOne @JoinColumn(name = "cartao_id")
    private final Cartao cartao;
    private LocalDateTime dataCadastro = LocalDateTime.now();

    public Biometria(String fingerPrint, Cartao cartao) {

        this.fingerPrint = encodeBase64(fingerPrint);
        this.cartao = cartao;
    }

    public static String encodeBase64(String string){
        return Base64.getEncoder().encodeToString(string.getBytes());
    }
    public static String decodeBase64(String string){
        return new String( Base64.getDecoder().decode(string.getBytes()) );
    }

    public Long getId() {
        return id;
    }

    public String getFingerPrint() {
        return decodeBase64(this.fingerPrint);
    }

    public Cartao getCartao() {
        return cartao;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }
}
