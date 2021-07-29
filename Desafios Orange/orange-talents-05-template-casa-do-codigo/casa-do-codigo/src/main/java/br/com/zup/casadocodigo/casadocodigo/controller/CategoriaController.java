package br.com.zup.casadocodigo.casadocodigo.controller;

import br.com.zup.casadocodigo.casadocodigo.dto.AutorDto;
import br.com.zup.casadocodigo.casadocodigo.dto.CategoriaDto;
import br.com.zup.casadocodigo.casadocodigo.entidades.Autor;
import br.com.zup.casadocodigo.casadocodigo.entidades.Categoria;
import br.com.zup.casadocodigo.casadocodigo.repositorios.CategoriaRepository;
import br.com.zup.casadocodigo.casadocodigo.validacao.NomeDuplicadoValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;

    }



    @PostMapping
    public ResponseEntity<CategoriaDto> novo(@RequestBody @Valid CategoriaDto categoriaDto, UriComponentsBuilder builder){

            Categoria categoria = categoriaRepository.save(categoriaDto.toCategoria());
            URI uri =  builder.path("/categoria/{id}").buildAndExpand(categoria.getId()).toUri();
            return ResponseEntity.created(uri).body( new CategoriaDto(categoria));


    }
}
