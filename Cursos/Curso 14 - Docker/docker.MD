
## Comandos básicos.

* `docker run <nome da imagem>` : Se a imagem não existir localmente, o docker vai buscar a imagem no dockHub, caso ela já exista localmente, ela vai ser criado.

* `docker ps`: Lista os containers em execução.
	* `docker ps -a` Lista todos os containers. 	
* `docker run -it <nome do contaneir>`: Acessa o container escolhido e permite usar o terminal.	
	* `docker run -d <nome do container>`: executa o container em modo *detach* ou seja, não trava o terminal.
	* `docker run -P <nome do container>`: executa o container e atribui uma porta diferente da padrão do container, para executar o redirecionamento.
	* `exit`: para sair do terminal.
	* `docker run --name <nome a ser dado ao container>`: atribui um nome ao container.
	* `docker run -p <porta na maquina local>:<porta do container>`: Define o redirecionamento de portas para o container
	* `docker run -e <nome da variavel>=<valor da variavel>`: define uma variavel de ambiente.
* `docker port <id do container>: Informa quais portas estão sendo utilizadas pelo container.
* `docker start <id do container>`: Inicia o container criado.
* `docker stop <id do container>`: Encerra o container.
	* `docker stop -t 0 $(docker ps -q)`: Encerra todos os containers em execução.
* `docker rm <id do container>`: remove um container
* `docker container prune`: remove todos os containers inativos.
* `docker rmi <id da imagem>`: remove a imagem informada
* `docker inspect <id da imagem>`: traz informações sobre o container.

## Principais estados de um container.

* Running
* Stopped

## Volumes

* `docker run -v "<Caminho da pasta dentro do container>"`: Informa ao docker que vai ser criado um volume em determinado local.
	* `docker run -v "<Caminho da pasta maquina local>:Caminho da pasta dentro do container"`: Informa ao docker que vai ser criado um volume em determinado local.
	* tag `-w <Caminho>` informa qual vai ser o working directory, ao subir o container.

## Construção de imagens.

DockerFile, é o arquivo responsável por conter as instruções para gerar a imagem docker

* `docker build -f <nome do arquivo> -t <nome da tag> <caminho dos arquivos>`: Cria uma imagem docker

## Subindo containers no dockerHub

* `docker login`: pede as credenciais de acesso ao dockerHub.
* `docker push <nome da imagem>`: faz upload da imagem.

## Rede no Docker.

Automaticamente, o docker engine atribui um ip para cada container, dentro da mesa rede.

* `docker network create --driver bridge <nome da rede>`: Cria uma rede em modo bridge.
	* `--network <nome da rede>`: Ao utilizar esse parametro ao criar um container, ele vai entrar na rede informada
	
## Docker Compose
