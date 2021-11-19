CREATE TABLE donation (
	id bigint PRIMARY KEY auto_increment,
	description TEXT,
	donation_type varchar(50),
	quantidade int DEFAULT 0,
	validade DATE,
	descricao_retirada varchar(200),
	user_id int	
);

CREATE TABLE user (
	id bigint PRIMARY KEY auto_increment,
	name varchar(200),
	email varchar(200),
	password varchar(200)
);

CREATE TABLE ROLE(
	id int primary key auto_increment,
	name varchar(200)
);

CREATE TABLE USER_ROLES(
	user_id int,
	roles_id int
);

INSERT INTO ROLE (name) VALUES ('ROLE_ADMIN'), ('ROLE_USER');

INSERT INTO USER_ROLES VALUES 
(1,1),
(2,2),
(3,2)
;

INSERT INTO user (name, email, password) VALUES
('Joao Carlos', 'joao@gmail.com', '$2a$12$iAFC7sgMoPSDNRV.6isc/.F1yT0R0L2tFypGPk6CQaRCFG/PiEjmO'),
('Carla Lopes', 'carla@gmail.com', '$2a$12$iAFC7sgMoPSDNRV.6isc/.F1yT0R0L2tFypGPk6CQaRCFG/PiEjmO'),
('Fabio Cabrini', 'fabio@fiap.com.br', '$2a$12$iAFC7sgMoPSDNRV.6isc/.F1yT0R0L2tFypGPk6CQaRCFG/PiEjmO');


INSERT INTO donation (description, donation_type, quantidade, validade, descricao_retirada, user_id) VALUES 
	('doando muito alimento para os pobres',
	'PERECIVEL', 
	10,
	TO_DATE('23/12/2021','DD/MM/YYYY'),
	'retirar na rua franca pinto n°69 das 14h ate 18h',
	1
	);
	
