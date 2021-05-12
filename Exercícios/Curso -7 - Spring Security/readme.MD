
# Cacheamento.

Visto que os dados de Alunos, tem sua mudança muito rara, precisamos habilitar o cache, para isso adicionamos a dependência de cache do Spring. Em seguida adicionar uma anotação no método main da nossa aplicação chamada @EnableCaching. Após isso se torna necessário adicionar a anotação @Cacheable, nos métodos que trazem o registro de Alunos, também se torna necessário em todos os métodos que existam alterações de Alunos, adicionar a anotação @CacheEvict informando o nome do cache a ser apagado, dessa maneira, os métodos de alteração e exclusão apagam o cache gerado pelos métodos de listagem.

# Paginação

A paginação é um recurso importante de se utilizada, pelo motivo de quando temos uma API, e essa API, toda vez que chamar todos os Alunos existentes, seria um esforço muito grande no banco de dados, e isso afeta grandemente a performance da aplicação

passar um objeto Pageable, como parâmetro no método, junto com a anotação @PageableDefault, nos parâmetros da anotação eu passo as configurações de paginação, como orde, pagina inicial e tamanho. No nosso método devemos retornar agora uma `Page<Aluno>` e nas nossas consultas ao banco, enviamos a nossa paginação como parâmetro. Assim o usuário consegue passar parâmetros via QueryParameter

# Autorização

A autenticação via webToken, se baseia na premissa, que não existe estado, sabendo disso, o usuário ao tentar logar na aplicação, é gerado um token, e esse token tem um tempo determinado, ou seja, após expirado o token será necessário o usuário realizar a autenticação novamente, o Cliente(navegador), vai armazenar esse token, seja em mémoria, cache ou cookie, então toda vez que o usuário tentar acessar algum recurso que precise de autorização esse token, deve ser enviado no Header da requisição.

No lado da API, é necessário fazer a configuração dos recursos, aonde podemos definir quais recursos, precisam ou não de autorização, qual o tipo de autenticação, qual o tipo da chave token e a validade da chave.