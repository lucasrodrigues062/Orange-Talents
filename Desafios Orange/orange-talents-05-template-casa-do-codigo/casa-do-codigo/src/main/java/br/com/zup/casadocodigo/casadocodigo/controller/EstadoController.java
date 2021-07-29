package br.com.zup.casadocodigo.casadocodigo.controller;

import br.com.zup.casadocodigo.casadocodigo.dto.EstadoDto;
import br.com.zup.casadocodigo.casadocodigo.entidades.Estado;
import br.com.zup.casadocodigo.casadocodigo.repositorios.EstadoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/estado")
public class EstadoController {

    @PersistenceContext
    private EntityManager manager;
    private EstadoRepository estadoRepository;

    public EstadoController(EstadoRepository estadoRepository){
        this.estadoRepository = estadoRepository;
    }

    @PostMapping
    public ResponseEntity<EstadoDto> novoEstado(@RequestBody @Valid EstadoDto estadoDto, UriComponentsBuilder builder){

        Estado estado = this.estadoRepository.save(estadoDto.toEstado(manager));
        URI uri = builder.path("/estado/{id}").buildAndExpand(estado.getId()).toUri();
        return ResponseEntity.created(uri).body(new EstadoDto(estado));

    }


}
