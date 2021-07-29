package br.com.zup.casadocodigo.casadocodigo.controller;

import br.com.zup.casadocodigo.casadocodigo.dto.AutorDto;
import br.com.zup.casadocodigo.casadocodigo.entidades.Autor;
import br.com.zup.casadocodigo.casadocodigo.repositorios.AutorRepository;
import br.com.zup.casadocodigo.casadocodigo.validacao.EmailDuplicadoValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/autor")
public class AutorController {

    private AutorRepository autorRepository;


    public AutorController(AutorRepository autorRepository){
        this.autorRepository = autorRepository;

    }

    @PostMapping
    public ResponseEntity<AutorDto> novoAutor(@RequestBody @Valid AutorDto autorDto, UriComponentsBuilder uriComponentsBuilder){

            Autor autor = autorRepository.save(autorDto.toAutor());
            URI uri =  uriComponentsBuilder.path("/autor/{id}").buildAndExpand(autor.getId()).toUri();
            return ResponseEntity.created(uri).body( new AutorDto(autor));

    }

    @PutMapping
    public ResponseEntity<AutorDto> atualizaAutor(@RequestBody @Valid AutorDto autorDto){

            Optional<Autor> optional = autorRepository.findByEmail(autorDto.getEmail());
            if(optional.isPresent()){
                Autor autor = optional.get();
                autor.setDescricao(autorDto.getDescricao());
                autor.setNome(autorDto.getNome());

                Autor persistedAutor = autorRepository.save(autor);

                return ResponseEntity.ok().body( new AutorDto(persistedAutor));

            }

            return ResponseEntity.notFound().build();


    }


    @GetMapping
    public ResponseEntity<Page<AutorDto>> listaAutor(@RequestParam(name = "email", required = false) String email,
                           @PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao){

        if (email == null){
            Page<Autor> autores = autorRepository.findAll(paginacao);
            return ResponseEntity.ok().body(AutorDto.toPage(autores));
        } else {
            Optional<Page<Autor>> optional = autorRepository.findByEmail(email, paginacao);
            if(optional.isPresent()){
                Page<Autor> autor = optional.get();
                return ResponseEntity.ok().body(AutorDto.toPage(autor));
            } else {
               return ResponseEntity.notFound().build();
            }


        }


    }

}
