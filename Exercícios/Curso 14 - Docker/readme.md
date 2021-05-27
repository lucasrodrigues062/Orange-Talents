
Primeiro passo seria criar um DockerFile, com as informações de build para criar uma imagem para cada uma das aplicações.
Depois criaria um arquivo docker-compose e nele informo os dados para criar uma network no docker, em seguida
informo os services, e defino a porta local que cada container vai usar, utilizando a tag "ports", informo nos services das aplicações o 
dockerfile correspondente a cada aplicação, e no container que será nosso banco de dados, só informo a imagem que ele utilizará(mongo, mysql, postgres, etc...)