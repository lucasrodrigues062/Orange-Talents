package br.com.zup.criacaodeproposta.cadastrobiometria;

import br.com.zup.criacaodeproposta.cadastracartao.Cartao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class BiometriaDto {

    @NotNull @NotBlank
    private String fingerPrint;
    private String idCartao;
    private LocalDateTime dataCadastro;

    public BiometriaDto(Biometria biometria) {
        this.fingerPrint = biometria.getFingerPrint();
        this.idCartao = biometria.getCartao().getId();
        this.dataCadastro = biometria.getDataCadastro();
    }

    public Biometria toModel(Cartao cartao){
        return new Biometria(fingerPrint, cartao);
    }
    public BiometriaDto(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public String getFingerPrint() {
        return Biometria.encodeBase64(this.fingerPrint);
    }

    public String getIdCartao() {
        return idCartao;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    @Deprecated
    public BiometriaDto() {
    }

    public void setIdCartao(String idCartao) {
        this.idCartao = idCartao;
    }
}
