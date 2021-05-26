
# Conteúdo mais profundo sobre testes.

* Teste de Unidade: Normalmente considerado quando testamos um ponto de entrada o seja um método.
* Teste de Integração: São quando testamos ambientes externos, como banco de dados ou API's.
* Teste de Sistemas: São quando colocamos uma entrada, e esperamos uma saída em um sistema completo.

## Técnicas de testes

### Specification Based Test

* Identificar possíveis parâmetros.
* Derivar as caracteristicas de cada parâmetro.

### Boundary Test

Consiste em testar as bordas, por exemplo, se tenho um `if` testar entrando na condicional ou não

### Structural Test

Basicamente significa cobertura de código, ou seja métrica para quando devo parar de testar.

structurar por linhas de código, não é uma métrica precisa.

Structural testing por branch, seria testar todos os pontos que existam tomadas de decisão.

### Self Testing

**Design By Contract**
