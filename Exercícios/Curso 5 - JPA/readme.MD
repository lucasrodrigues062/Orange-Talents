# Classes

## Aluno

A classe Aluno, vai contar nossos dados de alunos, anotamos a classe com a anotação @Entity. Criamos os seguintes atributos

* Id: Será nossa chave primária, anotamos ela com @Id informando a JPA que ela vai ser nossa chave primária, campo de tipo BigInteger, usamos a anotação @GeneretadValue com a estratégia Identity, para criar números sequenciais.

* nome: Atributo tipo String, anotamos ela com a anotação @Column e definimos o valor `lenght` para 30.

* email: Atributo tipo String, anotamos ela com a anotação @Column e definimos o valor `lenght` para 30.

* idade: atributo tipo Integer.

## Avaliação 

A classe Avaliação, vai contr nossos dados de valiações, anotamos a classe com a anotação @Entity. Criamos os seguintes atributos

* Id: Será nossa chave primária, anotamos ela com @Id informando a JPA que ela vai ser nossa chave primária, campo de tipo BigInteger, usamos a anotação @GeneretadValue com a estratégia Identity, para criar números sequenciais.

* titulo: Atributo tipo String, anotamos ela com a anotação @Column e definimos o valor `lenght` para 30.

* descricao: Atributo tipo String, anotamos ela com a anotação @Column e definimos o valor `lenght` para 30.

* resposta: Atributo tipo String, que vai conter a resposta da avaliação.

## Resposta

Essa classe vai conter as respostas e correção das avaliações, teremos os seguintes atributos:

* Id: Será nossa chave primária, anotamos ela com @Id informando a JPA que ela vai ser nossa chave primária, campo de tipo BigInteger, usamos a anotação @GeneretadValue com a estratégia Identity, para criar números sequenciais.

* aluno: Esse atributo, vai conter uma instância de aluno, necessário marcar a anotação @ManyToOne, pois um Aluno, pode ter várias respostas, com isso vai ser criado o link entre essas 2 tabelas.

* avaliação: Esse atributo, vai conter uma instância de aluno, necessário marcar a anotação @ManyToOne, pois uma Avaliação, pode ter várias respostas, com isso vai ser criado o link entre essas 2 tabelas.

* resposta: Esse atributo de tipo String vai conter a resposta da avaliação.

* nota: Campo tipo Double, que vai conter a nota que o aluno deu na avaliação.

# Persistindo os dados.

## Aluno
Para persistir Aluno, Criamos uma EntityManagerFactory, e apartir dela, criamos um EntityManager.

Criamos uma instância de Aluno e em seguida.
Abrimos a transação, utilizamos o método persist, e depois commitamos a transação.

## Avaliação

Para persistir Avaliação, Criamos uma EntityManagerFactory, e apartir dela, criamos um EntityManager.

Criamos uma instância de Avaliação e em seguida.
Abrimos a transação, utilizamos o método persist, e depois commitamos a transação.

## Resposta

Para persistir a Resposta, Criamos uma EntityManagerFactory, e apartir dela, criamos um EntityManager.

Criamos uma instânica de Avaliação, Aluno e em seguida uma de reposta, setamos as instâncias de Aluno e Avaliação, na resposta, depois comittamos a transação, acontece que quando vamos associar as instâncias de aluno e avaliação a transação já tem que estar aberta, para as intâncias se tornarem "Managed".

## Auto Correção

Para persistir a auto correção, precisamos, primeiro abrir a transação, carregar a resposta, e depois setarmos a nota de correção e por último, fechamos a transação

# Consultas

## Carregar aluno através da correção.

Podemos utilizar JPQL, relacionando as tabelas de resposta e aluno, passando o id da correção.

## Carregar respostas através de Aluno.

Mais uma vez usamos JPQL, dessa vez relacionamos a tabela aluno e respostas, passando como parametro o nome do aluno

