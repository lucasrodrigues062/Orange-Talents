
/* CRIANDO DATABASE */
create DATABASE SUCOS;

/* Deletando Banco de dados */

/* Drop database Sucos; */

create table tbCliente (cpf varchar(11), nome varchar(100), endereco1 varchar(150), endereco2 varchar(150),
bairro varchar(50), cidade varchar(50), estado varchar(50), cep varchar(8), idade smallint, sexo varchar(1),
limite_credito float, volume_compra float, primeira_compra bit(1)
);

create table tbProduto ( codigo varchar(20), nome varchar(150), embalagem varchar(50), tamanho varchar(50),
 sabor varchar(50), preco float
);

