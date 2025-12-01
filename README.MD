Desafio Hyperativa - Instruções
==================

## Configurações
### Requisitos
- Mysql 8.0
- Java OpenJDK 17 ou superior 
- Docker
- Docker Compose

### Instalação da imagem Docker
Execute o comando abaixo para compilar a aplicação e criar o arquivo JAR:
```
mvn clean package
```
Execute o comando abaixo para baixar a imagem Docker com o banco de dados Mysql 8.0:

```
docker-compose up 
```
Isso criará o container rest-application,com a aplicação REST e o banco de dados MySQL 8.0.

## Collection Postman
Importe a collection Postman disponível no arquivo `Hyperativa.postman_collection.json` encontrado na raiz do projeto, para testar os endpoints da aplicação REST.

### Criação de usuários
Crie os usuários a partir da requisição Create User Request, com caminho /auth/signup, dentro da pasta Authentication do postman.

### Login

Efetue o login a partir da requisição Login Request, com caminho /auth/login, dentro da pasta Authentication do postman.
Será gerado um token JWT, que deverá ser utilizado para autenticar as demais requisições.
As requisições já possuem variáveis pré configuradas no postman para utilização do token JWT. 

### Endpoints   
A aplicação REST possui um swagger disponível para consulta dos endpoints e testes.
Acesse o swagger através do link: `https://localhost:8443/rest-application/swagger-ui/index.html#/