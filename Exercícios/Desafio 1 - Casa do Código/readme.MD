## Entidade

Criaria a entidade Autor, anotando ela com @Entity, Criarias os atributos ID, Data de Criação de Nome, email e descrição, no ID anotaria com @Id e @GeneratedValue(Identity) com isso defino a chave primária e passo ao banco de dados, a responsabilidade de gerar os ID's, em todas as outras colunas, anoto com @Column, e informando o not null. No atributo Data de Criação colocaria como Final e já inicializaria ele na criação do objeto.

## Repository

Criaria um repositório que estenderia de CrudRepository, e passaria o Autor, com isso tenho o métodos de ler, salvar e deletar um autor

## Controller

Criaria um controle anotando com @RestController, Injetaria o repositório usando @Autowired, Criar um método para cada uma das operações do CRUD, respondendo 200 em caso de sucesso, 201 em caso de criação, 404 em caso de pesquisar algo que não exista