INSERT INTO PESSOA(nome, cpf, salario, sexo)
VALUES
	('Fulano de Tal',      '01898745028', '1500',    'M'),
	('Guilherme Silveira', '95346033840', '3754.50', 'M');

INSERT INTO ENDERECO(pais, estado, cidade, cep, rua)
VALUES
	('Brasil', 'Rio Grande do Sul', 'Pelotas', '96075230', 'Visconde de Abaete'),
	('Brasil', 'São Paulo',         'Olímpia', '15400971', 'Washington Luiz');
	
INSERT INTO PESSOA_ENDERECO(pessoa_cpf, endereco_cep)
VALUES
	('01898745028', '96075230'),
	('95346033840', '15400971');
	
INSERT INTO PRODUTO(id, descricao, preco_unitario, status)
VALUES
	(1, 'Arroz',    '10.00', TRUE),
	(2, 'Feijão',   '5.00',  TRUE),
	(3, 'Açúcar',   '3.50',  TRUE),
	(4, 'Lentilha', '4.00',  TRUE),
	(5, 'Tapioca',  '3.99',  TRUE),
	(6, 'Cuzcuz',   '12.59', TRUE);
	
INSERT INTO PEDIDO(id, data_criacao, total)
VALUES
	(1, '2021-07-13 17:32:06', '22.50'),
	(2, '2021-12-12 7:30:59', '17.49');
	
INSERT INTO PEDIDO_PRODUTO(pedido_id, produto_id)
VALUES
	(1, 1),
	(1, 2),
	(1, 3),
	(1, 4),
	(2, 3),
	(2, 5),
	(2, 1);
