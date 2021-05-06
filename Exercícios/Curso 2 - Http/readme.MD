# Pesquisa

Para a parte de pesquisa vamos utilizar o verbo HTTP GET, vamos passar os dados da pesquisa como parâmetros na url, decidi adotar essa abordagem, pois melhora o rankeamento SEO da página, apesar de ser algo que não vimos no curso, também o navegador guarda essa url no histórico, sendo assim, mais rápido a pesquisa pelo usuário

## Cadastro

Para a parte de cadastro, iremos utilizar o verbo HTTP POST, dessa maneira nosso dados cadastrais vão ser enviados dentro da requisição e não na URL, trazendo uma camada de segurança.

No header da requisição, a opção "Accept", deve vir preenchida com o tipo de dados esperado para aquela requisição, seja JSON, ou form-url-encoded

# HTTPS

Ao fazer tentativa de acesso a plataforma, redirecionamos a requisição para a porta 443 e utilizamos HTTPS ao invés de HTTP, visto que estamos trabalhando com dados sensíveis como senhas.