# Repositório

Para implementar os métodos de inserção, atualização, exibição e remoção de uma avaliação. Crio uma interface e anota ela com @Repository, dessa maneira o spring vai conseguir identificar a interface como um repositório, estendo a outra interface chamada CrudRepository, assim já ganhamos várias implementações padrões.

## Inserção

Como no repositório estendemos CrudRepository, já ganhamos o método `save()`, basta então passar a entidade como pârametro no método que o spring se encarrega da inserção

## Atualização

Como no repositório estendemos CrudRepository, já ganhamos o método `save()`, basta então passar a entidade como pârametro no método que o spring se encarrega da atualização, ele identifica que a classe já está persistida e faz a atualização

## Exibição

Ganhamos por padrão os métodos `findAll()`  e `findById()` o findAll vai nos retornar uma lista opcional do objeto, enquanto o findById nos retorna um opcional, do objeto com aquele id especificado

## Remoção

Ganhamos o método `deleteById()` com ele passo como parâmetro o id, e o spring se encarrega da exclusão

## Busca por Titulo

No nosso Repository, será necessário criar um novo método para isso uso as Querys derivadas do Spring aonde o nome do método será: `findByTitulo(String titulo)` dessa maneira o Spring entende que queremos fazer uma busca pelo atributo Titulo