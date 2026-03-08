# Reciclo - Plataforma de Reciclagem e Doação

## 📋 Sobre o Projeto

O Reciclo é uma plataforma web que facilita a doação e reciclagem de itens, conectando doadores com coletores e pontos de coleta estratégicos. O projeto implementa uma arquitetura com controllers MVC (Thymeleaf) e uma API REST completa.

## ✨ Funcionalidades Principais

- ✅ **Autenticação e Autorização** - Spring Security configurado
- ✅ **API REST Completa** - Endpoints bem documentados
- ✅ **Swagger/OpenAPI** - Documentação interativa automática
- ✅ **DTOs e Mappers** - Padrão de projeto implementado
- ✅ **JPA/Hibernate** - ORM com relacionamentos
- ✅ **Validação** - Jakarta Validation nos endpoints
- ✅ **CORS** - Habilitado para integração frontend

## 🛠 Tecnologias Utilizadas

```
- Java 21
- Spring Boot 3.4.4
- Spring Security
- Spring Data JPA
- Thymeleaf
- PostgreSQL
- Maven
- Swagger/OpenAPI 3.0
- Jakarta Validation
- MapStruct (para mappers)
```

## 📁 Estrutura do Projeto

```
ReCiclo/
├── src/
│   ├── main/
│   │   ├── java/com/example/reciclo/
│   │   │   ├── config/
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   ├── SwaggerConfig.java
│   │   │   │   └── CustomAuthenticationSuccessHandler.java
│   │   │   ├── controller/
│   │   │   │   ├── (Controllers MVC - Thymeleaf)
│   │   │   │   └── rest/
│   │   │   │       ├── ItemRestController.java
│   │   │   │       ├── UsuarioRestController.java
│   │   │   │       ├── PontoDeColetaRestController.java
│   │   │   │       └── AuthRestController.java
│   │   │   ├── dto/
│   │   │   ├── mapper/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   └── RecicloApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-prod.properties
│   │       └── templates/ (HTML com Thymeleaf)
│   └── test/
├── pom.xml
├── API_REST_DOCUMENTATION.md
├── API_USAGE_GUIDE.md
├── QUICK_START.md
├── STARTUP_GUIDE.md
├── ReCiclo_API_Postman.json
└── README.md
```

## 🚀 Como Executar

### Pré-requisitos
- Java 21+
- PostgreSQL 12+
- Maven 3.6+

### Passos

1. **Clonar o repositório**
   ```bash
   git clone https://github.com/seu-usuario/ReCiclo.git
   cd ReCiclo
   ```

2. **Configurar o banco de dados**
   
   Edite `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/reciclo
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```

3. **Executar o projeto**
   ```powershell
   .\scripts\start-safe.ps1
   ```

   Alternativa direta (sem limpeza automatica da porta):
   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

4. **Acessar a aplicação**
   - **Web MVC**: http://localhost:8080
   - **Swagger UI**: http://localhost:8080/swagger-ui.html
   - **API REST**: http://localhost:8080/api/v1

## 📚 Documentação da API

### Swagger UI (Recomendado)
Acesse a documentação interativa:
```
http://localhost:8080/swagger-ui.html
```

### Documentação em Markdown
- `API_REST_DOCUMENTATION.md` - Documentação detalhada dos endpoints
- `API_USAGE_GUIDE.md` - Guia prático com exemplos de uso
- `QUICK_START.md` - Passo a passo rápido para testar a API
- `STARTUP_GUIDE.md` - Guia completo de inicialização

### Coleção Postman
Importe `ReCiclo_API_Postman.json` no Postman para testar todos os endpoints.

## 🔑 Endpoints Principais

### Autenticação
- `POST /api/v1/auth/login` - Fazer login

### Usuários
- `GET /api/v1/usuarios` - Listar todos
- `GET /api/v1/usuarios/{id}` - Obter por ID
- `POST /api/v1/usuarios` - Criar novo
- `PUT /api/v1/usuarios/{id}` - Atualizar
- `DELETE /api/v1/usuarios/{id}` - Deletar

### Itens
- `GET /api/v1/items` - Listar todos
- `GET /api/v1/items/{id}` - Obter por ID
- `GET /api/v1/items/doador/{doadorId}` - Listar por doador
- `POST /api/v1/items` - Criar novo
- `PUT /api/v1/items/{id}` - Atualizar
- `DELETE /api/v1/items/{id}` - Deletar

### Pontos de Coleta
- `GET /api/v1/pontos-coleta` - Listar todos
- `GET /api/v1/pontos-coleta/{id}` - Obter por ID
- `GET /api/v1/pontos-coleta/tipo/{tipo}` - Listar por tipo
- `POST /api/v1/pontos-coleta` - Criar novo
- `PUT /api/v1/pontos-coleta/{id}` - Atualizar
- `DELETE /api/v1/pontos-coleta/{id}` - Deletar

## 🔐 Segurança

- Spring Security configurado
- Autenticação com validação de e-mail e senha
- CORS habilitado (configurável)
- Validação de dados com Jakarta Validation

## 📦 Build e Deploy

### Build para Produção
```bash
./mvnw clean package -DskipTests
```

### Deploy no Docker
```bash
docker build -t reciclo:latest .
docker run -p 8080:8080 reciclo:latest
```

## 🤝 Padrões de Projeto Implementados

- ✅ **DTO (Data Transfer Object)** - Separação entre dados transferidos e entidades
- ✅ **Mapper** - MapStruct para mapeamento de entidades para DTOs
- ✅ **Repository** - Spring Data JPA com custom queries
- ✅ **Service** - Lógica de negócio separada
- ✅ **REST** - Endpoints RESTful com ResponseEntity

## 📋 Modelos de Dados

### Relacionamentos
- **Usuário** (1) --- (N) **Item** (Doador)
- **Usuário** (1) --- (N) **Perfil**
- **Item** (N) --- (1) **PontoDeColeta** (opcional)

## 🧪 Testes

```bash
./mvnw test
```

## 📝 Licença

Apache License 2.0 - Veja LICENSE.txt para mais detalhes

## 👥 Autores

- Desenvolvedoras: Alycia e Evely
- Orientação: Acadêmico

## 📞 Suporte

Para dúvidas ou problemas, entre em contato através de:
- Email: suporte@reciclo.com
- Issues: GitHub Issues

---

**Última atualização:** 2025
**Versão da API:** 1.0.0

