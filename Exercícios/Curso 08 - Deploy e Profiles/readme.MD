# Roles

Visto que agora precisamos de Roles diferentes por usuários, será necessário criar a Entidade Perfil, e atribuir cada um dos perfis ao respectivos usuários. A classe Perfil precisa implementar a interface GrantedAuthority e precisamos sobrescrever o método getAuthority, no nosso caso somente precisamos retornar o nome do usuário.

Então na nossa classe de Configuração de autorização, extendemos a classe WebSecurityConfigurerAdapter e então sobrecrevemos o método configure, com nossos parâmetros de configuração. Utilizamos então o method `hasRole()` com o nome do Perfil autorizado a utilizar aquele método http e endereço, atribuido

# Suporte de envio de emails.

Como em produção precisamos enviar e-mail, entretanto em desenvolvimento esse recurso não é necessário, dessa maneira criamos uma cópia da classe de envio de emails, e em cada uma delas colocamos a anotação @ActiveProfile informando em qual profile essa classe será executada, então uma classe vai ter o Profile de prod, setado, enquanto a outra classe, terá o Profile de dev setado, dessa maneira na que tem profile de dev, podemos mudar o método para somente dar um Sysout das informação.

Precisamos então agora na execução da aplicação o paramêtro especificando qual o profile que estamos executando.