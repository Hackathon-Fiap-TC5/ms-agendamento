# Database Configuration Guide for New Project

Este guia descreve os passos necessários para configurar o banco de dados para um novo projeto que utilizará o mesmo ambiente, mas com um nome de banco de dados diferente.

## 📋 Pré-requisitos

- Acesso ao servidor MySQL/Cloud SQL
- Credenciais de acesso ao banco de dados
- Permissões para criar bancos de dados e usuários (se necessário)

## 🔧 Passos de Configuração

### 1. Criar o Banco de Dados

#### Para Ambiente Local (MySQL)

Conecte-se ao MySQL e crie o novo banco de dados:

```sql
-- Conectar ao MySQL
mysql -u root -p

-- Criar o novo banco de dados (substitua 'novo_db_name' pelo nome desejado)
CREATE DATABASE novo_db_name CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Verificar se o banco foi criado
SHOW DATABASES;
```

#### Para Ambiente Cloud SQL (Google Cloud)

**Opção A: Via Console do Google Cloud**

1. Acesse o [Google Cloud Console](https://console.cloud.google.com/)
2. Navegue até **SQL** > **Instâncias**
3. Selecione a instância: `fiap-474820:us-central1:ms-agendamento`
4. Clique em **Databases**
5. Clique em **Create Database**
6. Informe o nome do novo banco de dados
7. Selecione o charset: `utf8mb4`
8. Clique em **Create**

**Opção B: Via gcloud CLI**

```bash
# Autenticar no Google Cloud
gcloud auth login

# Definir o projeto
gcloud config set project fiap-474820

# Criar o banco de dados
gcloud sql databases create novo_db_name \
  --instance=ms-agendamento \
  --charset=utf8mb4 \
  --collation=utf8mb4_unicode_ci
```

**Opção C: Via SQL direto**

Conecte-se ao Cloud SQL e execute:

```sql
CREATE DATABASE novo_db_name CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 2. Configurar Permissões do Usuário

Certifique-se de que o usuário do banco de dados tem acesso ao novo banco:

#### Para Ambiente Local

```sql
-- Conceder todos os privilégios ao usuário no novo banco
GRANT ALL PRIVILEGES ON novo_db_name.* TO 'feedback'@'%';
FLUSH PRIVILEGES;

-- Verificar permissões
SHOW GRANTS FOR 'feedback'@'%';
```

#### Para Ambiente Cloud SQL

O usuário já configurado (`DB_USERNAME`) deve ter acesso automático a todos os bancos de dados na instância, mas você pode verificar:

```sql
-- Verificar usuários e permissões
SELECT User, Host FROM mysql.user;

-- Se necessário, conceder permissões explicitamente
GRANT ALL PRIVILEGES ON novo_db_name.* TO 'seu_usuario'@'%';
FLUSH PRIVILEGES;
```

### 3. Atualizar Configurações da Aplicação

#### 3.1. Atualizar `application.properties` (Produção/Cloud Run)

Edite o arquivo `src/main/resources/application.properties`:

```properties
# Alterar apenas o nome do banco na URL
# ANTES:
spring.datasource.url=jdbc:mysql:///feedback

# DEPOIS (substitua 'novo_db_name' pelo nome do novo banco):
spring.datasource.url=jdbc:mysql:///novo_db_name
```

**Nota:** Para Cloud SQL, a URL usa o formato `jdbc:mysql:///nome_do_banco` (sem host/porta, pois usa o socket factory).

#### 3.2. Atualizar `application-local.properties` (Desenvolvimento Local)

Edite o arquivo `src/main/resources/application-local.properties`:

```properties
# Alterar apenas o nome do banco na URL
# ANTES:
spring.datasource.url=jdbc:mysql://localhost:3308/feedback?useSSL=false&serverTimezone=UTC

# DEPOIS (substitua 'novo_db_name' pelo nome do novo banco):
spring.datasource.url=jdbc:mysql://localhost:3308/novo_db_name?useSSL=false&serverTimezone=UTC
```

### 4. Verificar Configurações que Permanecem Iguais

As seguintes configurações devem permanecer **inalteradas** (mesmas para todos os projetos):

#### Variáveis de Ambiente (Cloud Run)
- `DB_USERNAME` - Usuário do banco de dados
- `DB_PASSWORD` - Senha do banco de dados
- `RABBIT_HOST` - Host do RabbitMQ
- `RABBIT_PORT` - Porta do RabbitMQ
- `RABBIT_USERNAME` - Usuário do RabbitMQ
- `RABBIT_PASSWORD` - Senha do RabbitMQ
- `PORT` - Porta da aplicação

#### Configurações do Cloud SQL
- `spring.datasource.cloud-sql-instance=fiap-474820:us-central1:ms-agendamento`
- `spring.datasource.ip-types=PRIVATE`

#### Configurações do RabbitMQ
- Todas as configurações do RabbitMQ permanecem iguais

### 5. Executar Scripts de Inicialização

Os scripts SQL de inicialização (`statusConsulta.sql` e `statusNotificacao.sql`) serão executados automaticamente pela aplicação na primeira inicialização, desde que:

- O banco de dados esteja criado
- O usuário tenha permissões adequadas
- As tabelas sejam criadas pelo Hibernate/JPA (modo `update` no local ou via migrations)

#### Para Ambiente Local

O Hibernate criará as tabelas automaticamente com `spring.jpa.hibernate.ddl-auto=update`, e os scripts serão executados após a criação das tabelas.

#### Para Ambiente Cloud Run

Certifique-se de que as tabelas existem antes de executar os scripts. Você pode:

1. Executar manualmente os scripts SQL após criar as tabelas
2. Ou usar migrations (Flyway/Liquibase) para gerenciar o schema

### 6. Testar a Conexão

#### Teste Local

```bash
# Executar a aplicação com profile local
mvn spring-boot:run -Dspring-boot.run.profiles=local

# Verificar logs para confirmar conexão bem-sucedida
# Procurar por mensagens como:
# "HikariPool-1 - Starting..."
# "HikariPool-1 - Start completed."
```

#### Teste Cloud Run

Após fazer o deploy, verifique os logs:

```bash
# Ver logs do Cloud Run
gcloud run services logs read ms-agendamento --limit=50

# Ou via console
# Cloud Run > ms-agendamento > Logs
```

### 7. Checklist de Configuração

Use este checklist para garantir que tudo está configurado corretamente:

- [ ] Banco de dados criado no MySQL/Cloud SQL
- [ ] Usuário tem permissões no novo banco de dados
- [ ] `application.properties` atualizado com novo nome do banco
- [ ] `application-local.properties` atualizado com novo nome do banco
- [ ] Variáveis de ambiente configuradas (Cloud Run)
- [ ] Scripts SQL de inicialização disponíveis no projeto
- [ ] Tabelas criadas (via Hibernate ou migrations)
- [ ] Conexão testada localmente
- [ ] Deploy testado no Cloud Run (se aplicável)

## 🔍 Troubleshooting

### Erro: "Unknown database 'novo_db_name'"

**Causa:** Banco de dados não foi criado.

**Solução:** Execute o passo 1 (Criar o Banco de Dados).

### Erro: "Access denied for user"

**Causa:** Usuário não tem permissões no novo banco.

**Solução:** Execute o passo 2 (Configurar Permissões do Usuário).

### Erro: "Table doesn't exist"

**Causa:** Tabelas não foram criadas antes dos scripts de inicialização.

**Solução:** 
- Para local: Verifique se `spring.jpa.hibernate.ddl-auto=update` está configurado
- Para Cloud Run: Execute as migrations ou crie as tabelas manualmente

### Erro de Conexão no Cloud Run

**Causa:** Configuração incorreta do Cloud SQL Socket Factory.

**Solução:** Verifique se:
- `spring.datasource.cloud-sql-instance` está correto
- O Cloud Run tem permissão para acessar o Cloud SQL
- A conexão está usando IP privado (`PRIVATE`)

## 📝 Exemplo Completo

Suponha que você queira criar um novo projeto chamado `ms-paciente` com banco de dados `paciente`:

### 1. Criar Banco
```sql
CREATE DATABASE paciente CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 2. Atualizar `application.properties`
```properties
spring.datasource.url=jdbc:mysql:///paciente
```

### 3. Atualizar `application-local.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3308/paciente?useSSL=false&serverTimezone=UTC
```

### 4. Manter todas as outras configurações iguais

## 🔗 Referências

- [Google Cloud SQL Documentation](https://cloud.google.com/sql/docs)
- [Spring Boot Data Source Configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/data.html#data.sql.datasource)
- [Cloud SQL Socket Factory](https://github.com/GoogleCloudPlatform/cloud-sql-jdbc-socket-factory)
