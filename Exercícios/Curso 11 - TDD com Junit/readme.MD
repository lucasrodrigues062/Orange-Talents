


Crio uma Teste que verifica se uma exceção foi lançada, tanto para a Avaliação nula, como o Aluno nulo,
para isso anoto o método com @Test(expected = IllegalArgumentException.class), Crio uma instância de 
Resposta questão para cada uma das situações acima.

Crio um novo Teste anotando com @Test(expected = IllegalArgumentException.class), esse vai testar se a nota
é menor ou maior que 10, Crio então uma instância para cada uma das situações então executo o método.

Até agora só testei as falhas, então em seguida crio os teste de sucesso.

Crio um Teste anoto com @Test e uso o Assert.asserEquals, para testar se a nota que foi passada no objeto está correta.

Crio um outro Teste, Com a Avaliação e Aluno preenchidos e uso o Assert.asserEquals para verificar se eles realmente estão
presentes