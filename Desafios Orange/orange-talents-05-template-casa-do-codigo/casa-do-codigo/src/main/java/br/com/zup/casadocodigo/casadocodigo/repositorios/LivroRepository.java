package br.com.zup.casadocodigo.casadocodigo.repositorios;


import br.com.zup.casadocodigo.casadocodigo.entidades.Autor;
import br.com.zup.casadocodigo.casadocodigo.entidades.Categoria;
import br.com.zup.casadocodigo.casadocodigo.entidades.Livro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    @Query("SELECT a FROM Livro l JOIN l.autor a WHERE l.id = :id")
    public Autor findAutorByLivroId(Long id);

    @Query("SELECT c FROM Livro l JOIN l.categoria c WHERE l.id = :id")
    public Categoria findCategoriaByLivroId(Long id);
}
