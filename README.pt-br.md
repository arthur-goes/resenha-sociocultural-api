# Resenha Sociocultural API

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-Enabled-blue.svg)](https://www.docker.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

> 🇺🇸 **For the English version of this document, [click here](README.md).**

## 📖 Visão Geral

**Resenha Sociocultural** é um projeto social dedicado a acolher jovens de comunidades de baixa renda em grupos de discussão focados no desenvolvimento da maturidade.

Esta API é uma solução de backend completa projetada para gerenciar o projeto Resenha Sociocultural. Ela centraliza a administração dos jovens participantes, agendamento de encontros, controle de frequência e acompanhamento comportamental (sistema de *strikes* e pontos de participação).

O projeto foi construído com uma abordagem **Contract-First** (Contrato Primeiro), garantindo uma especificação de API robusta e bem documentada antes da implementação. Utiliza uma stack Java moderna, incluindo um Servidor de Autorização OAuth2 customizado e testes de integração rigorosos com Testcontainers.

## 🚀 Funcionalidades Chave

*   **Gestão de Jovens:** Ciclo de vida completo dos participantes (cadastro, atualizações, contatos de emergência).
*   **Eventos e Frequência:** Criação de encontros e rastreamento de presença com atualizações automáticas de status.
*   **Sistema Disciplinar:** Sistema de "Strikes" para gerenciar infrações comportamentais e pontos de participação.
*   **Design Contract-First:** Interfaces da API geradas automaticamente a partir de uma especificação OpenAPI.
*   **Servidor de Autorização OAuth2:** Diferente de implementações JWT simples, este projeto implementa um **Authorization Server** completo usando `spring-boot-starter-oauth2-authorization-server`.
*   **Controle de Acesso (RBAC):** Permissões granulares (Admin, Gerente, Coordenador, Usuário) protegidas por OAuth2.
*   **Testes de Integração com Testcontainers:** Nenhum banco de dados é "mockado". Um container PostgreSQL efêmero é iniciado para garantir que o código funcione contra um ambiente de banco de dados real.

## 🔮 Roadmap e Melhorias Futuras

Este projeto está em **fase ativa de desenvolvimento**. As seguintes funcionalidades estão planejadas para o próximo ciclo de lançamento visando preparar a aplicação para um ambiente de produção:

*   **Endpoints de Gestão Administrativa:** Desenvolvimento de endpoints para gestão granular de entidades e controle de acesso de usuários.
*   **Relatórios:** Implementação de um módulo de relatórios para visualizar dados de frequência e comportamento.
*   **Versionamento de Banco de Dados:** Implementação do **Flyway** para gerenciar migrações de schema, substituindo o mecanismo atual de `ddl-auto` usado para prototipagem rápida.
*   **Pipeline CI/CD:** Automação do processo de deploy para um provedor de nuvem (AWS ou Render) usando GitHub Actions.
*   **Provedor de Identidade Externo:** Transição do Servidor de Autorização Customizado (embarcado) para um provedor externo como **Keycloak** ou **Auth0** para maior segurança e escalabilidade.
*   **Observabilidade:** Integração com **Prometheus** e **Grafana** para métricas e monitoramento em tempo real.

## 🛠️ Stack Tecnológica e Arquitetura

Este projeto segue uma estrutura de **Empacotamento por Funcionalidade** (Feature-Based Packaging) para garantir modularidade e manutenibilidade.

*   **Core:** Java 17, Spring Boot 3
*   **Banco de Dados:** PostgreSQL (Dockerizado), H2 (Testes)
*   **ORM:** Spring Data JPA / Hibernate
*   **Segurança:** Spring Security, OAuth2 Authorization Server (JWT)
*   **Especificação da API:** OpenAPI 3.0, Swagger UI, SpringDoc
*   **Testes:** JUnit 5, Mockito, **Testcontainers** (Testes de Integração)
*   **Ferramentas:** Maven, Docker, Docker Compose, MapStruct, Lombok

## 🏗️ Destaques da Arquitetura

### Abordagem Contract-First
Ao contrário do desenvolvimento tradicional *code-first*, esta API usa uma definição `openapi.yaml` como a única fonte da verdade. O plugin `openapi-generator-maven-plugin` gera as interfaces Java, garantindo que a implementação siga estritamente o contrato da API.

### Segurança
A aplicação implementa um **Servidor de Autorização OAuth2** autocontido. Ele emite JWTs assinados com chaves RSA, gerenciando todo o fluxo de autenticação e autorização.

## ⚙️ Começando

### Pré-requisitos
*   Java 17+
*   Docker & Docker Compose
*   Maven

### Instalação e Execução

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/arthur-goes/resenha-sociocultural-api.git
    cd resenha-sociocultural-api
    ```

2.  **Execute com Docker Compose (Recomendado):**
    O projeto foi desenhado para rodar imediatamente com configurações padrão. Isso iniciará a API e o banco de dados PostgreSQL.
    ```bash
    docker-compose up -d --build
    ```

    > **💡 Configuração Opcional:** A aplicação usa credenciais padrão para uma experiência de desenvolvimento fluida. Se desejar customizar as configurações de banco ou chaves de segurança, você pode criar um arquivo `.env` baseado no `.env.example` antes de rodar o comando acima.

3.  **Acesse a Documentação da API:**
    Uma vez rodando, o Swagger UI estará disponível em:
    *   http://localhost:8080/swagger-ui.html

    **Para autenticar via OAuth2 (Swagger UI):**
    1.  Clique no botão **Authorize**.
    2.  **client_id:** `swagger-ui` (Deixe o client_secret vazio).
    3.  **Scopes:** Marque a caixa `api`.
    4.  Clique em **Authorize**. Você será redirecionado para a página de login.
    5.  **Credenciais:** Use usuário `admin` e senha `admin`.

### Executando Testes
O projeto utiliza **Testcontainers** para subir instâncias efêmeras do PostgreSQL para testes de integração, garantindo paridade de ambiente.

```bash
./mvnw verify
```

### 📝 Licença
Distribuído sob a licença MIT. Veja LICENSE para mais informações.