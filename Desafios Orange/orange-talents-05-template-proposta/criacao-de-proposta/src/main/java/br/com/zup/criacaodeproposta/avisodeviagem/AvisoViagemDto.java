package br.com.zup.criacaodeproposta.avisodeviagem;

import br.com.zup.criacaodeproposta.cadastracartao.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AvisoViagemDto {

    private String idCartao;
    @NotBlank @NotNull
    private String destino;
    @JsonFormat(pattern = "dd/MM/yyyy") @Future @NotNull
    private LocalDate dataTermino;
    private LocalDateTime dataAviso;
    private String ipAddr;
    private String userAgent;

    public AvisoViagemDto(AvisoViagem avisoViagem){
        this.idCartao = avisoViagem.getCartao().getId();
        this.destino = avisoViagem.getDestino();
        this.dataTermino = avisoViagem.getDataTermino();
        this.dataAviso = avisoViagem.getDataAviso();
        this.ipAddr = avisoViagem.getIpAddr();
        this.userAgent = avisoViagem.getUserAgent();
    }

    public AvisoViagem toModel(Cartao cartao){
        return new AvisoViagem(cartao, this.destino, this.dataTermino, this.ipAddr, this.userAgent);
    }

    public AvisoViagemDto(String idCartao, String destino, LocalDate dataTermino,  String ipAddr, String userAgent) {
        this.idCartao = idCartao;
        this.destino = destino;
        this.dataTermino = dataTermino;

        this.ipAddr = ipAddr;
        this.userAgent = userAgent;
    }
    @Deprecated
    public AvisoViagemDto() {
    }

    public String getIdCartao() {
        return idCartao;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public LocalDateTime getDataAviso() {
        return dataAviso;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
