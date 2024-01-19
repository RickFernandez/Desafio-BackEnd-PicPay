
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


## Referências da API

#### Cadastrar novo usuário

```
  POST: /users
```

| Parameter | Type     | Validações                |
| :-------- | :------- | :------------------------- |
| `firstName` | `string` | **Obrigatório, NotBlank(Não pode ser null nem vazio)** |
| `lastName` | `string` | **Obrigatório, NotBlank(Não pode ser null nem vazio)** |
| `document` | `string` | **Obrigatório, NotBlank(Não pode ser null nem vazio), Deve seguir o padrão 123.456.789-12** |
| `email` | `string` | **Obrigatório, NotBlank(Não pode ser null nem vazio), Email** |
| `password` | `string` | **Obrigatório, NotBlank(Não pode ser null nem vazio), Min 8 caracteres** |
| `balance` | `BigDecimal` | **Obrigatório, Não pode ser Null** |
| `userType` | [`UserType`](https://github.com/RickFernandez/Desafio-BackEnd-PicPay/blob/main/src/main/java/com/picpaychallenge/enums/UserType.java) | **Obrigatório, Não pode ser Null, Deve ser 'COMMON' ou 'MERCHANT'** |

#### Listar todos os usuários

```
  GET: /users
```
#### Resposta:

```
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

```
  POST: /transactions
```
| Parameter | Type     | Validações                |
| :-------- | :------- | :------------------------- |
| `senderId` | `Long` | **Obrigatório, Não pode ser Null** |
| `receiverId` | `Long` | **Obrigatório, Não pode ser Null** |
| `value` | `BigDecimal` | **Obrigatório, Não pode ser Null** |

## 🛠 Skills
Não foi exigido nenhuma skill específica para realizar o desafio, mas eu utilizei as seguintes:

| Tecnologia | Descrição     | Link                |
| :-------- | :------- | :------------------------- |
| Java 17 | Linguagem de programação  | [download](https://www.oracle.com/br/java/technologies/downloads/#java17) |
| Maven | Gerenciador de dependências | Projeto criado com o [Spring Initializer](https://start.spring.io/) então as dependências Spring foram adicionadas na criação do projeto. |
| Spring Boot | Para iniciar o projeto | [Documentação](https://spring.io/projects/spring-boot/) |
| Lombok | Para facilitar e agilizar a codificação | [Documentação](https://projectlombok.org/features/) |
| Spring Data JPA | Para realizar a estrutura com o banco de dados | [Documentação](https://spring.io/projects/spring-data-jpa/) |
| H2 Database | Utilizado como Banco de Dados local em memória | [Utilizando banco de dados H2 com Spring de forma rápida e simples](https://wpsilva.medium.com/utilizando-banco-de-dados-h2-com-spring-de-forma-r%C3%A1pida-e-simples-6d896e15a4af) |
| JUnit5 | Utilizado para realizar testes unitártios | [Documentação](https://junit.org/junit5/) |
| Mockito | Utilizado para mockar dados para os testes unitários | [Documentação](https://site.mockito.org/) |

## Instalação

1. **Clone o repositório**
```
  git clone https://github.com/RickFernandez/Desafio-BackEnd-PicPay.git
```
2. **Abra o projeto na sua IDE de preferência (IntelliJ, Eclipse, VS Code)**

3. **Vá ate o arquivo pom.xml e confira se as dependências do projeto foram instaladas com êxito (caso não, instale-as)**

4. **Inicie a aplicação**

5. **Pronto! Agora é só mandar as requisições para as URLs descritas na parte de 'Referências da API'**
```
  http://localhost:8080/{referência}
```

### **Made By** [Henrique Fernandez](https://github.com/RickFernandez)
