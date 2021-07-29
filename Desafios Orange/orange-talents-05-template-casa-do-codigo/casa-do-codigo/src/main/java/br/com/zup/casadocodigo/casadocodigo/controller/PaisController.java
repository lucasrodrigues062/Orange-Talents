package br.com.zup.casadocodigo.casadocodigo.controller;

import br.com.zup.casadocodigo.casadocodigo.dto.PaisDto;
import br.com.zup.casadocodigo.casadocodigo.entidades.Pais;
import br.com.zup.casadocodigo.casadocodigo.repositorios.PaisRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/pais")
public class PaisController {

    private PaisRepository paisRepository;
    public PaisController(PaisRepository paisRepository){
        this.paisRepository = paisRepository;
    }

    @PostMapping
    public ResponseEntity<PaisDto> novoPais(@RequestBody @Valid PaisDto paisDto, UriComponentsBuilder builder){

        Pais pais = this.paisRepository.save(paisDto.toPais());
        URI uri = builder.path("/pais/{id}").buildAndExpand(pais.getId()).toUri();
        return ResponseEntity.created(uri).body(new PaisDto(pais));

    }
}
