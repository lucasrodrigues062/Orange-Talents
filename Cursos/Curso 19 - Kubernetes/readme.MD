
O Kubernetes veio, para resolver o problema de escalação horizontal
pod = é o encapsulamento de um container

Os comandos necessários para a instalação e inicialização do cluster no Linux podem ser obtidos abaixo:

Primeiro para o kubectl:

sudo apt-get install curl -y
curl -LO "https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl"
chmod +x ./kubectl
sudo mv ./kubectl /usr/local/bin/kubectl
Agora para o minikube:

curl -Lo minikube https://storage.googleapis.com/minikube/releases/v1.12.1/minikube-linux-amd64 \ && chmod +x minikube
sudo install minikube /usr/local/bin/

## Pods

* Dentro de um Pod, pode existir um ou mais containers.
* Containers dentro de um pod, possuem o mesmo IP, só diferenciando a porta, ou seja consigo acessar multiplos sistemas usando localhost
* Um Pod, pode se conectar pela rede com outro Pod.

Validador de Yaml para kubernets: https://kubeyaml.com/

Kubernetes Cheat Sheet: https://linuxacademy.com/site-content/uploads/2019/04/Kubernetes-Cheat-Sheet_07182019.pdf - https://github.com/RehanSaeed/Kubernetes-Cheat-Sheet

## ReplicaSets

são os responsáveis por replicar os Pods, quando um pod cai, ele vai subir um clone.

