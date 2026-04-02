*Read this in other languages: [Português](#-versão-em-português) | [English](#-english-version)*

---

## 🇧🇷 Versão em Português

#  API de Gestão de Estoque e Compras

Uma API RESTful corporativa desenvolvida para gerenciar estoques, fornecedores e transações de entrada/saída de peças, garantindo a integridade dos dados e prevenindo furos de inventário.

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2+-brightgreen)
![Docker](https://img.shields.io/badge/Docker-Enabled-blue)
![Deploy](https://img.shields.io/badge/Deploy-Render-purple)

###  Live Demo
O projeto está em produção! Você pode testar a API diretamente pelo navegador através da interface interativa do Swagger:
 **[Testar API no Swagger](https://estoque-api-p7dw.onrender.com/swagger-ui/index.html#/)**

###  Regras de Negócio Implementadas
Esta API não é apenas um CRUD. Ela aplica regras rígidas do dia a dia de um almoxarifado:
- **Proteção de Estoque:** É impossível dar saída em uma quantidade maior do que a disponível (Prevenção de estoque negativo).
- **Validação Rigorosa (Fail-Fast):** O sistema barra cadastros de peças sem nome, sem SKU ou com quantidades iniciais negativas (`@Valid`).
- **Tratamento de Erros Global:** Exceções do servidor são capturadas por um `@ControllerAdvice`, devolvendo respostas JSON amigáveis e claras (Status 400) em vez de erros genéricos (Status 500).

###  Tecnologias e Arquitetura
- **Linguagem:** Java 21
- **Framework:** Spring Boot (Web, Data JPA, Validation)
- **Banco de Dados:** SQLite (Ideal para demonstrações rápidas)
- **Documentação:** OpenAPI 3 / Swagger
- **DevOps:** Docker (Multi-stage build) e Deploy na nuvem via Render.
- **Arquitetura:** Padrão em Camadas (Controller, Service, Repository) separando claramente as regras de negócio do roteamento HTTP.


---

## 🇺🇸 English Version

#  Inventory and Purchasing Management API

A corporate RESTful API developed to manage inventory, suppliers, and parts inbound/outbound transactions, ensuring data integrity and preventing inventory discrepancies.

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2+-brightgreen)
![Docker](https://img.shields.io/badge/Docker-Enabled-blue)
![Deploy](https://img.shields.io/badge/Deploy-Render-purple)

### 🌐 Live Demo
The project is in production! You can test the API directly in your browser through the interactive Swagger interface:
👉 **[Test API on Swagger](https://estoque-api-p7dw.onrender.com/swagger-ui/index.html#/)**

### 💼 Implemented Business Rules
This API is not just a simple CRUD. It enforces strict day-to-day warehouse