
# <> PickPay Simplificado </>

## Resumo:

Desafio OpenSource de Back-End do PicPay. [Ver Desafio](https://github.com/PicPay/picpay-desafio-backend?tab=readme-ov-file)

## Objetivo:

Temos 2 tipos de usuários, os comuns e lojistas, ambos têm carteira com dinheiro e realizam transferências entre eles. Vamos nos atentar somente ao fluxo de transferência entre dois usuários.

**Requisitos:**

- Para ambos tipos de usuário, precisamos do Nome Completo, CPF, e-mail e Senha. CPF/CNPJ e e-mails devem ser únicos no sistema. Sendo assim, seu sistema deve permitir apenas um cadastro com o mesmo CPF ou endereço de e-mail.

- Usuários podem enviar dinheiro (efetuar transferência) para lojistas e entre usuários.

- Lojistas só recebem transferências, não enviam dinheiro para ninguém.

- Validar se o usuário tem saldo antes da transferência.

- Antes de finalizar a transferência, deve-se consultar um serviço autorizador externo, [use este mock para simular](https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc).

- A operação de transferência deve ser uma transação (ou seja, revertida em qualquer caso de inconsistência) e o dinheiro deve voltar para a carteira do usuário que envia.

- No recebimento de pagamento, o usuário ou lojista precisa receber notificação (envio de email, sms) enviada por um serviço de terceiro e eventualmente este serviço pode estar indisponível/instável. [Use este mock para simular o envio](https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6).

- Este serviço deve ser RESTFul.


## API Reference

#### Cadastrar novo usuário

```http
  POST /users
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `firstName` | `string` | Ainda não foi implementado validações |
| `lastName` | `string` | ´´ |
| `document` | `string` | ´´ |
| `email` | `string` | ´´ |
| `password` | `string` | ´´ |
| `balance` | `BigDecimal` | ´´ |
| `userType` | [`UserType`](https://github.com/RickFernandez/Desafio-BackEnd-PicPay/blob/main/src/main/java/com/picpaychallenge/enums/UserType.java) | Deve ser 'COMMON' ou 'MERCHANT' |

#### Listar todos os usuários

```http
  GET /users
```
#### Resposta:

```json
[
		"id": 1,
		"firstName": "userName",
		"lastName": "lastName",
		"document": "document",
		"email": "email@exemple.com",
		"password": "password123",
		"balance": 10.00,
		"userType": "COMMON"
	},
	{
		"id": 3,
		"firstName": "userName",
		"lastName": "lastName",
		"document": "document",
		"email": "email@exemple.com",
		"password": "password123",
		"balance": 10.00,
		"userType": "MERCHANT"
]
```

#### Realizar uma transação

```http
  POST /transactions
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `senderId` | `Long` | Ainda não foi implementado validações |
| `receiverId` | `Long` | ´´ |
| `value` | `BigDecimal` | ´´ |

## Features

- Ajustar o NotificationService;
- Implementar validações nos dados de requisição da API;
- Implementar testes;
- Verificar se há mais tratamentos de erros para implementar;


## 🛠 Skills
Não foi exigido nenhuma skill específica para realizar o desafio, mas eu utilizei as seguintes:

| Tecnologia | Descrição     | Link                |
| :-------- | :------- | :------------------------- |
| Java 17 | Linguagem de programação  | [download](https://www.oracle.com/br/java/technologies/downloads/#java17) |
| Maven | Gerenciador de dependências | Projeto criado com o [Spring Initializer](https://start.spring.io/) então as dependências Spring foram adicionadas na criação do projeto. |
| Spring Boot | Para iniciar o projeto | ´´ |
| Spring Web | Para criar os end-points | ´´ |
| Lombok | Para facilitar e agilizar a codificação | ´´ |
| Spring Data JPA | Para realizar a estrutura com o banco de dados | ´´ |
| H2 Database | Utilizado como Banco de Dados local em memória | ´´ |


### **Made By** [Henrique Fernandez](https://github.com/RickFernandez)
