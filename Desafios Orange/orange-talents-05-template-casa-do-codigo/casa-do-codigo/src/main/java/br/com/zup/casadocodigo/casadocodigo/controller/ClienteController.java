package br.com.zup.casadocodigo.casadocodigo.controller;

import br.com.zup.casadocodigo.casadocodigo.dto.ClienteDto;
import br.com.zup.casadocodigo.casadocodigo.entidades.Cliente;
import br.com.zup.casadocodigo.casadocodigo.repositorios.ClienteRepository;
import br.com.zup.casadocodigo.casadocodigo.validacao.CpfCnpjValidator;
import br.com.zup.casadocodigo.casadocodigo.validacao.EstadoValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private EstadoValidator estadoValidator;
    @PersistenceContext
    private EntityManager manager;
    private ClienteRepository clienteRepository;

    public ClienteController (EstadoValidator estadoValidator, ClienteRepository clienteRepository){
        this.estadoValidator = estadoValidator;
        this.clienteRepository = clienteRepository;
    }

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(new CpfCnpjValidator(), estadoValidator );
    }

    @PostMapping
    public ResponseEntity<ClienteDto> novoCliente(@RequestBody @Valid ClienteDto clienteDto, UriComponentsBuilder builder){
        Cliente cliente = this.clienteRepository.save(clienteDto.toCliente(manager));
        URI uri = builder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
        ClienteDto response = new ClienteDto(cliente);
        response.setId(cliente.getId());
        return ResponseEntity.created(uri).body(response);

    }
}
