package br.com.zup.casadocodigo.casadocodigo.repositorios;

import br.com.zup.casadocodigo.casadocodigo.entidades.Autor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    public Optional<Page<Autor>> findByEmail(String email, Pageable paginacao);

    public Optional<Autor> findByEmail(String email);



}
