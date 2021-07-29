package br.com.zup.mercadolivre.categoria;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	Optional<Categoria> findByNome(String nome);
	
	Optional<Categoria> findByNomeAndCategoriaMaeIsNull(String nome);
	
	Optional<Categoria> findByNomeAndCategoriaMaeId(String nome, Long id);
}
