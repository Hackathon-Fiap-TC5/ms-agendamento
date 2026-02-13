# MS Agendamento SUS

Microserviço responsável pelo ciclo de vida do agendamento de consultas do SUS (Sistema Único de Saúde) e publicação de eventos de comparecimento.

## 📋 Sobre o Projeto

Este projeto faz parte do Hackathon da FIAP - 5º módulo e implementa uma API REST para gerenciar agendamentos de consultas médicas, incluindo:

- Criação, consulta e cancelamento de agendamentos
- Gerenciamento de status de consulta e notificação
- Publicação de eventos de comparecimento via RabbitMQ
- Integração com Cloud SQL (MySQL) para persistência de dados
 
## 🛠️ Tecnologias

- **Java 21** - Linguagem de programação
- **Spring Boot 4.0.2** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **MySQL** - Banco de dados relacional
- **RabbitMQ** - Mensageria para eventos
- **MapStruct** - Mapeamento de objetos
- **Lombok** - Redução de boilerplate
- **OpenAPI/Swagger** - Documentação da API
- **JUnit 5** - Testes unitários
- **Mockito** - Mocking em testes
- **Testcontainers** - Biblioteca disponível para testes de integração (não utilizada atualmente)
- **JaCoCo** - Cobertura de código

## 📁 Estrutura do Projeto

O projeto segue os princípios de Clean Architecture, organizado em camadas:

```
src/main/java/com/fiap/agendamento/
├── entrypoint/          # Camada de entrada (Controllers)
│   └── controllers/
│       ├── mappers/     # MapStruct DTO ↔ Domain
│       └── presenter/   # Conversão de DTOs
├── application/         # Camada de aplicação (Use Cases)
│   └── usecase/
│       ├── agendamento/
│       └── status/consulta|notificacao/
├── domain/              # Camada de domínio (Modelos e Serviços)
│   ├── model/           # Entidades de domínio
│   ├── domain/service/  # Serviços de domínio
│   └── exception/       # Exceções customizadas
└── infrastructure/      # Camada de infraestrutura
    ├── database/       # Repositórios, entidades JPA e gateways
    ├── publisher/       # Publicação de eventos RabbitMQ
    ├── listeners/       # Consumidores de mensagens RabbitMQ
    └── config/         # Configurações e beans
```

## 🚀 Pré-requisitos

- Java 21 ou superior
- Maven 3.9+
- MySQL 8.0+ (ou acesso ao Cloud SQL)
- RabbitMQ 3.8+
- Docker (opcional, para execução via container)

## ⚙️ Configuração

### Variáveis de Ambiente

O projeto utiliza variáveis de ambiente para configuração. Para ambiente local, configure no arquivo `application-local.properties` (ou crie um baseado no existente):

#### Banco de Dados (local)
```properties
spring.datasource.url=jdbc:mysql://localhost:3307/ms_agendamento?useSSL=false&serverTimezone=UTC
spring.datasource.username=agendamento
spring.datasource.password=admin
```

#### RabbitMQ
```properties
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=admin
spring.rabbitmq.password=admin123
```

### Cloud Run (Produção)

Para deploy no Google Cloud Run, configure as seguintes variáveis de ambiente:

- `SPRING_DATASOURCE_URL` - URL de conexão JDBC (ou use o padrão no `application.properties`)
- `DB_USERNAME` - Usuário do banco de dados
- `DB_PASSWORD` - Senha do banco de dados
- `RABBIT_HOST` - Host do RabbitMQ
- `RABBIT_PORT` - Porta do RabbitMQ
- `RABBIT_USERNAME` - Usuário do RabbitMQ
- `RABBIT_PASSWORD` - Senha do RabbitMQ
- `PORT` - Porta da aplicação (padrão: 8080)

## 🏃 Executando a Aplicação

### Localmente

1. Clone o repositório:
```bash
git clone <repository-url>
cd ms-agendamento
```

2. Configure o banco de dados MySQL e RabbitMQ

3. Execute a aplicação com o profile local:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

Ou compile e execute:
```bash
mvn clean install
java -jar target/ms-agendamento-1.0.0-SNAPSHOT.jar --spring.profiles.active=local
```

A aplicação estará disponível em `http://localhost:9095`.

> **Nota:** O profile `local` utiliza `server.servlet.context-path=/ms-agendamento`. As URLs base incluem este prefixo:
> - Base: `http://localhost:9095/ms-agendamento`
> - Swagger: `http://localhost:9095/ms-agendamento/swagger-ui.html`

### Docker

1. Construa a imagem:
```bash
docker build -t ms-agendamento .
```

2. Execute o container:
```bash
docker run -p 8080:8080 \
  -e DB_USERNAME=seu_usuario \
  -e DB_PASSWORD=sua_senha \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://seu_host:3306/ms_agendamento?sslMode=PREFERRED&serverTimezone=UTC \
  -e RABBIT_HOST=seu_host \
  -e RABBIT_PORT=5672 \
  -e RABBIT_USERNAME=seu_usuario_rabbit \
  -e RABBIT_PASSWORD=sua_senha_rabbit \
  ms-agendamento
```

> **Nota:** Para usar um banco diferente do padrão, defina `SPRING_DATASOURCE_URL`. O `application.properties` possui uma URL padrão para Cloud SQL.

## 📚 API Endpoints

A documentação completa da API está disponível via Swagger UI quando a aplicação estiver em execução:

- **Local (profile `local`):** `http://localhost:9095/ms-agendamento/swagger-ui.html`
- **Docker/Produção:** `http://localhost:8080/swagger-ui.html`
- **OpenAPI Spec:** `http://localhost:9095/ms-agendamento/v3/api-docs` (local)

### Principais Endpoints

#### Agendamentos
- `POST /v1/agendamentos` - Criar novo agendamento
- `GET /v1/agendamentos?idAgendamento={id}` - Consultar agendamento por ID
- `GET /v1/agendamentos/cns?cns={cns}` - Consultar agendamentos por CNS do paciente
- `PATCH /v1/agendamentos?idAgendamento={id}` - Cancelar agendamento (retorna 200)
- `PATCH /v1/agendamentos/status-consulta?idAgendamento={id}` - Atualizar status da consulta
- `PATCH /v1/agendamentos/status-notificacao?idAgendamento={id}` - Atualizar status da notificação

> Com o profile `local`, os paths incluem o context path: `/ms-agendamento/v1/agendamentos`

#### Status de Consulta
- `POST /v1/status-consulta` - Criar status de consulta
- `GET /v1/status-consulta` - Listar todos os status de consulta
- `PUT /v1/status-consulta/{id}` - Atualizar status de consulta
- `DELETE /v1/status-consulta/{id}` - Remover status de consulta

#### Status de Notificação
- `POST /v1/status-notificacao` - Criar status de notificação
- `GET /v1/status-notificacao` - Listar todos os status de notificação
- `PUT /v1/status-notificacao/{id}` - Atualizar status de notificação
- `DELETE /v1/status-notificacao/{id}` - Remover status de notificação

#### Health Check
- `GET /health` - Verificar saúde da aplicação
- `GET /` - Endpoint raiz (retorna o mesmo que `/health`)

## 🧪 Testes

### Executar Testes

```bash
# Executar todos os testes
mvn test

# Executar testes com cobertura e relatório
mvn clean verify
```

### Estrutura dos Testes

Os testes seguem a mesma arquitetura do projeto, organizados por camada:

```
src/test/java/com/fiap/agendamento/
├── entrypoint/controllers/           # Testes dos controllers REST
│   ├── AgendamentosControllerTest
│   ├── StatusConsultaControllerTest
│   ├── StatusNotificacaoControllerTest
│   ├── HealthControllerTest
│   └── presenter/                    # Testes dos presenters (conversão DTO)
│       ├── AgendamentoPresenterTest
│       ├── StatusConsultaPresenterTest
│       └── StatusNotificacaoPresenterTest
├── application/usecase/              # Testes dos casos de uso
│   ├── agendamento/implementations/
│   └── status/consulta|notificacao/implementation/
├── domain/
│   ├── model/                        # Testes dos modelos de domínio
│   │   ├── AgendamentoDomainTest
│   │   ├── StatusConsultaDomainTest
│   │   ├── StatusNotificacaoDomainTest
│   │   └── EventoComparecimentoMessageDomainTest
│   ├── domain/service/implementations/  # Testes dos serviços de domínio
│   └── exception/                    # Testes das exceções customizadas
```

### Cobertura de Código

O projeto utiliza JaCoCo para análise de cobertura. O relatório é gerado em:
```
target/site/jacoco/index.html
```

**Requisitos de Cobertura:**
- Mínimo de 80% de cobertura de instruções
- Mínimo de 80% de cobertura de branches
- Aplicado nas camadas: `entrypoint`, `application` e `domain`

**Cobertura Atual (camadas verificadas):**
- Controllers: 100%
- Domain models: 100%
- Domain services: 100%
- Use cases (status): 100%
- Use cases (agendamento): 83%
- Presenters: 90%

### Cenários de Teste

Os testes cobrem:

- **Happy path**: fluxos principais de sucesso
- **Erros**: cenários de exceção (ex.: `AgendamentoNaoEncontradoException`)
- **Casos extremos**: listas vazias, conversões de DTO

> **Total:** 81 testes unitários cobrindo controllers, use cases, domain services, presenters, models e exceptions.

## 🏗️ Build e Deploy

### Build

```bash
mvn clean install
```

### Deploy no Cloud Run

O projeto está otimizado para Google Cloud Run com:

- **Lazy Initialization**: Inicialização sob demanda para startup rápido
- **JVM Otimizada**: Configurações G1GC e memória otimizadas para containers de 512MB
- **Cloud SQL Socket Factory**: Conexão otimizada com Cloud SQL

Para fazer deploy:

1. Construa a imagem Docker:
```bash
docker build -t gcr.io/[PROJECT-ID]/ms-agendamento .
```

2. Faça push para Google Container Registry:
```bash
docker push gcr.io/[PROJECT-ID]/ms-agendamento
```

3. Deploy no Cloud Run:
```bash
gcloud run deploy ms-agendamento \
  --image gcr.io/[PROJECT-ID]/ms-agendamento \
  --platform managed \
  --region us-central1 \
  --allow-unauthenticated
```

## 📊 Banco de Dados

O projeto utiliza scripts SQL para inicialização de dados de referência:

- `src/main/resources/db/statusConsulta.sql` - Status iniciais de consulta
- `src/main/resources/db/statusNotificacao.sql` - Status iniciais de notificação

Estes scripts são executados automaticamente na inicialização da aplicação.

> **📖 Guia de Configuração de Banco de Dados:** Para configurar o banco de dados em um novo projeto com o mesmo ambiente, consulte o arquivo [DATABASE_SETUP.md](DATABASE_SETUP.md).

## 🔄 Mensageria

O microserviço publica eventos no RabbitMQ quando há mudanças no status de agendamentos. Os eventos contêm informações sobre:

- CNS do paciente
- Status da consulta
- Status da notificação

## 📝 Licença

Este projeto foi desenvolvido para o Hackathon da FIAP - 5º módulo.

## 👥 Contribuidores

- Equipe FIAP Hackathon

## 📞 Suporte

Para questões ou problemas, abra uma issue no repositório do projeto.
