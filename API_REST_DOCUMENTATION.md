# API REST - ReCiclo

Documentacao de uso da API REST do ReCiclo.
O objetivo deste arquivo e servir como referencia rapida para desenvolvimento e avaliacao academica.

## Base URL

```text
http://localhost:8080/api/v1
```

## Regras gerais

- Formato de request/response: `application/json`
- IDs: `Long`
- Datas: `yyyy-MM-dd`
- Os exemplos abaixo usam valores ficticios
- Quando a API retorna erro simples, o corpo pode vir como string

---

## Autenticacao

### `POST /auth/login`
Realiza login com email e senha.

**Request body**

```json
{
  "email": "usuario@example.com",
  "senha": "senha123"
}
```

**200 OK**

```json
{
  "id": 1,
  "name": "Joao Silva",
  "email": "usuario@example.com",
  "tipoPerfil": "USUARIO"
}
```

**401 Unauthorized**

```json
"Usuario ou senha invalidos"
```

---

## Usuarios

### `GET /usuarios`
Retorna a lista completa de usuarios.

**200 OK**

```json
[
  {
    "id": 1,
    "name": "Joao Silva",
    "email": "joao@example.com",
    "cpf": "123.456.789-00",
    "celular": "(11) 98765-4321",
    "genero": "M",
    "dataNascimento": "1990-05-15",
    "cep": "12345-678",
    "logradouro": "Rua A",
    "numero": "123",
    "bairro": "Centro",
    "municipio": "Sao Paulo",
    "estado": "SP",
    "complemento": "Apto 101",
    "tipoPerfil": "USUARIO"
  }
]
```

### `GET /usuarios/{id}`
Retorna um usuario pelo ID.

**Path params**
- `id`: ID do usuario

**200 OK**

```json
{
  "id": 1,
  "name": "Joao Silva",
  "email": "joao@example.com",
  "cpf": "123.456.789-00",
  "celular": "(11) 98765-4321",
  "genero": "M",
  "dataNascimento": "1990-05-15",
  "cep": "12345-678",
  "logradouro": "Rua A",
  "numero": "123",
  "bairro": "Centro",
  "municipio": "Sao Paulo",
  "estado": "SP",
  "complemento": "Apto 101",
  "tipoPerfil": "USUARIO"
}
```

**404 Not Found**

```json
{}
```

### `GET /usuarios/email/{email}`
Busca usuario por email.

**Path params**
- `email`: email do usuario

**200 OK**

```json
{
  "id": 1,
  "name": "Joao Silva",
  "email": "joao@example.com",
  "cpf": "123.456.789-00",
  "celular": "(11) 98765-4321",
  "genero": "M",
  "dataNascimento": "1990-05-15",
  "cep": "12345-678",
  "logradouro": "Rua A",
  "numero": "123",
  "bairro": "Centro",
  "municipio": "Sao Paulo",
  "estado": "SP",
  "complemento": "Apto 101",
  "tipoPerfil": "USUARIO"
}
```

**404 Not Found**

```json
{}
```

### `POST /usuarios`
Cria um novo usuario.

**Request body**

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

**201 Created**

```json
{
  "id": 2,
  "name": "Maria Santos",
  "email": "maria@example.com",
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
  "complemento": "Apto 202",
  "tipoPerfil": "USUARIO"
}
```

**400 Bad Request**

```json
"As senhas nao coincidem"
```

**409 Conflict**

```json
"Ja existe um usuario com este e-mail ou CPF"
```

### `PUT /usuarios/{id}`
Atualiza dados cadastrais de um usuario existente.

**Path params**
- `id`: ID do usuario

**Request body**

```json
{
  "name": "Maria Santos Silva",
  "email": "maria@example.com",
  "senha": "senha123",
  "confirmarSenha": "senha123",
  "cpf": "123.456.789-00",
  "celular": "(11) 99999-8888",
  "genero": "F",
  "dataNascimento": "1995-03-20",
  "cep": "54321-876",
  "logradouro": "Rua C",
  "numero": "789",
  "bairro": "Centro",
  "municipio": "Brasilia",
  "estado": "DF",
  "complemento": "Apto 303"
}
```

**200 OK**

```json
{
  "id": 2,
  "name": "Maria Santos Silva",
  "email": "maria@example.com",
  "cpf": "123.456.789-00",
  "celular": "(11) 99999-8888",
  "genero": "F",
  "dataNascimento": "1995-03-20",
  "cep": "54321-876",
  "logradouro": "Rua C",
  "numero": "789",
  "bairro": "Centro",
  "municipio": "Brasilia",
  "estado": "DF",
  "complemento": "Apto 303",
  "tipoPerfil": "USUARIO"
}
```

**404 Not Found**

```json
{}
```

### `DELETE /usuarios/{id}`
Remove um usuario pelo ID.

**Path params**
- `id`: ID do usuario

**204 No Content**

---

## Itens

### `GET /items`
Lista todos os itens cadastrados.

**200 OK**

```json
[
  {
    "id": 1,
    "nomeItem": "Garrafa de Vidro",
    "estadoItem": "Novo",
    "tipoItem": "Vidro",
    "localRetirada": "Rua A, 123",
    "doadorId": 1,
    "doadorNome": "Joao Silva",
    "pontoDeColetaId": null,
    "pontoDeColetaNome": null
  }
]
```

### `GET /items/{id}`
Retorna um item por ID.

**Path params**
- `id`: ID do item

**200 OK**

```json
{
  "id": 1,
  "nomeItem": "Garrafa de Vidro",
  "estadoItem": "Novo",
  "tipoItem": "Vidro",
  "localRetirada": "Rua A, 123",
  "doadorId": 1,
  "doadorNome": "Joao Silva",
  "pontoDeColetaId": 1,
  "pontoDeColetaNome": "Ponto Centro"
}
```

**404 Not Found**

```json
{}
```

### `GET /items/doador/{doadorId}`
Lista itens por doador.

**Path params**
- `doadorId`: ID do doador

**200 OK**

```json
[
  {
    "id": 1,
    "nomeItem": "Garrafa de Vidro",
    "estadoItem": "Novo",
    "tipoItem": "Vidro",
    "localRetirada": "Rua A, 123",
    "doadorId": 1,
    "doadorNome": "Joao Silva",
    "pontoDeColetaId": null,
    "pontoDeColetaNome": null
  }
]
```

### `POST /items`
Cria um item.

**Query params**
- `doadorId`: ID do doador dono do item

**Request body**

```json
{
  "nomeItem": "Garrafa de Vidro",
  "estadoItem": "Novo",
  "tipoItem": "Vidro",
  "localRetirada": "Rua A, 123"
}
```

**201 Created**

```json
{
  "id": 1,
  "nomeItem": "Garrafa de Vidro",
  "estadoItem": "Novo",
  "tipoItem": "Vidro",
  "localRetirada": "Rua A, 123",
  "doadorId": 1,
  "doadorNome": "Joao Silva",
  "pontoDeColetaId": null,
  "pontoDeColetaNome": null
}
```

### `PUT /items/{id}`
Atualiza os dados de um item.

**Path params**
- `id`: ID do item

**Query params**
- `doadorId`: ID do doador usado para validar permissao

**Request body**

```json
{
  "nomeItem": "Garrafa de Vidro Verde",
  "estadoItem": "Usado",
  "tipoItem": "Vidro",
  "localRetirada": "Rua A, 123"
}
```

**200 OK**

```json
{
  "id": 1,
  "nomeItem": "Garrafa de Vidro Verde",
  "estadoItem": "Usado",
  "tipoItem": "Vidro",
  "localRetirada": "Rua A, 123",
  "doadorId": 1,
  "doadorNome": "Joao Silva",
  "pontoDeColetaId": null,
  "pontoDeColetaNome": null
}
```

**403 Forbidden**

```json
{}
```

**404 Not Found**

```json
{}
```

### `DELETE /items/{id}`
Exclui um item.

**Path params**
- `id`: ID do item

**Query params**
- `doadorId`: ID do doador usado para validar permissao

**204 No Content**

**403 Forbidden**

```json
{}
```

**404 Not Found**

```json
{}
```

---

## Pontos de coleta

### `GET /pontos-coleta`
Lista todos os pontos de coleta.

**200 OK**

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

### `GET /pontos-coleta/{id}`
Retorna um ponto de coleta por ID.

**Path params**
- `id`: ID do ponto de coleta

**200 OK**

```json
{
  "id": 1,
  "nomePonto": "Ponto Centro",
  "endereco": "Rua Principal, 100",
  "tipoPonto": "Vidro"
}
```

**404 Not Found**

```json
{}
```

### `GET /pontos-coleta/tipo/{tipo}`
Filtra pontos de coleta por tipo.

**Path params**
- `tipo`: tipo do ponto (ex.: `Vidro`, `Plastico`, `Metal`)

**200 OK**

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

### `POST /pontos-coleta`
Cria um novo ponto de coleta.

**Request body**

```json
{
  "nomePonto": "Ponto Vilela",
  "endereco": "Rua Secundaria, 200",
  "tipoPonto": "Plastico"
}
```

**201 Created**

```json
{
  "id": 2,
  "nomePonto": "Ponto Vilela",
  "endereco": "Rua Secundaria, 200",
  "tipoPonto": "Plastico"
}
```

### `PUT /pontos-coleta/{id}`
Atualiza um ponto de coleta.

**Path params**
- `id`: ID do ponto de coleta

**Request body**

```json
{
  "nomePonto": "Ponto Vilela - Atualizado",
  "endereco": "Rua Secundaria, 250",
  "tipoPonto": "Plastico"
}
```

**200 OK**

```json
{
  "id": 2,
  "nomePonto": "Ponto Vilela - Atualizado",
  "endereco": "Rua Secundaria, 250",
  "tipoPonto": "Plastico"
}
```

**404 Not Found**

```json
{}
```

### `DELETE /pontos-coleta/{id}`
Remove um ponto de coleta.

**Path params**
- `id`: ID do ponto de coleta

**204 No Content**

**404 Not Found**

```json
{}
```

---

## Codigos HTTP usados na API

| Codigo | Significado no projeto |
| --- | --- |
| `200` | Consulta ou atualizacao executada com sucesso |
| `201` | Registro criado |
| `204` | Exclusao realizada sem corpo de resposta |
| `400` | Dados invalidos ou regra de negocio nao atendida |
| `401` | Credenciais invalidas |
| `403` | Requisicao sem permissao para o recurso |
| `404` | Recurso nao encontrado |
| `409` | Conflito de unicidade (email/cpf) |
| `500` | Erro interno nao tratado |

---

## Seguranca (estado atual)

Com base no `SecurityConfig` atual:

- `POST /auth/login`: publico
- `GET /usuarios`, `POST /usuarios`, `GET /usuarios/email/{email}`: publicos
- `GET/POST/PUT/DELETE /pontos-coleta/**`: publicos
- `GET /items`: publico
- Outras rotas podem exigir sessao autenticada

> Importante: como a autorizacao depende de matchers do Spring Security, valide em ambiente de execucao os fluxos que exigem login.

---

## Teste rapido com curl

### Criar usuario

```bash
curl -X POST http://localhost:8080/api/v1/usuarios \\
  -H "Content-Type: application/json" \\
  -d '{
    "name": "Joao Silva",
    "email": "joao@example.com",
    "senha": "senha123",
    "confirmarSenha": "senha123",
    "cep": "12345-678",
    "logradouro": "Rua A",
    "numero": "123",
    "bairro": "Centro",
    "municipio": "Sao Paulo",
    "estado": "SP"
  }'
```

### Login

```bash
curl -X POST http://localhost:8080/api/v1/auth/login \\
  -H "Content-Type: application/json" \\
  -d '{
    "email": "joao@example.com",
    "senha": "senha123"
  }'
```

### Listar itens

```bash
curl http://localhost:8080/api/v1/items
```

### Criar item

```bash
curl -X POST "http://localhost:8080/api/v1/items?doadorId=1" \\
  -H "Content-Type: application/json" \\
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
