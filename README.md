```markdown
# Sistema de Gestão de Clientes

Sistema de cadastro e gerenciamento de clientes desenvolvido em Java, com menu interativo via terminal e persistência de dados em banco MySQL.

## Funcionalidades

- Cadastrar clientes (nome, email, telefone)
- Listar todos os clientes cadastrados
- Buscar clientes por nome
- Excluir clientes por ID
- Dados persistidos em banco de dados MySQL

## Tecnologias

- Java 25
- MySQL 8.0
- JDBC (MySQL Connector/J)
- Programação Orientada a Objetos (POO)

## Pré-requisitos

- Java 17 ou superior
- MySQL 8.0 instalado e rodando
- MySQL Connector/J (já incluído no repositório)

## Configuração do banco de dados

Execute no MySQL Workbench:

```sql
CREATE DATABASE sistema_clientes;
USE sistema_clientes;
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(20)
);
```

## Como executar

1. Clone o repositório:
```
git clone https://github.com/lucasbrl95/sistema-clientes.git
```

2. Abra `src/ConexaoDB.java` e informe sua senha do MySQL:
```java
private static final String PASSWORD = "sua_senha_aqui";
```

3. Acesse a pasta `src` e compile:
```
javac -cp ".;../mysql-connector-j-26.7.0.jar" *.java
```

4. Execute:
```
java -cp ".;../mysql-connector-j-26.7.0.jar" Main
```

## Autor

Lucas — Desenvolvedor Back-end | Goiânia, BR | [GitHub](https://github.com/lucasbrl95)
```