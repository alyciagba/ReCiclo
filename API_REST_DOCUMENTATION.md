# 📚 Documentação da API REST - ReCiclo

## Base URL
```
http://localhost:8080/api/v1
```

---

## 🔐 Autenticação

### POST /auth/login
Autenticar usuário e obter informações de sessão.

**Request Body:**
```json
{
  "email": "usuario@example.com",
  "senha": "senha123"
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "name": "João Silva",
  "email": "usuario@example.com",
  "tipoPerfil": "USUARIO"
}
```

**Respostas de Erro:**
- `401 Unauthorized`: Usuário ou senha inválidos

---

## 👥 Usuários

### GET /usuarios
Listar todos os usuários (dados públicos).

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "name": "João Silva",
    "email": "joao@example.com",
    "tipoPerfil": "USUARIO",
    "cep": "12345-678",
    "municipio": "São Paulo",
    "estado": "SP"
  }
]
```

---

### GET /usuarios/{id}
Obter dados de um usuário específico.

**Path Parameters:**
- `id` (Long): ID do usuário

**Response (200 OK):**
```json
{
  "id": 1,
  "name": "João Silva",
  "email": "joao@example.com",
  "cpf": "123.456.789-00",
  "celular": "(11) 98765-4321",
  "genero": "M",
  "dataNascimento": "1990-05-15",
  "cep": "12345-678",
  "logradouro": "Rua A",
  "numero": "123",
  "bairro": "Centro",
  "municipio": "São Paulo",
  "estado": "SP",
  "complemento": "Apto 101",
  "tipoPerfil": "USUARIO"
}
```

**Respostas de Erro:**
- `404 Not Found`: Usuário não encontrado

---

### GET /usuarios/email/{email}
Obter usuário pelo email.

**Path Parameters:**
- `email` (String): Email do usuário

**Response (200 OK):**
```json
{
  "id": 1,
  "name": "João Silva",
  "email": "joao@example.com",
  "tipoPerfil": "USUARIO"
}
```

---

### POST /usuarios
Registrar novo usuário.

**Request Body:**
```json
{
  "name": "Maria Santos",
  "email": "maria@example.com",
  "senha": "senha123",
  "confirmarSenha": "senha123",
  "cpf": "123.456.789-00",
  "celular": "(11) 98765-4321",
  "genero": "F",
  "dataNascimento": "1995-03-20",
  "cep": "12345-678",
  "logradouro": "Rua B",
  "numero": "456",
  "bairro": "Vila Nova",
  "municipio": "Rio de Janeiro",
  "estado": "RJ",
  "complemento": "Apto 202"
}
```

**Response (201 Created):**
```json
{
  "id": 2,
  "name": "Maria Santos",
  "email": "maria@example.com",
  "tipoPerfil": "USUARIO",
  "cep": "12345-678",
  "municipio": "Rio de Janeiro",
  "estado": "RJ"
}
```

**Respostas de Erro:**
- `400 Bad Request`: Senhas não coincidem ou validação falhou
- `409 Conflict`: Email ou CPF já cadastrado

---

### PUT /usuarios/{id}
Atualizar dados do usuário (não atualiza email/CPF/senha).

**Path Parameters:**
- `id` (Long): ID do usuário

**Request Body:**
```json
{
  "name": "Maria Santos Silva",
  "celular": "(11) 99999-8888",
  "genero": "F",
  "dataNascimento": "1995-03-20",
  "cep": "54321-876",
  "logradouro": "Rua C",
  "numero": "789",
  "bairro": "Centro",
  "municipio": "Brasília",
  "estado": "DF",
  "complemento": "Apto 303"
}
```

**Response (200 OK):**
```json
{
  "id": 2,
  "name": "Maria Santos Silva",
  "email": "maria@example.com",
  "tipoPerfil": "USUARIO"
}
```

---

### DELETE /usuarios/{id}
Deletar usuário (requer autenticação).

**Path Parameters:**
- `id` (Long): ID do usuário

**Response (204 No Content)**

---

## 📦 Itens

### GET /items
Listar todos os itens.

**Query Parameters:**
- Nenhum

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "nomeItem": "Garrafa de Vidro",
    "estadoItem": "Novo",
    "tipoItem": "Vidro",
    "localRetirada": "Rua A, 123",
    "doadorId": 1,
    "doadorNome": "João Silva",
    "pontoDeColetaId": null,
    "pontoDeColetaNome": null
  }
]
```

---

### GET /items/{id}
Obter item específico.

**Path Parameters:**
- `id` (Long): ID do item

**Response (200 OK):**
```json
{
  "id": 1,
  "nomeItem": "Garrafa de Vidro",
  "estadoItem": "Novo",
  "tipoItem": "Vidro",
  "localRetirada": "Rua A, 123",
  "doadorId": 1,
  "doadorNome": "João Silva",
  "pontoDeColetaId": 1,
  "pontoDeColetaNome": "Ponto Centro"
}
```

---

### GET /items/doador/{doadorId}
Listar todos os itens de um doador.

**Path Parameters:**
- `doadorId` (Long): ID do doador

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "nomeItem": "Garrafa de Vidro",
    "estadoItem": "Novo",
    "tipoItem": "Vidro",
    "localRetirada": "Rua A, 123",
    "doadorId": 1,
    "doadorNome": "João Silva"
  }
]
```

---

### POST /items
Criar novo item (requer autenticação).

**Query Parameters:**
- `doadorId` (Long): ID do doador/usuário logado

**Request Body:**
```json
{
  "nomeItem": "Garrafa de Vidro",
  "estadoItem": "Novo",
  "tipoItem": "Vidro",
  "localRetirada": "Rua A, 123"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "nomeItem": "Garrafa de Vidro",
  "estadoItem": "Novo",
  "tipoItem": "Vidro",
  "localRetirada": "Rua A, 123",
  "doadorId": 1,
  "doadorNome": "João Silva"
}
```

---

### PUT /items/{id}
Atualizar item (requer ser o doador).

**Path Parameters:**
- `id` (Long): ID do item

**Query Parameters:**
- `doadorId` (Long): ID do doador/proprietário do item

**Request Body:**
```json
{
  "nomeItem": "Garrafa de Vidro Verde",
  "estadoItem": "Usado",
  "tipoItem": "Vidro",
  "localRetirada": "Rua A, 123"
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "nomeItem": "Garrafa de Vidro Verde",
  "estadoItem": "Usado",
  "tipoItem": "Vidro",
  "localRetirada": "Rua A, 123",
  "doadorId": 1,
  "doadorNome": "João Silva"
}
```

**Respostas de Erro:**
- `403 Forbidden`: Você não tem permissão para editar este item
- `404 Not Found`: Item não encontrado

---

### DELETE /items/{id}
Deletar item (requer ser o doador).

**Path Parameters:**
- `id` (Long): ID do item

**Query Parameters:**
- `doadorId` (Long): ID do doador/proprietário do item

**Response (204 No Content)**

**Respostas de Erro:**
- `403 Forbidden`: Você não tem permissão para deletar este item
- `404 Not Found`: Item não encontrado

---

## 🗺️ Pontos de Coleta

### GET /pontos-coleta
Listar todos os pontos de coleta.

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "nomePonto": "Ponto Centro",
    "endereco": "Rua Principal, 100",
    "tipoPonto": "Vidro"
  }
]
```

---

### GET /pontos-coleta/{id}
Obter ponto de coleta específico.

**Path Parameters:**
- `id` (Long): ID do ponto

**Response (200 OK):**
```json
{
  "id": 1,
  "nomePonto": "Ponto Centro",
  "endereco": "Rua Principal, 100",
  "tipoPonto": "Vidro"
}
```

---

### GET /pontos-coleta/tipo/{tipo}
Listar pontos de coleta por tipo.

**Path Parameters:**
- `tipo` (String): Tipo de ponto (ex: "Vidro", "Plástico", "Metal")

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "nomePonto": "Ponto Centro",
    "endereco": "Rua Principal, 100",
    "tipoPonto": "Vidro"
  }
]
```

---

### POST /pontos-coleta
Criar novo ponto de coleta (requer autenticação).

**Request Body:**
```json
{
  "nomePonto": "Ponto Vilela",
  "endereco": "Rua Secundária, 200",
  "tipoPonto": "Plástico"
}
```

**Response (201 Created):**
```json
{
  "id": 2,
  "nomePonto": "Ponto Vilela",
  "endereco": "Rua Secundária, 200",
  "tipoPonto": "Plástico"
}
```

---

### PUT /pontos-coleta/{id}
Atualizar ponto de coleta (requer autenticação).

**Path Parameters:**
- `id` (Long): ID do ponto

**Request Body:**
```json
{
  "nomePonto": "Ponto Vilela - Atualizado",
  "endereco": "Rua Secundária, 250",
  "tipoPonto": "Plástico"
}
```

**Response (200 OK):**
```json
{
  "id": 2,
  "nomePonto": "Ponto Vilela - Atualizado",
  "endereco": "Rua Secundária, 250",
  "tipoPonto": "Plástico"
}
```

---

### DELETE /pontos-coleta/{id}
Deletar ponto de coleta (requer autenticação).

**Path Parameters:**
- `id` (Long): ID do ponto

**Response (204 No Content)**

---

## 📊 Status de Respostas Padrão

| Código | Significado |
|--------|------------|
| `200` | OK - Requisição bem-sucedida |
| `201` | Created - Recurso criado com sucesso |
| `204` | No Content - Operação bem-sucedida, sem conteúdo |
| `400` | Bad Request - Dados inválidos |
| `401` | Unauthorized - Não autenticado |
| `403` | Forbidden - Sem permissão |
| `404` | Not Found - Recurso não encontrado |
| `409` | Conflict - Conflito (ex: email duplicado) |
| `500` | Internal Server Error - Erro do servidor |

---

## 🔒 Segurança

- Endpoints sem autenticação explícita aceitam requisições sem token
- Endpoints que requerem autenticação devem ser acessados com a sessão ativa
- Operações de PUT/DELETE em itens verificam se o usuário é o proprietário
- Senhas são criptografadas com BCrypt

---

## 🧪 Exemplos de Uso com cURL

### Registrar novo usuário
```bash
curl -X POST http://localhost:8080/api/v1/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "name": "João Silva",
    "email": "joao@example.com",
    "senha": "senha123",
    "confirmarSenha": "senha123",
    "cep": "12345-678",
    "logradouro": "Rua A",
    "numero": "123",
    "bairro": "Centro",
    "municipio": "São Paulo",
    "estado": "SP"
  }'
```

### Fazer login
```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "joao@example.com",
    "senha": "senha123"
  }'
```

### Listar todos os itens
```bash
curl http://localhost:8080/api/v1/items
```

### Criar novo item
```bash
curl -X POST "http://localhost:8080/api/v1/items?doadorId=1" \
  -H "Content-Type: application/json" \
  -d '{
    "nomeItem": "Garrafa de Vidro",
    "estadoItem": "Novo",
    "tipoItem": "Vidro",
    "localRetirada": "Rua A, 123"
  }'
```

### Listar pontos de coleta
```bash
curl http://localhost:8080/api/v1/pontos-coleta
```

---

## ⚠️ Notas Importantes

1. **CORS está habilitado** para todas as origens (`*`)
2. **Validação de dados**: Todos os campos obrigatórios são validados
3. **Formato de resposta**: Sempre JSON
4. **Tratamento de erros**: Respostas de erro incluem mensagens descritivas
5. **Permissões**: Itens só podem ser editados/deletados por seu proprietário

---

## 📝 Endpoints Resumo

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/auth/login` | Autenticar usuário |
| `GET` | `/usuarios` | Listar usuários |
| `GET` | `/usuarios/{id}` | Obter usuário |
| `GET` | `/usuarios/email/{email}` | Obter usuário por email |
| `POST` | `/usuarios` | Registrar usuário |
| `PUT` | `/usuarios/{id}` | Atualizar usuário |
| `DELETE` | `/usuarios/{id}` | Deletar usuário |
| `GET` | `/items` | Listar itens |
| `GET` | `/items/{id}` | Obter item |
| `GET` | `/items/doador/{doadorId}` | Listar itens do doador |
| `POST` | `/items` | Criar item |
| `PUT` | `/items/{id}` | Atualizar item |
| `DELETE` | `/items/{id}` | Deletar item |
| `GET` | `/pontos-coleta` | Listar pontos |
| `GET` | `/pontos-coleta/{id}` | Obter ponto |
| `GET` | `/pontos-coleta/tipo/{tipo}` | Listar pontos por tipo |
| `POST` | `/pontos-coleta` | Criar ponto |
| `PUT` | `/pontos-coleta/{id}` | Atualizar ponto |
| `DELETE` | `/pontos-coleta/{id}` | Deletar ponto |

