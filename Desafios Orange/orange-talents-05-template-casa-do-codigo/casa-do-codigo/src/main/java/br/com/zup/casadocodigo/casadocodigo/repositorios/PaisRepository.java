package br.com.zup.casadocodigo.casadocodigo.repositorios;

import br.com.zup.casadocodigo.casadocodigo.entidades.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {
}
