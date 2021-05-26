

Crio um primeiro teste que verifica se a quantidade de registros que foram criados,
um segundo teste que compara se a reposta é do aluno com o ID informado,
um terceiro teste que compara se a resposta informada é a que está registrada no banco.

Para os testes funcionarem é necessário criar uma sessão com o banco de dados.

preciso criar métodos com a anotação @Before, para criar a conexão com o banco e um outro,
método anotado com @After, para realizar o rolback da transação, isso vai impedir de um teste 
de integração atrapalhar outro teste