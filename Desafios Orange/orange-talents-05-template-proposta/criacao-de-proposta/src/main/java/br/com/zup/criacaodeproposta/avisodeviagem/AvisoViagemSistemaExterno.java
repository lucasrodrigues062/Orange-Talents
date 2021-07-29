package br.com.zup.criacaodeproposta.avisodeviagem;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AvisoViagemSistemaExterno {

    private String destino;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validoAte;

    public AvisoViagemSistemaExterno(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }
    @Deprecated
    public AvisoViagemSistemaExterno() {
    }
    public AvisoViagemSistemaExterno (AvisoViagemDto dto){
        this.destino=dto.getDestino();
        this.validoAte = dto.getDataTermino();
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
