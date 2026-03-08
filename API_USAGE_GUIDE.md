# Guia de Uso da API Reciclo

## 🔍 Visão Geral

Este guia apresenta exemplos práticos de como utilizar os endpoints da API Reciclo. Todos os exemplos podem ser testados através do Swagger UI.

## 📌 URL Base

```
http://localhost:8080/api/v1
```

---

## 🔑 1. Autenticação

### Login de Usuário

**Endpoint:** `POST /auth/login`

**Requisição:**
```json
{
  "email": "usuario@email.com",
  "senha": "senha123"
}
```

**Resposta Sucesso (200):**
```json
{
  "id": 1,
  "name": "João Silva",
  "email": "usuario@email.com",
  "tipoPerfil": "USUARIO"
}
```

**Resposta Erro (401):**
```json
"Usuário ou senha inválidos"
```

---

## 👥 2. Gerenciamento de Usuários

### 2.1 Listar Todos os Usuários

**Endpoint:** `GET /usuarios`

**Resposta (200):**
```json
[
  {
    "id": 1,
    "name": "João Silva",
    "email": "joao@email.com",
    "celular": "(11) 99999-9999",
    "genero": "M",
    "dataNascimento": "1990-01-15",
    "cep": "12345-000",
    "municipio": "São Paulo",
    "estado": "SP"
  },
  {
    "id": 2,
    "name": "Maria Santos",
    "email": "maria@email.com",
    "celular": "(11) 98888-8888",
    "genero": "F",
    "dataNascimento": "1992-03-20",
    "cep": "54321-000",
    "municipio": "Rio de Janeiro",
    "estado": "RJ"
  }
]
```

### 2.2 Obter Usuário por ID

**Endpoint:** `GET /usuarios/{id}`

**Exemplo:** `GET /usuarios/1`

**Resposta (200):**
```json
{
  "id": 1,
  "name": "João Silva",
  "email": "joao@email.com",
  "celular": "(11) 99999-9999",
  "genero": "M",
  "dataNascimento": "1990-01-15",
  "cep": "12345-000",
  "logradouro": "Rua das Flores",
  "numero": "123",
  "bairro": "Centro",
  "municipio": "São Paulo",
  "estado": "SP",
  "complemento": "Apt 401"
}
```

### 2.3 Buscar Usuário por Email

**Endpoint:** `GET /usuarios/email/{email}`

**Exemplo:** `GET /usuarios/email/joao@email.com`

**Resposta (200):**
```json
{
  "id": 1,
  "name": "João Silva",
  "email": "joao@email.com",
  "celular": "(11) 99999-9999"
}
```

### 2.4 Registrar Novo Usuário

**Endpoint:** `POST /usuarios`

**Requisição:**
```json
{
  "name": "Pedro Oliveira",
  "email": "pedro@email.com",
  "cpf": "123.456.789-10",
  "celular": "(21) 97777-7777",
  "genero": "M",
  "dataNascimento": "1988-05-10",
  "cep": "20000-000",
  "logradouro": "Avenida Rio Branco",
  "numero": "500",
  "bairro": "Centro",
  "municipio": "Rio de Janeiro",
  "estado": "RJ",
  "complemento": "Sala 200",
  "senha": "senha123",
  "confirmarSenha": "senha123"
}
```

**Resposta Sucesso (201):**
```json
{
  "id": 3,
  "name": "Pedro Oliveira",
  "email": "pedro@email.com",
  "celular": "(21) 97777-7777",
  "dataNascimento": "1988-05-10",
  "municipio": "Rio de Janeiro",
  "estado": "RJ"
}
```

**Resposta Erro (409):**
```json
"Já existe um usuário com este e-mail ou CPF"
```

### 2.5 Atualizar Usuário

**Endpoint:** `PUT /usuarios/{id}`

**Exemplo:** `PUT /usuarios/1`

**Requisição:**
```json
{
  "name": "João Silva Atualizado",
  "email": "joao@email.com",
  "celular": "(11) 99999-0000",
  "dataNascimento": "1990-01-15",
  "genero": "M",
  "cep": "12345-000",
  "logradouro": "Rua Nova",
  "numero": "456",
  "bairro": "Bairro Novo",
  "municipio": "São Paulo",
  "estado": "SP",
  "complemento": "Apt 801",
  "senha": "senha123",
  "confirmarSenha": "senha123"
}
```

**Resposta (200):**
```json
{
  "id": 1,
  "name": "João Silva Atualizado",
  "email": "joao@email.com",
  "celular": "(11) 99999-0000"
}
```

### 2.6 Deletar Usuário

**Endpoint:** `DELETE /usuarios/{id}`

**Exemplo:** `DELETE /usuarios/3`

**Resposta Sucesso (204):** (sem corpo)

---

## 📦 3. Gerenciamento de Itens

### 3.1 Listar Todos os Itens

**Endpoint:** `GET /items`

**Resposta (200):**
```json
[
  {
    "id": 1,
    "nomeItem": "Notebook Dell",
    "estadoItem": "Como novo",
    "tipoItem": "Eletrônico",
    "localRetirada": "Rua das Flores, 123",
    "dataPublicacao": "2025-01-15T10:30:00",
    "doadorId": 1,
    "doadorNome": "João Silva"
  },
  {
    "id": 2,
    "nomeItem": "Livros de Programação",
    "estadoItem": "Bom",
    "tipoItem": "Livro",
    "localRetirada": "Avenida Paulista, 1000",
    "dataPublicacao": "2025-01-16T14:45:00",
    "doadorId": 2,
    "doadorNome": "Maria Santos"
  }
]
```

### 3.2 Obter Item por ID

**Endpoint:** `GET /items/{id}`

**Exemplo:** `GET /items/1`

**Resposta (200):**
```json
{
  "id": 1,
  "nomeItem": "Notebook Dell",
  "estadoItem": "Como novo",
  "tipoItem": "Eletrônico",
  "localRetirada": "Rua das Flores, 123",
  "dataPublicacao": "2025-01-15T10:30:00",
  "doadorId": 1,
  "doadorNome": "João Silva"
}
```

### 3.3 Listar Itens de um Doador

**Endpoint:** `GET /items/doador/{doadorId}`

**Exemplo:** `GET /items/doador/1`

**Parâmetros:**
- `doadorId` (path) - ID do doador

**Resposta (200):**
```json
[
  {
    "id": 1,
    "nomeItem": "Notebook Dell",
    "estadoItem": "Como novo",
    "tipoItem": "Eletrônico",
    "localRetirada": "Rua das Flores, 123",
    "dataPublicacao": "2025-01-15T10:30:00",
    "doadorId": 1,
    "doadorNome": "João Silva"
  }
]
```

### 3.4 Criar Novo Item

**Endpoint:** `POST /items`

**Parâmetros:**
- `doadorId` (query) - ID do doador proprietário do item

**Requisição:**
```json
{
  "nomeItem": "Smartphone Samsung",
  "estadoItem": "Como novo",
  "tipoItem": "Eletrônico",
  "localRetirada": "Rua Central, 789"
}
```

**Exemplo URL:** `POST /items?doadorId=1`

**Resposta Sucesso (201):**
```json
{
  "id": 3,
  "nomeItem": "Smartphone Samsung",
  "estadoItem": "Como novo",
  "tipoItem": "Eletrônico",
  "localRetirada": "Rua Central, 789",
  "dataPublicacao": "2025-01-17T09:15:00",
  "doadorId": 1,
  "doadorNome": "João Silva"
}
```

**Resposta Erro (400):**
```json
"Dados inválidos ou doador não encontrado"
```

### 3.5 Atualizar Item

**Endpoint:** `PUT /items/{id}`

**Parâmetros:**
- `id` (path) - ID do item
- `doadorId` (query) - ID do doador (para validação de permissão)

**Requisição:**
```json
{
  "nomeItem": "Smartphone Samsung Atualizado",
  "estadoItem": "Bom",
  "tipoItem": "Eletrônico",
  "localRetirada": "Rua Central, 789 - Apto 100"
}
```

**Exemplo URL:** `PUT /items/3?doadorId=1`

**Resposta (200):**
```json
{
  "id": 3,
  "nomeItem": "Smartphone Samsung Atualizado",
  "estadoItem": "Bom",
  "tipoItem": "Eletrônico",
  "localRetirada": "Rua Central, 789 - Apto 100",
  "dataPublicacao": "2025-01-17T09:15:00",
  "doadorId": 1,
  "doadorNome": "João Silva"
}
```

**Resposta Erro (403):**
```json
(Sem permissão)
```

### 3.6 Deletar Item

**Endpoint:** `DELETE /items/{id}`

**Parâmetros:**
- `id` (path) - ID do item
- `doadorId` (query) - ID do doador (para validação de permissão)

**Exemplo URL:** `DELETE /items/3?doadorId=1`

**Resposta Sucesso (204):** (sem corpo)

---

## 🏪 4. Pontos de Coleta

### 4.1 Listar Todos os Pontos

**Endpoint:** `GET /pontos-coleta`

**Resposta (200):**
```json
[
  {
    "id": 1,
    "nomePonto": "Ponto Centro",
    "endereco": "Rua Principal, 100",
    "tipoPonto": "Eletrônicos"
  },
  {
    "id": 2,
    "nomePonto": "Ponto Sul",
    "endereco": "Avenida Secundária, 200",
    "tipoPonto": "Roupas"
  }
]
```

### 4.2 Obter Ponto por ID

**Endpoint:** `GET /pontos-coleta/{id}`

**Exemplo:** `GET /pontos-coleta/1`

**Resposta (200):**
```json
{
  "id": 1,
  "nomePonto": "Ponto Centro",
  "endereco": "Rua Principal, 100",
  "tipoPonto": "Eletrônicos"
}
```

### 4.3 Listar Pontos por Tipo

**Endpoint:** `GET /pontos-coleta/tipo/{tipo}`

**Exemplo:** `GET /pontos-coleta/tipo/Eletrônicos`

**Resposta (200):**
```json
[
  {
    "id": 1,
    "nomePonto": "Ponto Centro",
    "endereco": "Rua Principal, 100",
    "tipoPonto": "Eletrônicos"
  }
]
```

### 4.4 Criar Novo Ponto de Coleta

**Endpoint:** `POST /pontos-coleta`

**Requisição:**
```json
{
  "nomePonto": "Ponto Leste",
  "endereco": "Avenida Leste, 300",
  "tipoPonto": "Livros"
}
```

**Resposta Sucesso (201):**
```json
{
  "id": 3,
  "nomePonto": "Ponto Leste",
  "endereco": "Avenida Leste, 300",
  "tipoPonto": "Livros"
}
```

### 4.5 Atualizar Ponto de Coleta

**Endpoint:** `PUT /pontos-coleta/{id}`

**Exemplo:** `PUT /pontos-coleta/3`

**Requisição:**
```json
{
  "nomePonto": "Ponto Leste Atualizado",
  "endereco": "Avenida Leste, 300 - Sala 10",
  "tipoPonto": "Livros e Revistas"
}
```

**Resposta (200):**
```json
{
  "id": 3,
  "nomePonto": "Ponto Leste Atualizado",
  "endereco": "Avenida Leste, 300 - Sala 10",
  "tipoPonto": "Livros e Revistas"
}
```

### 4.6 Deletar Ponto de Coleta

**Endpoint:** `DELETE /pontos-coleta/{id}`

**Exemplo:** `DELETE /pontos-coleta/3`

**Resposta Sucesso (204):** (sem corpo)

---

## 🔍 Testando com cURL

### Exemplo 1: Listar Itens
```bash
curl -X GET "http://localhost:8080/api/v1/items" \
  -H "Content-Type: application/json"
```

### Exemplo 2: Criar Novo Item
```bash
curl -X POST "http://localhost:8080/api/v1/items?doadorId=1" \
  -H "Content-Type: application/json" \
  -d '{
    "nomeItem": "Tablet",
    "estadoItem": "Bom",
    "tipoItem": "Eletrônico",
    "localRetirada": "Rua Test, 123"
  }'
```

### Exemplo 3: Login
```bash
curl -X POST "http://localhost:8080/api/v1/auth/login" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "joao@email.com",
    "senha": "senha123"
  }'
```

---

## 📊 Códigos de Status

| Código | Significado |
|--------|------------|
| 200 | OK - Requisição bem-sucedida |
| 201 | CREATED - Recurso criado |
| 204 | NO CONTENT - Sucesso sem corpo |
| 400 | BAD REQUEST - Dados inválidos |
| 401 | UNAUTHORIZED - Autenticação falhou |
| 403 | FORBIDDEN - Sem permissão |
| 404 | NOT FOUND - Recurso não encontrado |
| 409 | CONFLICT - Conflito de dados |
| 500 | SERVER ERROR - Erro no servidor |

---

## 💡 Dicas Importantes

1. **Sempre incluir Content-Type:** Envie `Content-Type: application/json` em requisições POST/PUT
2. **Parâmetros de Query:** O `doadorId` é obrigatório em operações com itens
3. **Validação:** Todos os campos obrigatórios são validados automaticamente
4. **Permissões:** Para deletar ou atualizar um item, o doador deve ser o proprietário
5. **CORS:** A API permite requisições de qualquer origem (*)

---

**Para mais informações, acesse:** http://localhost:8080/swagger-ui.html

