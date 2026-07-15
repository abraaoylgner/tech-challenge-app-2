# 🍽️ Tech Challenge - Fase 02: Sistema de Gestão de Restaurantes Compartilhado

## 📖 Descrição do Projeto

Este projeto nasceu da iniciativa de um grupo de restaurantes locais que, motivados pelo alto custo dos sistemas de gestão individuais, uniram forças para construir uma plataforma única e compartilhada. O objetivo principal é democratizar a gestão e focar no que realmente importa: a qualidade da comida oferecida aos clientes.

Nesta segunda fase de implementação, o sistema foi expandido para um modelo robusto de retaguarda (back-end), permitindo o gerenciamento eficiente das operações essenciais. As funcionalidades entregues visam estruturar a base de usuários e estabelecimentos de forma escalável e controlada.

## 🏗️ Arquitetura

O projeto foi inteiramente desenhado sob os princípios da **Clean Architecture**. Essa abordagem arquitetural foi escolhida para garantir a máxima separação de responsabilidades, testabilidade e escalabilidade do sistema a longo prazo.

A estrutura está dividida de forma estrita nas seguintes camadas:

* **Domain (Core):** Contém as entidades de negócio puras (Usuário, Tipo de Usuário, Restaurante, Item de Cardápio) e as interfaces de portas (Ports) de entrada e saída. Não possui dependência de nenhum framework externo.
* **Application (Use Cases):** Orquestra o fluxo de dados entre o domínio e as portas, contendo as regras de negócio específicas da aplicação, como validações e associações.
* **Infrastructure / Adapters:** A camada mais externa, responsável por implementar as portas de saída (ex: repositórios JPA acessando o PostgreSQL) e expor as portas de entrada via Controladores REST (Spring MVC). É aqui que residem as configurações de Segurança (Spring Security + JWT) e a documentação interativa (Swagger/OpenAPI).

## 🚀 Funcionalidades da Fase 02

Esta fase atende especificamente aos domínios exigidos no escopo:

1.
**Gestão de Tipos de Usuário:** Estrutura para distinguir entre "Dono de Restaurante" e "Cliente".


* Implementação de CRUD para criação dos tipos de perfis contendo o Nome do Tipo.


* Mecanismo estabelecido para associar corretamente os usuários cadastrados aos seus respectivos perfis.




2.
**Cadastro de Restaurantes:** CRUD completo para gerenciar os estabelecimentos.


* Campos gerenciados: Nome, Endereço, Tipo de cozinha, Horário de funcionamento e Dono do Restaurante (obrigatoriamente associado a um usuário existente).




3.
**Gestão do Cardápio:** CRUD para os itens que serão comercializados nos restaurantes.


* Campos gerenciados: Nome, Descrição, Preço, Flag de disponibilidade para consumo exclusivo no local e Caminho de armazenamento da foto do prato.





## 🛠️ Tecnologias e Práticas Utilizadas

*
**Linguagem & Framework:** Java 21, Spring Boot (Web, Data JPA, Security, Validation) utilizando o padrão Clean Architecture.


*
**Banco de Dados:** PostgreSQL estruturado via Docker.


* **Autenticação:** JWT (JSON Web Tokens) de forma stateless.
* **Mapeamento de Objetos:** MapStruct para conversão eficiente entre DTOs e entidades de Domínio.
*
**Qualidade & Testes:** JUnit e Mockito focados na cobertura exigida (Testes unitários e de integração).


* **Documentação da API:** SpringDoc OpenAPI (Swagger UI).

## 🔌 Documentação dos Endpoints (API)

A aplicação expõe recursos RESTful. A documentação interativa completa (Swagger) contendo os esquemas de requisição e resposta pode ser acessada, com a aplicação rodando, em:
👉 `http://localhost:8082/swagger-ui.html`

### Resumo das Rotas Disponíveis:

* **Autenticação (`/v1/auth`)**
* `POST /v1/auth/login`: Autentica o usuário e retorna o token JWT.


*
**Tipos de Usuário (`/v1/tipos-usuario`)**


* `POST` / `GET`: Cria e lista perfis base (Cliente, Dono). Rota pública.


* **Usuários (`/v1/usuarios`)**
* `POST`: Cria um novo usuário associado a um tipo.
* `GET` / `PUT /{id}` / `PATCH /{id}/senha` / `DELETE /{id}`: Gestão da conta de usuário.


*
**Restaurantes (`/v1/restaurantes`)**


*
`POST` / `GET` / `GET /{id}` / `PUT /{id}` / `DELETE /{id}`: Gerenciamento completo de estabelecimentos, vinculados ao Dono.




*
**Itens de Cardápio (`/v1/itens-cardapio`)**


* `POST`: Adiciona pratos a um restaurante.
* `GET /restaurante/{id}`: Lista o menu completo de um estabelecimento.
*
`GET /{id}` / `PUT /{id}` / `DELETE /{id}`: Gestão individual dos pratos.





## ⚙️ Instruções de Configuração e Execução

O projeto utiliza um `docker-compose.yml` pré-configurado para subir toda a infraestrutura necessária (Aplicação Java + Banco de Dados PostgreSQL) de forma transparente.

### Pré-requisitos

* Docker e Docker Compose (ou Docker Desktop) devidamente instalados e rodando.
* Portas `8082` e `5432` livres na sua máquina.

### Passo a Passo

1. **Clone o repositório:**
```bash
git clone https://github.com/abraaoylgner/tech-challenge-app-2
cd tech-challenge-app-2

```


2. **Suba os contêineres:**
   Execute o comando abaixo na raiz do projeto (onde está o `docker-compose.yml`):


```bash
docker-compose up -d

```


Isso fará o build da imagem da aplicação Java e fará o deploy junto com o banco de dados.


3. **Acesse a aplicação:**
   A API estará disponível em `http://localhost:8082`.



```

## 📦 Collections do Postman

Para facilitar a avaliação técnica e os testes manuais, uma Collection do Postman está disponibilizada na raiz deste repositório, contendo as chamadas prontas para os fluxos de criação. Procure pelo arquivo `postman_collection.json` na pasta `/docs`.