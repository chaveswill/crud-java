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

## Requisitos

Para rodar este projeto, você vai precisar:

- 🎲 Banco de dados [MySQL Server](https://github.com/mysql/mysql-server)
- 🔗 Drive de conexão com [MySQL Connector/J](https://github.com/mysql/mysql-connector-j)


Depois de instalados e configurada a conexão com o banco de dados, crie sua base de dados e em seguida as tabelas.

## Tabelas


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

O proximo passo é adicionar os dados nas tabelas 👌:


```sql
INSERT INTO department (Name) VALUES 
  ('Computers'),
  ('Electronics'),
  ('Fashion'),
  ('Books');

INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES 
  ('Bob Brown','bob@gmail.com','1998-04-21 00:00:00',1000,1),
  ('Maria Green','maria@gmail.com','1979-12-31 00:00:00',3500,2),
  ('Alex Grey','alex@gmail.com','1988-01-15 00:00:00',2200,1),
  ('Martha Red','martha@gmail.com','1993-11-30 00:00:00',3000,4),
  ('Donald Blue','donald@gmail.com','2000-01-09 00:00:00',4000,3),
  ('Alex Pink','bob@gmail.com','1997-03-04 00:00:00',3000,2);
  
```
Feito isso, nosso banco de dados está pronto para ser utilizado.

## Os métodos CRUD

Os métodos responsáveis por realizar as transações no banco de dados estão localizados nas classes [SellerDaoJDBC](https://github.com/chaveswill/crud-java/blob/main/src/model/dao/impl/SellerDaoJDBC.java) e [DepartmentDaoJDBC](https://github.com/chaveswill/crud-java/blob/main/src/model/dao/impl/DepartmentDaoJDBC.java).

> ➕ Inserir dados (CREATE)

```java

public insert()
		
```

> 🔎 Ler dados (READ)

```java

public findById()
		
```

```java

public findAll()
	
```

> 🔃 Atualizar dados (UPDATE)

```java

public update()
	
```

> ❌ Apagar dados (DELETE)

```java

public deleteById()
	
```







Neste projeto, são implementados conceitos de Programação Orientada a Objetos, Herença, Encapsulamento, Exceções e outros ✌️ . 





