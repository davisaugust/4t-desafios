# Beneficiários API

## Visão Geral
Esta API tem como objetivo gerenciar beneficiários e seus respectivos planos.  

### Funcionalidades principais
- CRUD dos planos e beneficiários.  
- Validação de CPF (duplicado ou inválido).   
- Atualização de status do beneficiário.  
- Tratamento centralizado de erros, retornando respostas em JSON.  

---

## Stack Utilizada
- **Java 17+**  
- **Spring Boot 3** (Data JPA, Validation)  
- **PostgreSQL** (banco de dados)
- **Maven** (gerenciamento de dependências e build)  
- **JUnit 5 + Mockito** (testes unitários)  

---

## Como rodar localmente

### 1 - Pré-requisitos
- Java 17+  
- Maven 
- PostgreSQL  

### 2 - Configuração do banco
Crie um banco no PostgreSQL e configure no `application.properties`:

### 3 - Comandos para rodar
Caso esteja tentando rodar localmente, utilizar o comando 

Propriedades para se aplicar no .properties: 
```
spring.datasource.url=jdbc:postgresql://localhost:5432/beneficiarios
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

## Trade Offs
Criação de um RestHandler para centralizar o controle de exceptions e evitar o uso de try catchs na lógica do código. Dessa forma, mantendo o código mais limpo e facilitando a manutenção das exceptions.

## Exemplos de requisições:
### localhost:8080/Plano
GetAll - Plano (Retorna todos os planos cadastrados no banco de dados e seus respectivos ids).
```
  {
  "id": "11a19651-d406-435a-aeab-da6163348390",
	"nome": "Plano Bronze",
	"codigo_registro_ans": "ANS-100001"
  },
  {
  "id": "6c4e03d0-8b8b-4f1e-a908-27df1be196fc",
	"nome": "Plano Prata",
	"codigo_registro_ans": "ANS-100002"
  },
  {
  "id": "18e1280a-f094-4947-b88e-486e47e7ad09",
	"nome": "Plano Prata",
	"codigo_registro_ans": "ANS-100003"
  }
```
Post - Plano (Cria um plano no banco de dados). 
```
  {
	"nome": "Plano Bronze",
	"codigo_registro_ans": "ANS-100002"
  }
```
### localhost:8080/Beneficiario
Post - Beneficiario (Cria um beneficiário no banco de dados).
```
  {
  "nome_completo": "Rian Vitorino",
  "cpf": "12345622209",
  "data_nascimento": "2004-12-20",
  "plano_id": "UUID do plano criado previamente"
  }
```
