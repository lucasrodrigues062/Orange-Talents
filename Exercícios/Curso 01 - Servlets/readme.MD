

# Parte 1

Criar as classes que servirão de dados para a nossa aplicação são elas:

## Autor

### Atributos

Nessa Classe vamos ter os atributos privados que serão:

* **id[Integer]:** Esse atributo vai ser responsável por guardar um número único que identifique cada instância do objeto

* **nome[String]:** Esse atributo vai armazenar o nome do autor.

* **email[String]:** Esse atributo vai armazenar o email do autor.
 

Também teremos getter e setters sendo eles para cada um dos atributos. Nenhuma lógica de negócio necessária neles a princípio.

## Banco

Nessa classe vamos armazenar em memória os dados dos nosso autores, iremos ter 2 atributos

### Atributos

* **lista[Static List< autor >]:** Esse vai ser o array responsável por armazenar nosso dados em mémoria, por isso a necessidade de ser estático, assim os dados não vão ser diferentes a cada instância da classe.

* **chave[Static Integer]:** Esse vai ser o nosso identificados, cada vez que um autor for inserido, será incrementado mais uma unidade a esse número, por esse motivo ele é estático, para esse número não se repetir, ele já inicializado com o valor 0.

### Métodos

* **adicionar(Autor):** Esse método não tem retorno e serve para adicionar um novo autor na nossa classe *Banco*, a classe simplesmente insere no atributo *Autor.id* do objeto que foi passado como parâmetro a próxima chave, que será o id do objeto, em seguida adiciona no atributo da classe *Banco.lista*.

* **remove(Integer):** Esse método não tem retorno, sua funcionalidade é remover um registro do nosso banco de dados em mémoria, ou seja elimina um registro do atributo *Banco.lista*,
Criamos um *Iterator* para percorrer os registros do atributo *Banco.list* em seguida criamos um laço com `while` usando o método do *Iterator* `hasNext()`, a cada interação do laço, fazemos uma validação usando o `if`, caso o *Autor.id* seja igual ao *Integer* passado no parâmetro do método então utilizamos o método *Iterator.remove()* para remover o registro.
Precisamos utilizar essa abordagem com o Iterator, pois ao tentar iterar, pelo objeto utilizando `for` ou `forEach`, nos retornaria um erro, pois esses métodos não conseguem editar aquilo que iteram.

* **buscarPeloId(Integer):** Esse método retorna uma instância de
*Autor*. Aqui criamos um laço `for` para percorrer *Banco.lista*, e fazemos uma validação usando o `if`, caso o *Autor.id* seja igual ao *Integer* passado no parâmetro do método então retornamos o *Autor* encontrado, caso não encontre retornamos `null`

* **getAutores():** Esse método é o mais simples de todos, nele somente retornamos, o atributo *Banco.lista* ou seja retornamos uma lista de *Autor*

Com isso concluímos as classes de registro, em seguida vamos para os Servlets.

# Parte 2

Agora vamos criar os nosso Servlets, eles seram os responsáveis por lidar com as requisições, criaremos um Servlet para cada uma das operações do CRUD, gosto de adotar essa abordagem, pois cada um dos Servlets, terão suas obrigações bem definidas.

## NovoAutor

Nessa classe lidaremos com a criação de um novo Autor. Para isso precisamos estender a interface *HttpServlet*, com isso ganhamos toda a parte de tratamento de requisição HTTP. Adicionamos a anotação `@WebServlet` ela é responsável por definir a rota da nossa aplicação, então adicionamos nela o valor `/novoAutor`.

Nessa clase, só iremos aceitar o método POST, visto que estamos cadastrando um Autor, e o POST é o metodo indicado, para inserir registros.


### Métodos

* **doPost(HttpServletRequest, HttpServletResponse):** Esse método vai conter nossa lógica de negócio, lembrando que na assinatura do método temos dois `throws` são eles: ServletException e IOException

  Começamos requisitando os pârametros que foram enviados pelo formulário enviado pelo navegador do cliente para isso utilizamos o método `HttpServletRequest.getParameter(<nome do pârametro>)`, fazemos isso para cada um dos pârametros enviados pelo formulário, como todos os pârametros são `String`(nome e email), então não precisamos fazer nenhum Parse da informação.

  Criamos uma instância de *Autor*, e utilizamos os *setters*, para inserir os dados de email e nome, então em seguida criamos uma instância de *Banco* e utilizamos o método `Banco.adicionar(autor)` para adicionar o *Autor* em nossa lista em mémoria.

  Por último chamamos o método `HttpServletResponse.sendRedirect(listaAutor)` e enviamos um redirect para o Servlet *listaAutor*, com isso impedimos o reenvio de formulário pelo navegador, evitando a criação de registros repetidos, devido o reenvio de formulário do navegador e já mostramos em seguida todos os objetos criados.

## ListaAutor

Nessa classe lidaremos com a listagem de um novo Autor. Para isso precisamos estender a interface *HttpServlet*, com isso ganhamos toda a parte de tratamento de requisição HTTP. Adicionamos a anotação `@WebServlet` ela é responsável por definir a rota da nossa aplicação, então adicionamos nela o valor `/listaAutor`.

Nessa clase, só iremos aceitar o método GET, visto que estamos listando Autores, e o GET é o metodo indicado, para carregar registros.

### Métodos

* **doGet(HttpServletRequest, HttpServletResponse):** Esse método vai conter nossa lógica de negócio, lembrando que na assinatura do método temos dois `throws` são eles: ServletException e IOException
  
  Criamos uma nova instância de *Banco*, em seguida criamos uma instância de `List<Autor>` que recebe o resultado do método `Banco.getAutores()`.

  Então "penduramos" na requisição a nossa listagem de autores, utilizando o método `HttpServletRequest.setAttribute(<nome do parametro>, <variavel para enviar para o jsp>)`

  Por último Criamos um *RequestDispatcher* e preenchemos com o retorno do método `HttpServletRequest.getRequestDispatcher(<endereco para redirecionar>)` colocando o valor da noss JSP de *listaEmpresas* e então enviamos um *Dispatcher* utilizando o método `RequestDispatcher.forwars(request, response)` para enviarmos ao cliente a nossa página, com as variavéis preenchidos.

## AtualizaAutor

Nessa classe lidaremos com a atualização de dados de um Autor. Para isso precisamos estender a interface *HttpServlet*, com isso ganhamos toda a parte de tratamento de requisição HTTP. Adicionamos a anotação `@WebServlet` ela é responsável por definir a rota da nossa aplicação, então adicionamos nela o valor `/atualizaAutor`.

Nessa clase, só iremos aceitar o método GET, visto que estamos atualizando Autores, e nossa JSP, envia os dados via GET pois precisamos trazer os dados do autor, para serem editados pelo cliente(navegador).

## Métodos

* **doGet(HttpServletRequest, HttpServletResponse):** Esse método vai conter nossa lógica de negócio, lembrando que na assinatura do método temos dois `throws` são eles: ServletException e IOException.

  Primeiro passo seria pegar o id que veio do formulário e localizar o registro no nosso *Banco*. Para isso utilzado o método `.getParameter("id")`, e atríbuimos a uma variável, em seguida realizamos o parse, dessa String para inteiro, utilizando o método `Integer.valueOf("id")`

  Agora em posse do id do autor, criamos uma nova instância de *Banco*, e em seguida criamos uma instânca de *Autor*, que recebe o retorno do método `Banco.buscarPeloId(id)`. Utilizamos os setters, para atualizar o nome e o email do autor.

  Por último enviamos um redirect, para o Servlet ListaEmpresa.

## RemoveAutor

Nessa classe lidaremos com a remoção de um Autor. Para isso precisamos estender a interface *HttpServlet*, com isso ganhamos toda a parte de tratamento de requisição HTTP. Adicionamos a anotação `@WebServlet` ela é responsável por definir a rota da nossa aplicação, então adicionamos nela o valor `/removeAutor`.

Nessa clase, só iremos aceitar o método GET, visto que estamos removendo Autores, e nossa JSP, envia os dados via GET pois precisamos trazer o id autor, para ser excluído.

## Métodos

* **doGet(HttpServletRequest, HttpServletResponse):** Esse método vai conter nossa lógica de negócio, lembrando que na assinatura do método temos dois `throws` são eles: ServletException e IOException.

  Primeiro passo seria pegar o id que veio do formulário e localizar o registro no nosso *Banco*. Para isso utilzado o método `.getParameter("id")`, e atríbuimos a uma variável, em seguida realizamos o parse, dessa String para inteiro, utilizando o método `Integer.valueOf("id")`

  Agora em posse do id do autor, criamos uma nova instância de *Banco*, e em utilizamos o método `Banco.removeAutor(id)`.

  Por último enviamos um redirect, para o Servlet ListaEmpresa.


# Parte 3

Agora vamos criar nossas JSP(Java Server Pages), vai ser nelas que vai ter o que será mostrado, para o usuário. elas ficam contidas na pasta WebContent do nosso projeto, utilizaremos também o jstl(Java Standard Taglib), para isso precisamos adicionar essa depêndencia ao nosso projeto, em posse disso podemos começar.

## formNovoAutor

Esse é o nosso formulário de cadastro de autores, então precisamos criar dois `input` um com o nome a ser preenchido outro com o email a ser preenchido, e um botão de enviar, importante no início do arquivo inserir a importação da biblioteca *jstl*, na *action* do nosso formulário, fazemos um POST para o nosso Servlet responsável pelo cadastro(/novoAutor)


## listaAutores

Esse é o nosso formulário que traz a listagem de autores, para isso primeiro precisamos importar as bibliotecas do nosso projeto, para isso utilizamos a tag `<%@ page import=<endereco> %>` importamos também o jstl através da tag `<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>`

Como definimos o prefixo c, para a nossa taglib, podemos criar um `forEach` para mostrar a lista de autores, pois a nossa variável autor, foi enviada pelo Servlet, ao lado de cada registo, criamos dois links, um para editar o registro, e outro para remover.

Caso o usuário clique no link para editar, ele será redirecionado, para a página de atualização de autor, caso ele clique em remover, enviamos então uma requisição para a Servlet, /removeAutor, e o registro é removido.

## formEditaAutor

Esse é o formulário que utilizamos para editar um autor, quando clicamos no link de editar na página de lista de autores, somos encaminhados, para essa JSP.

importamos também o jstl através da tag `<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>`

Agora criamos o formulário, preenchendo com os dados que retornaram pelo Servlet, nesse momento o usuário fica livre, para editar os dados do autor, e ao terminar clica em enviar.

Ao enviar, enviarmos o formulário, enviamos via POST para a rota /alteraEmpresa, ela se encarrega de atualizar os dados, e então retornar para a lista de autores.
  
