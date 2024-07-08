# 🚀 API Rest Controle de Contatos

Esta API é um CRUD de Pessoas e Contatos, com integração com a ViaCEP para inserir as informações de endereço de forma automática, a partir do CEP.

## 🛠️ Instalação e Configuração

### Instalação

Clone o repositório e compile o projeto:

```bash
git clone https://github.com/seu-usuario/nome-do-repositorio.git
cd nome-do-repositorio
./mvnw clean install
```

### Configuração

Configure as variáveis de ambiente no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/nome-do-banco
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
spring.jpa.hibernate.ddl-auto=update
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

### Uso

Inicie o servidor:

```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

## 📚 Documentação e Suporte

- **Swagger:** Todos os endpoints das aplicações RESTful são documentados utilizando o Swagger, acessível via `/swagger-ui.html` no navegador após a aplicação estar rodando.

## 📊 Modelagem de Dados

  ### Pessoas:
  - **ID**
  - **Nome**
  - **Endereço**
  - **CEP**
  - **Cidade**
  - **UF**

  ### Contatos:
  - **ID**
  - **Tipo contato**
  - **Contato**
  - **Relacionamento**

### Tipo de Contato: Telefone, Celular, Email
- **ID**
- **Descrição**

## 🌐 Endpoints

### Pessoas:
- **POST /api/pessoas**: Cria uma nova Pessoa
- **GET /api/pessoas/{id}**: Retorna os dados de uma Pessoa por ID
- **GET /api/pessoas/maladireta/{id}**: Retorna os dados de uma Pessoa por ID para mala direta
  - Exemplo de resposta:
    ```json
    {
      "ID": 1,
      "Nome": "Fulano",
      "MalaDireta": "Rua A, 1 – CEP: 11111-000 – Cidade/UF"
    }
    ```
- **GET /api/pessoas**: Lista todas as Pessoas
- **PUT /api/pessoas/{id}**: Atualiza uma Pessoa existente
- **DELETE /api/pessoas/{id}**: Remove uma Pessoa por ID

### Contatos:
- **POST /api/contatos**: Adiciona um novo Contato a uma Pessoa
- **GET /api/contatos/{id}**: Retorna os dados de um Contato por ID
- **GET /api/contatos/pessoa/{idPessoa}**: Lista todos os Contatos de uma Pessoa
- **PUT /api/contatos/{id}**: Atualiza um Contato existente
- **DELETE /api/contatos/{id}**: Remove um Contato por ID

## 📦 Tecnologias Utilizadas
- Java
- Spring Boot
- Maven
- Docker
- MariaDB
- Swagger
- Integração API ViaCEP

## 📩 Contato
Para mais informações, entre em contato através de [lucasfranca_zs@hotmail.com](mailto:lucasfranca_zs@hotmail.com).
```
