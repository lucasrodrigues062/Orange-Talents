package br.com.zup.mercadolivre.opiniao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpiniaoProdutoRepository extends JpaRepository<OpiniaoProduto, Long>{

}
