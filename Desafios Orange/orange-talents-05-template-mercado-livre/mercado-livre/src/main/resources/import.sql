
Insert into categoria (nome) values ('Padrao')
Insert into usuario (data_cadastro,login,senha) values (now(), 'lucas@lucas.com', '$2a$10$x5PlHmRK5FHxL9sjNSJnk.c.RYVlmjPFEYxgkifHqPJSOZKeIicO.')
INSERT INTO `mercadolivre`.`produto` (`data_cadastro`,`descricao`,`nome`,`qtd`,`valor`,`categoria_id`,`usuario_id`) VALUES (now(),'Playstation 4','Playstation',100,2500.00,1,1);
INSERT INTO `mercadolivre`.`caracteristicas_produto`(`descricao`,`nome`,`produto_id`)VALUES('branco','cor',1);
INSERT INTO `mercadolivre`.`caracteristicas_produto`(`descricao`,`nome`,`produto_id`)VALUES('110v','voltagem',1);
INSERT INTO `mercadolivre`.`caracteristicas_produto`(`descricao`,`nome`,`produto_id`)VALUES('gratis','frete',1);
INSERT INTO `mercadolivre`.`imagem_produto`(`link`,`produto_id`)VALUES('http://bucket.io/Cronograma-Turma 5.pdf',1);
INSERT INTO `mercadolivre`.`imagem_produto`(`link`,`produto_id`)VALUES('http://bucket.io/download.png',1);
INSERT INTO `mercadolivre`.`pergunta_produto`(`data_pergunta`,`produto_id`, `usuario_id`, `titulo`)VALUES(now(),1, 1, 'vem com quantos controles?');
INSERT INTO `mercadolivre`.`pergunta_produto`(`data_pergunta`,`produto_id`, `usuario_id`, `titulo`)VALUES(now(),1, 1, 'quanto fica o frete para Macapa?');
INSERT INTO `mercadolivre`.`opiniao_produto`(`descricao`,`nota`,`titulo`,`produto_id`,`usuario_id`)VALUES('Produto nota 10',5,'Valeu a pena',1,1);
INSERT INTO `mercadolivre`.`opiniao_produto`(`descricao`,`nota`,`titulo`,`produto_id`,`usuario_id`)VALUES('Produto muito caro',2,'Pessimo custo beneficio',1,1);
INSERT INTO `mercadolivre`.`opiniao_produto`(`descricao`,`nota`,`titulo`,`produto_id`,`usuario_id`)VALUES('Vendedor, sacana, nao entregou o produto',1,'vendedor nao entregou',1,1);

