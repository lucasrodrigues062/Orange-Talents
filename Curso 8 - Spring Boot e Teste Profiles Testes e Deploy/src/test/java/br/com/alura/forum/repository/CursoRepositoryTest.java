package br.com.alura.forum.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.alura.forum.modelo.Curso;

@RunWith(SpringRunner.class)
@DataJpaTest
// A configuracao abaixa, forca o spring a utilizar um banco "normal" ao fazer testes
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// Forca o Spring a utilizar um profile especfico
//@ActiveProfiles("test")
public class CursoRepositoryTest {
	
	@Autowired
	private CursoRepository repository;
	
	
	
	@Test
	public void deveCarregarCursoPorNome() {
		String nomeCurso = "HTML 5";
		Curso  curso = repository.findByNome(nomeCurso);
		
		Assert.assertNotNull(curso);
		Assert.assertEquals(nomeCurso, curso.getNome());
		
	}

	@Test
	public void naoDeveCarregarCursoNaoCadastrado() {
		String nomeCurso = "JPA ";
		Curso  curso = repository.findByNome(nomeCurso);
		
		Assert.assertNull(curso);
		
		
	}
}
