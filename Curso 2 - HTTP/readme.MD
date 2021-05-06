
# Aula 1:

HTTP(HyperText Transfer Protocol): é o protocolo de comunicação utilizado na web, para comunicar-se

# Aula 2:

HTTPS: é o http com uma camada de segurança usando SSL.

Certificado digital, vai servir para provar a identidade da entidade.

No navegador vai existir a chave pública, enquanto no servidor vai existir a chave privada, ao fazer uma requisição o navegador, criptgrafa com a chave pública e então ao chegar no servidor ele descriptografa usando a chave privada.

Aprendemos no vídeo que o HTTPS usa uma chave pública e uma chave privada. As chaves estão ligadas matematicamente, o que foi cifrado pela chave pública só pode ser decifrado pela chave privada. Isso garante que os dados cifrados pelo navegador (chave pública) só podem ser lidos pelo servidor (chave privada). Como temos duas chaves diferentes envolvidas, esse método de criptografia é chamado de criptografia assimétrica. No entanto, a criptografia assimétrica tem um problema, ela é lenta.



Por outro lado, temos a criptografia simétrica, que usa a mesma chave para cifrar e decifrar os dados, como na vida real, onde usamos a mesma chave para abrir e fechar a porta. A criptografia simétrica é muito mais rápida, mas infelizmente não tão segura. Como existe apenas uma chave, ela ficará espalhada pelos clientes (navegadores) e qualquer um, que tem a posse dessa chave, pode decifrar a comunicação.



Agora, o interessante é que o HTTPS usa ambos os métodos de criptografia, assimétrica e simétrica. Como assim? Muita calma, tudo o que aprendemos é verdade! Só faltou o grande final :)

No certificado, vem a chave pública para o cliente utilizar, certo? E o servidor continua na posse da chave privada, ok? Isso é seguro, mas lento e por isso o cliente gera uma chave simétrica ao vivo. Uma chave só para ele e o servidor com o qual está se comunicando naquele momento! Essa chave exclusiva (e simétrica) é então enviada para o servidor utilizando a criptografia assimétrica (chave privada e pública) e então é utilizada para o restante da comunicação.

Então, HTTPS começa com criptografia assimétrica para depois mudar para criptografia simétrica. Essa chave simétrica será gerada no início da comunicação e será reaproveitada nas requisições seguintes. Bem-vindo ao mundo fantástico do HTTPS :)

# Aula 3

DNS(Domain Name System): responsável por encontrar o ip do serviço, dado o nome(domínio) do mesmo.

porta padrão https é a 443

tudo que vem após a porta em um endereço, é chamado de recurso.

uma URL, sempre começa com o nome do protocolo

Muitas vezes, desenvolvedores usam a sigla URI (Uniform Resource Identifier) quando falam de endereços na web. Alguns preferem URL (Uniform Resource Locator), e alguns misturam as duas siglas à vontade. Há uma certa confusão no mercado a respeito e mesmo desenvolvedores experientes não sabem explicar a diferença. Então, qual é a diferença?

Resposta 1 (fácil): Uma URL é uma URI. No contexto do desenvolvimento web, ambas as siglas são válidas para falar de endereços na web. As siglas são praticamente sinônimos e são utilizadas dessa forma.

Resposta 2 (mais elaborada): Uma URL é uma URI, mas nem todas as URI's são URL's! Existem URI's que identificam um recurso sem definir o endereço, nem o protocolo. Em outras palavras, uma URL representa uma identificação de um recurso (URI) através do endereço, mas nem todas as identificações são URL's.

Humm ... ficou claro? Não? Vamos dar um exemplo! Existe um outro padrão que se chama URN (Uniform Resource Name). Agora adivinha, os URN's também são URI's! Um URN segue também uma sintaxe bem definida, algo assim urn:cursos:alura:course:introducao-html-css. Repare que criamos uma outra identificação do curso Introdução ao HTML e CSS da Alura, mas essa identificação não é um endereço.


Novamente, a resposta 2 vai muito além do que você realmente precisa no dia a dia. Normalmente URL e URI são usados como sinônimos.


# Aula 4

O Http é *STATELESS* ele não sabe qual foi a requisição anterior, e também não sabe qual será a próxima.

Uma sessão é guardada, em cookies, é nele que fica as informações da sessão.

Uma sessão HTTP nada mais é que um tempo que o cliente permanece ativo no sistema! Isso é parecido com uma sessão no cinema. Uma sessão, nesse contexto, é o tempo que o cliente usa a sala no cinema para assistir a um filme. Quando você sai da sala, termina a sessão. Ou seja, quando você se desloga, a Alura termina a sua sessão.

Quando enviamos uma requisição HTTP, todos os dados para que ela seja respondida devem ser enviados. Mas e o e-mail e a senha? Quando o login é feito, a Alura tem certeza de que um usuário existe e gera uma identificação quase aleatória pra esse usuário, lembra? E esse número fica salvo em um arquivo especial, chamado cookie, que é gerado e enviado por cada site :)

Quando falamos de Cookies na verdade queremos dizer Cookies HTTP ou Cookie web. Um cookie é um pequeno arquivo de texto, normalmente criado pela aplicação web, para guardar algumas informações sobre usuário no navegador. Quais são essas informações depende um pouco da aplicação. Pode ser que fique gravado alguma preferência do usuário. Ou algumas informações sobre as compras na loja virtual ou, como vimos no vídeo, a identificação do usuário. Isso depende da utilidade para a aplicação web.

# Aula 5



# Aula 6

# Aula 7

# Aula 8

No http2 todo o conteúdo da resposta, já é comprimido com gzip.

A requisição e a resposta, são trafegados em dados binários.

Os cabeçalhos são comprimidos com o HPACK

Http2 por padrão já criptografa os dados com TLS

no Http2 os cabeçalhos são stateful, ou seja os cabeçalhos conseguem guardar estado

no Http2 temos o conceito de server push, o servidor, percebendo que é necessário enviar mais dados na requisição, não espera o cliente pedir, e já envia todos os dados necessários.