*Read this in other languages: [Português](#versão-em-português) | [English](#english-version)*

---

## Versão em Português

# API de Gestão de Estoque e Compras (Enterprise Edition)

Uma API RESTful profissional desenvolvida para o controle total de almoxarifados. O sistema gerencia o ciclo de vida de peças e fornecedores, garantindo rastreabilidade absoluta e inteligência na tomada de decisão de compras.

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2+-brightgreen)
![Docker](https://img.shields.io/badge/Docker-Enabled-blue)
![Deploy](https://img.shields.io/badge/Deploy-Render-purple)

### Live Demo
O projeto está em produção. Teste a API através da interface interativa do Swagger:
**[Acessar Swagger no Render](https://estoque-api-p7dw.onrender.com/swagger-ui/index.html#/)**

### Diferenciais de Nível Sênior Implementados
Diferente de um CRUD básico, esta API implementa padrões arquiteturais de mercado:

- **Trilha de Auditoria (Audit Trail):** Histórico completo de movimentações. Cada entrada ou saída é registrada com data, hora, tipo e quantidade, permitindo auditorias precisas.
- **Alerta de Ponto de Pedido (Smart Search):** Algoritmo proativo que filtra peças com estoque abaixo do limite de segurança, otimizando o fluxo de compras.
- **Relatórios de Business Intelligence:** Exportação dinâmica de dados para formato .csv, pronto para análise em Excel ou dashboards corporativos.
- **Segurança e DTOs (Data Transfer Objects):** Uso de Java Records para blindar a camada de persistência, evitando exposição de dados sensíveis do banco.
- **Arquitetura em Camadas (MVC):** Separação clara de responsabilidades entre Controladores, Serviços (Regras de Negócio) e Repositórios.

### Tecnologias
- **Linguagem:** Java 21 (LTS)
- **Framework:** Spring Boot (Data JPA, Web, Validation)
- **Banco de Dados:** SQLite (Relacional)
- **Documentação:** OpenAPI 3 / Swagger
- **Containerização:** Docker (Multi-stage build para performance)

---

## English Version

# Inventory and Purchasing Management API (Enterprise Edition)

A professional RESTful API designed for total warehouse control. The system manages the lifecycle of parts and suppliers, ensuring absolute traceability and intelligence in purchasing decision-making.

### Senior-Level Features Implemented
Unlike a basic CRUD, this API implements industry-standard architectural patterns:

- **Audit Trail:** Complete movement history. Every inbound or outbound transaction is logged with date, time, type, and quantity, enabling precise audits.
- **Reorder Point Alert (Smart Search):** Proactive algorithm that filters parts with stock below safety limits, optimizing the purchasing workflow.
- **Business Intelligence Reports:** Dynamic data export to .csv format, ready for analysis in Excel or corporate dashboards.
- **Security & DTOs (Data Transfer Objects):** Implementation of Java Records to shield the persistence layer, preventing the exposure of sensitive database data.
- **Layered Architecture (MVC):** Clear separation of concerns between Controllers, Services (Business Logic), and Repositories.

### Tech Stack
- **Language:** Java 21 (LTS)
- **Framework:** Spring Boot
- **Database:** SQLite (Relational)
- **Documentation:** OpenAPI 3 / Swagger
- **DevOps:** Docker (Multi-stage build for performance) & Render Cloud Deploy

### Live Demo
Access the production environment and test the endpoints:
**[Test API on Swagger](https://estoque-api-p7dw.onrender.com/swagger-ui/index.html#/)**



