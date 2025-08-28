# Beneficiários API

## Visão Geral
Esta API tem como objetivo gerenciar beneficiários e seus respectivos planos e também persistir essas informações em um banco de dados. 

### Funcionalidades principais
- CRUD dos planos e beneficiários.  
- Validação de CPF (duplicado ou inválido).
- Validação de Plano (duplicado ou inválido).
- Validação
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

## 1 - Pré-requisitos
- Java 17+
- JDK 17+
- PostgreSQL
### Ocasionais:
- Caso esteja utilizando o vscode, baixar a extensão "Extension Pack For Java".
- Maven (caso queira subir e testar a aplicação de forma mais eficiente).

## 2 - Configuração do banco
Crie um banco no PostgreSQL e configure no `application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/"nome do banco criado"
spring.datasource.username=nome de usuario do postgres (normalmente vem "postgres" por padrão).
spring.datasource.password=sua_senha (senha utilizada para entrar no postgres)
spring.jpa.hibernate.ddl-auto=update
```
## 3 - Comandos para rodar a aplicação
### Com o maven (Recomendando).
```
mvn spring-boot:run
```
### Para rodar sem o maven:
Apenas utilize o inicializador da sua IDE dentro da classe "DesafioApplication.java".

## Testes

### Com o Maven (Recomendando).
```
mvn test
```
### Sem o maven:
Apenas utilize o inicializador da sua IDE dentro da classe "BeneficiarioServiceTest.java".

## Trade Offs
Criação de um ExceptionHandler para centralizar o controle de exceptions e evitar o uso de try catchs na lógica do código. Dessa forma, mantendo o código mais limpo e facilitando a manutenção.

## Para rodar o swagger:
Com a aplicação rodando, colar a url no navegador ```http://localhost:8080/swagger-ui.html```

## Exemplos de requisições:
### localhost:8080/planos
GetAll (Retorna todos os planos cadastrados no banco de dados e seus respectivos ids).
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
Post (Cria um plano no banco de dados). 
```
  {
	"nome": "Plano Bronze",
	"codigo_registro_ans": "ANS-100002"
  }
```
### localhost:8080/beneficiarios
Post (Cria um beneficiário no banco de dados).
```
  {
  "nome_completo": "Rian Vitorino",
  "cpf": "12345622209",
  "data_nascimento": "2004-12-20",
  "plano_id": "UUID do plano criado previamente"
  }
```

GetAll (Retorna todos os beneficiários armazenados no banco de dados).
```
[
	{
		"id": "fecb686e-f0b2-4aab-be2c-c657fe5cf23a",
		"nome_completo": "Geladeira Eletrolux",
		"cpf": "32345622209",
		"data_nascimento": "2004-12-19",
		"status": "ATIVO",
		"plano": {
			"id": "11a19651-d406-435a-aeab-da6163348390",
			"nome": "Plano Bronze",
			"codigo_registro_ans": "AWS_1231234"
		},
		"data_cadastro": "2025-08-27T15:27:20.843109"
	},
	{
		"id": "d170f7de-68e4-4d14-8e93-5e74120f1c8a",
		"nome_completo": "Ramon Nogueira",
		"cpf": "12345622209",
		"data_nascimento": "2004-12-19",
		"status": "ATIVO",
		"plano": {
			"id": "2b4ec0f0-5a17-4cc9-a50b-681afefd2f5b",
			"nome": "Plano Bronze",
			"codigo_registro_ans": "AWS_1231434"
		},
		"data_cadastro": "2025-08-27T20:58:19.3634808"
	}
]
```
