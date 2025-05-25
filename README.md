# 🩺 Checkpoint 3 - SOA - Microservices and Web Engineering

Projeto desenvolvido por **Lucas de Alencar** e **Miguel Stein** como checkpoint3,
este sistema tem como objetivo facilitar o agendamento e gerenciamento de consultas entre **pacientes** e **profissionais da saúde**, oferecendo controle completo via API REST.

---

## 🚀 Tecnologias Utilizadas

- **Java 17/18**
- **Spring Boot 3.4.*** (Web, DevTools)
- **Maven**
- **Swagger (springdoc-openapi)** para documentação interativa
- **Lombok** (opcional para produtividade)
- **JPA / Hibernate** para persistência
- **H2 / PostgreSQL** (dependendo da escolha local para persistência)

---

## 🧠 Modelo de Dados (MER)

O sistema é composto por 3 entidades principais:

- **Paciente**
- **Profissional**
- **Consulta**

> Cada consulta vincula um paciente a um profissional, com informações detalhadas como data, status (AGENDADA, REALIZADA, CANCELADA), duração e valor total.

---

## 🌐 Endpoints Disponíveis

### 🧍 Pacientes

| Método | Rota                    | Descrição                         |
|--------|-------------------------|-----------------------------------|
| POST   | `/pacientes`            | Cadastrar novo paciente           |
| GET    | `/pacientes?sort=`      | Listar pacientes ordenados        |
| GET    | `/pacientes/{id}`       | Buscar paciente por ID            |
| PUT    | `/pacientes/{id}`       | Atualizar dados do paciente       |
| DELETE | `/pacientes/{id}`       | Remover paciente                  |

---

### 👨‍⚕️ Profissionais

| Método | Rota                          | Descrição                         |
|--------|-------------------------------|-----------------------------------|
| POST   | `/profissionais`              | Cadastrar novo profissional       |
| GET    | `/profissionais?sort=`        | Listar profissionais ordenados    |
| GET    | `/profissionais/{id}`         | Buscar profissional por ID        |
| PUT    | `/profissionais/{id}`         | Atualizar dados do profissional   |
| DELETE | `/profissionais/{id}`         | Remover profissional              |

---

### 📅 Consultas

| Método | Rota                          | Descrição                         |
|--------|-------------------------------|-----------------------------------|
| POST   | `/consultas`                  | Agendar nova consulta             |
| GET    | `/consultas`                  | Listar todas as consultas         |
| GET    | `/consultas/{id}`             | Detalhar consulta por ID          |
| PUT    | `/consultas/{id}`             | Atualizar consulta                |
| DELETE | `/consultas/{id}`             | Cancelar/Remover consulta         |

---

## 📊 Endpoints Especiais (Consultas Específicas)

- `GET /profissionais/{id}/stats`  
  📌 Exibe estatísticas das consultas realizadas por um profissional.

- `GET /pacientes/{id}/consultas`  
  🔍 Lista as consultas de um paciente filtrando por **status** e **intervalo de datas**.

- `GET /profissionais/{id}/consultas`  
  🔍 Lista as consultas de um profissional com filtros semelhantes.

- `GET /consultas`  
  📆 Lista todas as consultas com filtros por **status** e **data**.

---

## 📑 Documentação da API

Acesse a documentação Swagger em:  
`http://localhost:8080/swagger-ui.html`  
Ou via OpenAPI:  
`http://localhost:8080/v3/api-docs`

---

## ⚙️ Como rodar o projeto

```bash
# Clone o repositório
git clone https://github.com/alencasz/checkpoint3
cd checkpoint3

# Rode com sua IDE favorita (IntelliJ, VSCode, Eclipse) ou via terminal:
./mvnw spring-boot:run

# Ou apenas execute a aplicação (Checkpoint3Application.java) e acesse
H2 Console: http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:checkpoint3db)
