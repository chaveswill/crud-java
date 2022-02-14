<p align="center">
  <img src="/CRUD.png" width="450">
</p>

---

## CRUD em Java e MySQL



<p>
<img src = "https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white">
<img src = "https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white">
<img src = "https://img.shields.io/github/stars/chaveswill/crud-java?style=for-the-badge">
<img src = "https://img.shields.io/github/issues/chaveswill/crud-java?style=for-the-badge">
</p>


CRUD é o acrônimo da expressão em Inglës, Create (Criação), Read (Consulta), Update (Atualização) e Delete (Destruição). Este acrônimo é comumente utilizado para definir as quatro operações básicas usadas em Banco de Dados Relacionais.

Neste projeto, foi implementado um banco de dados utilizando o servidor MySQL :dolphin:, com duas tabelas: uma de vendedores de uma determinada loja fictícia, outra com os departamentos desta loja. Os métodos para realizar as operações foram implementadas na linguagem Java. 

---

## Criando o banco de dados

Antes de tudo, é preciso instalar o MySQL Server e o MySQL Workbench. Em seguida, configure suas credenciais de acesso ao banco de dados.
Depois de instalados, o primeiro passo é criar a base de dados que ira armazenar as tabelas.
```sql
CREATE DATABASE nome_do_banco

```
O ```nome_do_banco``` pode ser o nome de sua preferência.

Depois de criar o banco de dados, selecione-o, usando o comando ```USE nome_do_banco```. Em seguida crie as tabelas ```department``` e ```seller``` .
```sql
CREATE TABLE department (
  Id int(11) NOT NULL AUTO_INCREMENT,
  Name varchar(60) DEFAULT NULL,
  PRIMARY KEY (Id)
);



CREATE TABLE seller (
  Id int(11) NOT NULL AUTO_INCREMENT,
  Name varchar(60) NOT NULL,
  Email varchar(100) NOT NULL,
  BirthDate datetime NOT NULL,
  BaseSalary double NOT NULL,
  DepartmentId int(11) NOT NULL,
  PRIMARY KEY (Id),
  FOREIGN KEY (DepartmentId) REFERENCES department (id)
);

```

O proximo passo é adicionar os dados nas tabelas:
```sql
INSERT INTO department (Name) VALUES 
  ('Computers'),
  ('Electronics');

INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES 
  ('Bob Brown','bob@gmail.com','1998-04-21 00:00:00',1000,1),
  ('Maria Green','maria@gmail.com','1979-12-31 00:00:00',3500,2);
  
```
Feito isso, nosso banco de dados está pronto para ser utilizado.

## Implementando os métodos CRUD

Pelo fato de Java ser uma linguagem orientada a objetos, neste projeto são apresentados conceitos de orientação a objetos, herança, encapsulamento, exceções e outros.

Para nossos métodos CRUD "acessarem" nosso banco de dados, é necessário instalar o driver MySQL Java Connector no seu IDLE/editor. Uma vez que seu projeto esteja criado e a conexão com o banco de dados esteja estabelecida, é hora de implementar os códigos.

> Inserir dados (CREATE)



Neste projeto, cada vendedor pertence a um departamento :point_up:, e cada departamento pode ter vários vendedores :ok_hand:.




