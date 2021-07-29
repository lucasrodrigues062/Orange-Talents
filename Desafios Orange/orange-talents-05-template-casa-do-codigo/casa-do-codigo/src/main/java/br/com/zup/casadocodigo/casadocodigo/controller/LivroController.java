package br.com.zup.casadocodigo.casadocodigo.controller;

import br.com.zup.casadocodigo.casadocodigo.dto.LivroDto;
import br.com.zup.casadocodigo.casadocodigo.entidades.Autor;
import br.com.zup.casadocodigo.casadocodigo.entidades.Categoria;
import br.com.zup.casadocodigo.casadocodigo.entidades.Livro;
import br.com.zup.casadocodigo.casadocodigo.form.DetalheAutorForm;
import br.com.zup.casadocodigo.casadocodigo.form.DetalheLivroForm;
import br.com.zup.casadocodigo.casadocodigo.form.LivroForm;
import br.com.zup.casadocodigo.casadocodigo.repositorios.LivroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.lang.module.ResolutionException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livro")
public class LivroController {
    @PersistenceContext
    private EntityManager manager;
    private LivroRepository livroRepository;

    public LivroController(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<LivroDto> novoLivro(@RequestBody @Valid LivroDto livroDto, UriComponentsBuilder builder){

        Livro livro = livroRepository.save(livroDto.toLivro(manager));
        URI uri = builder.path("/livro/{id}").buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.created(uri).body(new LivroDto(livro));

    }

    @GetMapping
    public ResponseEntity<List<LivroForm>> listarLivros(){
        List<Livro> livros = livroRepository.findAll();
        List<LivroForm> livroForms = new ArrayList<LivroForm>();

        livros.forEach(livro -> {
            livroForms.add(new LivroForm(livro.getId(),livro.getTitulo()));
        });

        return ResponseEntity.ok().body(livroForms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalheLivroForm> detalharLivro(@PathVariable Long id){
        Optional<Livro> optional = this.livroRepository.findById(id);
        if(optional.isPresent()) {
            Livro livro = optional.get();
            Autor autor = this.livroRepository.findAutorByLivroId(id);
            Categoria categoria = this.livroRepository.findCategoriaByLivroId(id);

            return ResponseEntity.ok().body(new DetalheLivroForm(livro,categoria,autor));
        }

        return ResponseEntity.notFound().build();
    }

}
