<img src="https://img.shields.io/badge/STATUS-CONCLUÍDO-green"/>

# Projeto CRUD Spring Boot

## Sobre o Projeto:

Uma aplicação web modelada no padrão MVC em Java e construída com o Spring Boot, o projeto conta com uma estrutura de CRUD, no qual os dados estão sendo persistidos em um banco de dados (MySQL). Na camada de visualização foi utilizado HTML, CSS, o framework Bootstrap e o template Thymeleaf.  Também foi utilizado o JavaScript para validação de uma regra de negócio. O sistema possui outras funcionalidades como, tela de login/cadastro, criptografia de dados do usuário, entre outras.
 
## Tecnologias Utilizadas

- Java
- Spring Boot
- JPA / Hibernate
- Maven
- HTML/CSS/JS
- Bootstrap
- MySQL

O projeto é gerenciado pelo Maven, então para usa-lo basta importa-lo para uma IDE.

## Configurações do banco de dados
Você pode criar um banco de dados MySQL com o nome o nome de sua preferência, porém é necessario adequar o projeto de acordo com as suas configurações. Para isso abra o arquivo application.properties, localizado em src/main/resources/application.properties e altere os seguintes comandos ao arquivo:

 
CREATE SCHEMA IF NOT EXISTS celulares DEFAULT CHARACTER SET utf8;
USE celulares;

create table if not exists usuario(
	 id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     email VARCHAR(100) NOT NULL,
     senha VARCHAR(100) NOT NULL,
     user VARCHAR(100) NOT NULL
) Engine= InnoDB;

SELECT * FROM usuario;

create table if not exists celular (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(100) NOT NULL,
    cor VARCHAR(100),
    marca VARCHAR(100) NOT NULL,
    preco  DECIMAL(10, 2) NULL
) Engine= InnoDB;

SELECT * FROM celular;
