# Place API

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.oracle.com/java/) 
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot) 
[![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/) 
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/) 
[![R2DBC](https://img.shields.io/badge/R2DBC-336791?style=for-the-badge&logo=reactivex&logoColor=white)](https://r2dbc.io/) 
[![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=white)](https://swagger.io/)  

[![License](https://img.shields.io/badge/license-Apache%202.0-blue)](http://www.apache.org/licenses/LICENSE-2.0.html)


Este repositório armazena uma API reativa para gerenciar lugares (Places), construída utilizando Java, Spring Boot, Maven, MySQL e R2DBC. A API adota o padrão DTO para controlar as requests e responses, além de utilizar o Swagger para documentar os endpoints. O projeto foi desenvolvido com foco em reatividade, utilizando programação assíncrona para maior eficiência e escalabilidade.

---

## Descrição

A aplicação permite o gerenciamento de lugares por meio de um sistema CRUD (Create, Read, Update, Delete). As principais funcionalidades incluem:

- Criar novos lugares com nome e estado
- Visualizar a lista de todos os lugares cadastrados
- Atualizar informações de lugares existentes
- Excluir lugares
- Buscar detalhes de um lugar pelo ID

Cada lugar possui os seguintes campos mínimos:
- **Name**: Nome do lugar
- **Slug**: Identificador único gerado a partir do nome
- **State**: Estado em que o lugar está localizado

---

## Requisitos

Para construir e executar a aplicação, você precisará de:

- [JDK 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven 3](https://maven.apache.org/)
- [MySQL 8.0](https://dev.mysql.com/downloads/mysql/)

---

## Configuração do Ambiente

1. Clone este repositório:

   ```bash
   git clone https://github.com/EnzoLFranco/PlaceAPI-JavaSpring.git
   cd PlaceAPI-JavaSpring
   ```

2. Configure o banco de dados MySQL:
   - Crie um banco de dados chamado `places`
   - Atualize as credenciais do banco no arquivo `application.properties` localizado em `src/main/resources/`.

3. Execute a aplicação com o Maven:

   ```bash
   mvn spring-boot:run
   ```

4. Acesse a aplicação no navegador em: `http://localhost:8080/places`

---

## Documentação da API

A aplicação utiliza o Swagger para documentar as APIs. Após iniciar o servidor, a documentação estará disponível em:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Funcionalidades Principais

1. **Criar Lugar**: Permite criar novos lugares com nome e estado.
2. **Visualizar Lugares**: Lista todos os lugares cadastrados no banco de dados.
3. **Editar Lugar**: Atualiza informações de um lugar existente.
4. **Excluir Lugar**: Remove um lugar do sistema.
5. **Buscar Lugar por ID**: Recupera os detalhes de um lugar específico pelo seu identificador único.

---

### Boas Práticas Aplicadas

### 1. Uso de DTOs (Data Transfer Objects)
- **Descrição**: As classes `PlaceRequest` e `PlaceResponse` foram utilizadas para encapsular os dados enviados e retornados pelos endpoints. Isso permite uma separação clara entre a camada de domínio e a API pública, facilitando a manutenção e reduzindo o acoplamento.
- **Benefícios**:
  - Evita expor diretamente entidades do domínio.
  - Permite adicionar ou remover campos específicos para a API sem impactar a lógica de negócio.

### 2. API Reativa com Spring WebFlux e R2DBC
- **Descrição**: A aplicação foi projetada como uma API reativa utilizando o Spring WebFlux e R2DBC para lidar com banco de dados de forma não bloqueante.
- **Benefícios**:
  - Melhor desempenho para sistemas que exigem alta escalabilidade.
  - Maior eficiência no uso de recursos, especialmente para aplicações com grande volume de I/O.

---

## Tecnologias Utilizadas

- **Java 17**: Linguagem principal para o desenvolvimento da aplicação.
- **Spring Boot**: Framework para simplificar o desenvolvimento e configuração da aplicação.
- **R2DBC**: Para integração reativa com o banco de dados MySQL.
- **MySQL**: Banco de dados relacional utilizado para armazenamento das informações.
- **Swagger**: Para documentação interativa e teste dos endpoints.
- **Maven**: Ferramenta para gerenciamento de dependências e automação de build.

---

## Licença

Este projeto está licenciado sob a [Licença Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0.html).
