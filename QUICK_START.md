# Quick Start - Swagger Reciclo API

## ⚡ 3 Passos para Ver o Swagger em Ação

### Passo 1: Iniciar a Aplicação
```powershell
cd C:\Users\alyci\IdeaProjects\ReCiclo
.\scripts\start-safe.ps1
```

Se sua politica de execucao bloquear scripts, rode uma vez no PowerShell atual:
```powershell
Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass
```

Aguarde ate ver a mensagem:
```
Started RecicloApplication in X.XXX seconds
```

### Passo 2: Abrir Swagger UI
Acesse no navegador:
```
http://localhost:8080/swagger-ui.html
```

### Passo 3: Testar um Endpoint

#### Exemplo 1: Listar Todos os Usuários
1. Clique em **"Usuários"** (verde)
2. Clique em **"GET /api/v1/usuarios"**
3. Clique em **"Try it out"** (azul)
4. Clique em **"Execute"** (azul)
5. Veja a resposta em **"Server response"**

#### Exemplo 2: Criar um Novo Item
1. Clique em **"Items"** (verde)
2. Clique em **"POST /api/v1/items"** (verde)
3. Clique em **"Try it out"**
4. Na caixa "doadorId", insira: `1`
5. No "Request body", altere para:
```json
{
  "nomeItem": "Smartphone Samsung",
  "estadoItem": "Como novo",
  "tipoItem": "Eletrônico",
  "localRetirada": "Rua das Flores, 123"
}
```
6. Clique em **"Execute"**
7. Veja a resposta com o item criado

---

## 📱 Interface Swagger UI

```
┌─────────────────────────────────────────────────┐
│  API Reciclo    Documentação da API REST       │
│  v1.0.0    ☰ Select a definition              │
├─────────────────────────────────────────────────┤
│                                                 │
│  📝 Tags (Agrupamentos)                        │
│  ├── 🔑 Autenticação     (1 endpoint)          │
│  ├── 👥 Usuários         (6 endpoints)         │
│  ├── 📦 Items            (6 endpoints)         │
│  └── 🏪 Pontos de Coleta (6 endpoints)         │
│                                                 │
│  Schemas (Modelos)                             │
│  ├── ItemDTO                                   │
│  ├── UsuarioCadastroDTO                        │
│  └── ... mais 8                                │
│                                                 │
└─────────────────────────────────────────────────┘
```

---

## 🎯 Endpoints Principais

### 🔑 Autenticação
```
POST /api/v1/auth/login
├── Email de usuário
├── Senha
└── Retorna: ID, Nome, Email, Perfil
```

### 👥 Usuários
```
GET    /api/v1/usuarios        - Listar todos
GET    /api/v1/usuarios/{id}   - Obter por ID
GET    /api/v1/usuarios/email/{email} - Buscar por email
POST   /api/v1/usuarios        - Criar novo
PUT    /api/v1/usuarios/{id}   - Atualizar
DELETE /api/v1/usuarios/{id}   - Deletar
```

### 📦 Items
```
GET    /api/v1/items           - Listar todos
GET    /api/v1/items/{id}      - Obter por ID
GET    /api/v1/items/doador/{doadorId} - Itens do doador
POST   /api/v1/items           - Criar novo
PUT    /api/v1/items/{id}      - Atualizar
DELETE /api/v1/items/{id}      - Deletar
```

### 🏪 Pontos de Coleta
```
GET    /api/v1/pontos-coleta   - Listar todos
GET    /api/v1/pontos-coleta/{id} - Obter por ID
GET    /api/v1/pontos-coleta/tipo/{tipo} - Filtrar por tipo
POST   /api/v1/pontos-coleta   - Criar novo
PUT    /api/v1/pontos-coleta/{id} - Atualizar
DELETE /api/v1/pontos-coleta/{id} - Deletar
```

---

## 📊 Resposta Exemplo

### Request
```
GET /api/v1/items
```

### Response (200 OK)
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
    "nomeItem": "Smartphone Samsung",
    "estadoItem": "Bom",
    "tipoItem": "Eletrônico",
    "localRetirada": "Avenida Paulista, 1000",
    "dataPublicacao": "2025-01-16T14:45:00",
    "doadorId": 2,
    "doadorNome": "Maria Santos"
  }
]
```

---

## 🎨 Cores e Significados no Swagger

```
🟢 GET     - Recuperar dados (seguro)
🟡 POST    - Criar novo dado
🟠 PUT     - Atualizar dado
🔴 DELETE  - Remover dado
```

---

## ⚠️ Códigos de Erro Comuns

```
200 ✅ OK - Requisição bem-sucedida
201 ✅ CRIADO - Novo recurso criado
204 ✅ SEM CONTEÚDO - Sucesso sem dados
400 ❌ BAD REQUEST - Dados inválidos
401 ❌ NÃO AUTORIZADO - Falha na autenticação
403 ❌ PROIBIDO - Sem permissão
404 ❌ NÃO ENCONTRADO - Recurso não existe
409 ❌ CONFLITO - E-mail/CPF duplicado
500 ❌ ERRO DO SERVIDOR
```

---

## 💡 Dicas Práticas

### ✅ Para Testar com Sucesso

1. **Leia a descrição do endpoint**
   - Está indicando o que faz

2. **Verifique os parâmetros necessários**
   - Obrigatórios: em **negrito**
   - Opcionais: sem negrito

3. **Preencha o body corretamente**
   - Use as validações como guia
   - Veja exemplos de response

4. **Verifique os status codes**
   - 200/201 = Sucesso
   - 400+ = Erro

5. **Teste do simples para o complexo**
   - Comece com GET
   - Depois teste POST
   - Finalmente tente PUT/DELETE

---

## 🔄 Fluxo Típico de Uso

### 1️⃣ Criar Usuário
```
POST /api/v1/usuarios
{
  "name": "João",
  "email": "joao@email.com",
  "celular": "(11) 99999-9999",
  ...
}
↓
Response: usuarioId = 1
```

### 2️⃣ Criar Item (para esse usuário)
```
POST /api/v1/items?doadorId=1
{
  "nomeItem": "Notebook",
  "estadoItem": "Como novo",
  ...
}
↓
Response: itemId = 1
```

### 3️⃣ Listar Itens do Usuário
```
GET /api/v1/items/doador/1
↓
Response: [item 1, item 2, ...]
```

### 4️⃣ Atualizar Item
```
PUT /api/v1/items/1?doadorId=1
{
  "nomeItem": "Notebook Atualizado",
  ...
}
↓
Response: item atualizado
```

### 5️⃣ Deletar Item
```
DELETE /api/v1/items/1?doadorId=1
↓
Response: 204 (sucesso)
```

---

## 📞 Precisa de Ajuda?

### 📖 Documentação Completa
```
API_REST_DOCUMENTATION.md
API_USAGE_GUIDE.md
README.md
STARTUP_GUIDE.md
```

### 🐛 Problemas?

**Porta ja esta em uso?**
```powershell
netstat -ano | findstr :8080
taskkill /PID <PID> /T /F
```

Ou use o script pronto (recomendado):
```powershell
.\scripts\start-safe.ps1
```

**Banco de dados nao conecta?**
```powershell
# Verifique PostgreSQL
# Edite as credenciais em application.properties
```

**Swagger nao aparece?**
```powershell
# Verifique se a aplicacao iniciou
# Acesse http://localhost:8080
# Se funcionar, acesse http://localhost:8080/swagger-ui.html
```

---

## 🎉 Pronto!

Você tem uma API REST completamente documentada com Swagger!

- ✅ 19 endpoints prontos para usar
- ✅ Documentação interativa
- ✅ Exemplos de requisição e resposta
- ✅ Testes diretamente na UI
- ✅ Pronto para produção

**Divirta-se testando! 🚀**

---

**Última atualização:** 2025  
**Versão:** 1.0.0  
**Status:** ✅ OPERACIONAL
