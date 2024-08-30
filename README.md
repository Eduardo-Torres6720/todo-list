# Lista de tarefas

Esse projeto é uma api de lista de tarefas com autenticação de usuário.

Essa api utiliza **Java, Spring Boot, Flyway, PostgresSql, e Spring security e JWT para autenticação.**

## Requisitos

1. Java com Maven

2. postgresSQL

## Instalação

1. Clone esse repositório:

```bash
git clone https://github.com/Eduardo-Torres6720/todo-list.git
```

2. Instale as dependecias do pom.xml com o Maven.

## Rodando

1. Rode a aplicação utilizando o Maven

2. A api vai estar disponivel na rota http://localhost:8080

## Funcionalidades

### Autenticação

A api possui um sistema de login e cadastro, sendo seus endpoints:

```markdown
POST /auth/login - Login para entrar no app

POST /auth/register - Cadastro para entrar no app
```

As contas não possuem cargo, mas não possuem acesso a aplicação caso não sejam autenticados. Utiliza **Spring Security e JWT** para a autenticação.

### Lista

A api possui um sistema de manipulação de tarefas por usuário, sendo seus endpoints:

```markdown
GET tasks/user/{userId} - Chama todas as tarefas ativadas do usuário

GET tasks/deletedTask/user/{userId} - Chama todas as tarefas desativadas do usuário

POST tasks/user/{userId}/addTask - Adiciona uma nova tarefa ao usuário

DELETE tasks/delete/{taskId} - desativa e deleta uma tarefa específica

PUT tasks/updateTask/{taskId} - atualiza uma tarefa especifica

PUT tasks/activeTask - Recupera 1 ou varias tarefas desativadas

PUT tasks/completeTask - Altera a tarefa como completada ou não
```

## Banco de dados

Essa api utiliza o PostgresSQL como banco de dados e o Flyway como gerenciador de migrações.
