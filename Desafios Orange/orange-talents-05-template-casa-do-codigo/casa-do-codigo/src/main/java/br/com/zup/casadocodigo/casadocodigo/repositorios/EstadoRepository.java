package br.com.zup.casadocodigo.casadocodigo.repositorios;

import br.com.zup.casadocodigo.casadocodigo.entidades.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
}
