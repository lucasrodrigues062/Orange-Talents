package br.com.zup.casadocodigo.casadocodigo.repositorios;

import br.com.zup.casadocodigo.casadocodigo.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
