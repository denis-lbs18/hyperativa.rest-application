Hyperativa Challenge - Instructions
==================

## Setup
### Requirements
- MySQL 8.0
- Java OpenJDK 17 or higher
- Docker
- Docker Compose

### Docker Image Installation
Run the command below to compile the application and generate the JAR file:
```
mvn clean package
```

Run the command below to download the Docker image with the MySQL 8.0 database:

```
docker-compose up
```

This will create the rest-application container, with the REST application and the MySQL 8.0 database.

## Postman Collection
Import the Postman collection available in the file Hyperativa.postman_collection.json located at the root of the project, to test the REST application endpoints.

### User Creation
Create users using the Create User Request, with the path /auth/signup, inside the Authentication folder in Postman.

### Login
Perform login using the Login Request, with the path /auth/login, inside the Authentication folder in Postman. A JWT token will be generated, which must be used to authenticate subsequent requests. The requests already have pre-configured variables in Postman for using the JWT token.

### Endpoints
The REST application provides a Swagger interface for consulting and testing endpoints.
Access Swagger at: `https://localhost:8443/rest-application/swagger-ui/index.html#/`