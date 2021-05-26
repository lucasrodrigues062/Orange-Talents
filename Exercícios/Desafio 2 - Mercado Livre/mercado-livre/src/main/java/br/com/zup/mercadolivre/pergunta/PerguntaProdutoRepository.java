package br.com.zup.mercadolivre.pergunta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerguntaProdutoRepository extends JpaRepository<PerguntaProduto, Long> {

}
