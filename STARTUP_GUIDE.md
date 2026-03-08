# Instruções de Startup - Reciclo API

## 🚀 Iniciando a Aplicação

### 1. Pré-requisitos

Certifique-se de que você possui:
- ✅ Java 21 ou superior instalado
- ✅ PostgreSQL 12+ rodando
- ✅ Maven configurado (ou use o mvnw incluído)

### 2. Configuração do Banco de Dados

#### 2.1 Criar Banco de Dados PostgreSQL

```sql
-- Conectar ao PostgreSQL
psql -U postgres

-- Criar banco de dados
CREATE DATABASE reciclo;
CREATE USER reciclo_user WITH PASSWORD 'reciclo_password';
ALTER ROLE reciclo_user SET client_encoding TO 'utf8';
ALTER ROLE reciclo_user SET default_transaction_isolation TO 'read committed';
ALTER ROLE reciclo_user SET default_transaction_deferrable TO on;
ALTER ROLE reciclo_user SET default_transaction_read_committed TO on;
ALTER ROLE reciclo_user SET timezone TO 'UTC';
GRANT ALL PRIVILEGES ON DATABASE reciclo TO reciclo_user;

-- Sair
\q
```

#### 2.2 Configurar application.properties

Edite `src/main/resources/application.properties`:

```properties
# Server
server.port=8080
server.servlet.context-path=/

# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/reciclo
spring.datasource.username=reciclo_user
spring.datasource.password=reciclo_password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQL13Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true

# Logging
logging.level.root=INFO
logging.level.com.example.reciclo=DEBUG

# Thymeleaf
spring.thymeleaf.mode=HTML
spring.thymeleaf.cache=false
spring.thymeleaf.suffix=.html
spring.thymeleaf.prefix=classpath:/templates/

# CORS
spring.web.cors.allowed-origins=*
spring.web.cors.allowed-methods=GET,POST,PUT,DELETE,OPTIONS
spring.web.cors.allow-credentials=true
spring.web.cors.max-age=3600

# Swagger/OpenAPI
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.enabled=true
```

### 3. Compilar o Projeto

```bash
# Usar Maven Wrapper (Windows)
.\mvnw.cmd clean install -DskipTests

# Ou usar Maven (se instalado)
mvn clean install -DskipTests
```

### 4. Executar a Aplicação

#### Opção 1: Usando script seguro (Windows, recomendado)
```powershell
.\scripts\start-safe.ps1
```

Se sua politica de execucao bloquear scripts no terminal atual:
```powershell
Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass
```

#### Opção 2: Usando Maven
```powershell
.\mvnw.cmd spring-boot:run
```

#### Opção 3: Executar JAR
```powershell
java -jar target/reciclo-0.0.1-SNAPSHOT.jar
```

#### Opção 4: IDE (IntelliJ IDEA)
- Abrir `RecicloApplication.java`
- Clicar em Run (verde) ao lado da classe

### 5. Verificar se Iniciou Corretamente

Acesse no navegador:

```
http://localhost:8080
```

Você deve ver:
- ✅ Home page da aplicação
- ✅ Sem erros no console

### 6. Acessar Swagger UI

```
http://localhost:8080/swagger-ui.html
```

Você deve ver:
- ✅ Lista de todos os endpoints
- ✅ Documentação interativa

---

## 🔍 Troubleshooting

### Erro: "Connection refused" no banco de dados

**Solução:**
1. Verifique se PostgreSQL está rodando
2. Confirme as credenciais em `application.properties`
3. Verifique a porta (padrão: 5432)

```bash
# No Windows, verificar status do PostgreSQL
pg_ctl -D "C:\Program Files\PostgreSQL\15\data" status
```

### Erro: "Maven command not found"

**Solução:**
Use o Maven Wrapper incluído:
```bash
# Windows
.\mvnw.cmd clean install

# Linux/Mac
./mvnw clean install
```

### Erro: "Port 8080 already in use"

**Solução:**
1. Descobrir o PID que ocupa a porta:
```powershell
netstat -ano | findstr :8080
```
2. Encerrar o processo:
```powershell
taskkill /PID <PID> /T /F
```
3. Iniciar com limpeza automatica da porta:
```powershell
.\scripts\start-safe.ps1
```

Se preferir manter o processo atual e subir em outra porta:
```properties
server.port=8081
```

### Erro: "No qualifying bean of type 'UsuarioRepository'"

**Solução:**
1. Execute `mvn clean install` para recompilar
2. Verifique se as interfaces estão na pasta `repository/`
3. Reinicie a IDE

---

## 📊 Dados de Teste

Após iniciar a aplicação, você pode usar a seção "Autenticação" no Swagger para fazer login com:

```
Email: usuario@teste.com
Senha: senha123
```

> **Nota:** Esses dados devem ser criados através do endpoint `POST /api/v1/usuarios`

---

## 🛠 Variáveis de Ambiente (Produção)

Para produção, configure as variáveis de ambiente:

```bash
# Linux/Mac
export SPRING_DATASOURCE_URL=jdbc:postgresql://prod-server:5432/reciclo
export SPRING_DATASOURCE_USERNAME=prod_user
export SPRING_DATASOURCE_PASSWORD=prod_password
export SPRING_PROFILES_ACTIVE=prod

# Windows (PowerShell)
$env:SPRING_DATASOURCE_URL="jdbc:postgresql://prod-server:5432/reciclo"
$env:SPRING_DATASOURCE_USERNAME="prod_user"
$env:SPRING_DATASOURCE_PASSWORD="prod_password"
$env:SPRING_PROFILES_ACTIVE="prod"
```

Depois execute:
```bash
java -jar target/reciclo-0.0.1-SNAPSHOT.jar
```

---

## 🐳 Docker

### Build da imagem
```bash
docker build -t reciclo:1.0 .
```

### Executar container
```bash
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/reciclo \
  -e SPRING_DATASOURCE_USERNAME=reciclo_user \
  -e SPRING_DATASOURCE_PASSWORD=reciclo_password \
  reciclo:1.0
```

---

## 📝 Logs Importantes

### Aplicação iniciada com sucesso
```
....
Started RecicloApplication in 3.141 seconds (process running for 3.421)
```

### Swagger disponível
```
Swagger UI available at http://localhost:8080/swagger-ui.html
```

---

## ✅ Checklist de Startup

- [ ] PostgreSQL está rodando
- [ ] Banco de dados "reciclo" foi criado
- [ ] Arquivo `application.properties` está configurado
- [ ] Maven/Java estão instalados
- [ ] Projeto compila sem erros
- [ ] Aplicação inicia sem erros
- [ ] Acesso http://localhost:8080 funciona
- [ ] Swagger está disponível em http://localhost:8080/swagger-ui.html

---

## 📞 Suporte

Em caso de dúvidas:
1. Consulte `README.md`
2. Consulte `QUICK_START.md`
3. Consulte `API_REST_DOCUMENTATION.md`
4. Consulte `API_USAGE_GUIDE.md`
5. Verifique os logs na console

**Versão:** 1.0.0  
**Data:** 2025
