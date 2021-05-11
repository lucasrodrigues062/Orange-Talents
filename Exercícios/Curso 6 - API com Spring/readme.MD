# Qual a utilidade do Spring Boot.

O Spring boot vem com um conjunto de ferramentas, que nos permitem criar microserviços de maneira muito rápida e coesa, não somente microserviços, mas é possível também criar estruturas mais complexar

# Cadastro de Aluno

* Criar classe aluno, com os atributos id, nome, idade e email onde o id seria a chave primária, anotando a classe com @Entity, para a JPA mapear e persistir os dados.

* Criar a interface AlunoRepository, sendo ela o repósitorio de acesso ao banco de dados, extendendo a JPA repository, passando a classe Aluno e a chave primária que seria o id.

* Criar a classe AlunoDto, ela será a responsável por fazer a validação dos dados, por isso nos atributos alunoe também de expor e receber eles.
anotamos os tributos nome, email e idade com a anotacao @NotNull e @NotEmpty, para os valores não virem vazios ou em branco, anotamos email com a anotação @Email, para o validator, validar se é um email, na idade usamos a anotação @Min e colocamos o valor 18, para só permitir cadastros com idade superior a 18

## Controller

* Crio a classe AlunoController, e anotamos com @RestController, assim o Spring já assume que a classe é um controller tipo REST. usamos o @Autowired, para injetar a dependêcia do repositório AlunoRepository que contém nossos métodos de CRUD

* Criar método cadastrar(), que retorna uma ResponseEntity de AlunoDto, recebemos como pârametro um AlunoDto, anoto o parâmetro com @RequestBody, para o Spring procurar os dados, no corpo da requisição, ainda adiciono a anotação @Valid, o outro parâmetro será UriComponentsBuilder para construir o URI de retorno, anotamos o método com @PostMapping.

    * Então convertemos o AlunoDto recebido, em um aluno, então usamos o método save, para persistir os dados, só então devolvemos no Header do retorno a URI do aluno cadastrado e no corpo os dados do aluno cadastrado usando o ResponseEntity.ok().

    * Caso não passe na validação ou der um erro na persistência, Retornamos um ResponseEntity.badRequest().

* Criar método detalhar() que retorna uma ResponseEntity de AlunoDtoSemIdade, recebemos o id do aluno como variavel na url por isso anotamos com @GetMapping("/aluno/{id}"), na assinatura do parâmetro anoto com @PathVariable e o nome do parâmetro, para o spring saber que esse parâmetro está vindo na url.

    * Usamos o método findById() que irá nos retornar um optional, e então validamos, se o Optional estiver presente, converto de Aluno para AlunoDtoSemIdade e então retorno o status 200 com o AlunoDtoSemIdade no corpo da requisição.

    * Caso não seja encontrado o aluno, retornamos o ResponseEntity.notFound, que seria o status 404