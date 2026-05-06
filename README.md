# 🏥 Clínica Agendamento

Projeto de exemplo em **Spring Boot** para gerenciamento de pacientes e agendamentos de consultas médicas.  
Inclui cadastro de pacientes, criação de agendamentos e listagem de dados, utilizando **H2 Database** em memória.

---

## 🚀 Tecnologias utilizadas
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- H2 Database
- Lombok

---

## ⚙️ Como executar o projeto

### Pré-requisitos
- Java 17 instalado
- Maven instalado

### Passos
1. Clone o repositório:
   ```bash
   git clone https://github.com/<seu-usuario>/clinica-agendamento.git
   cd clinica-agendamento
2. Compile e rode o projeto:
mvn spring-boot:run

3. Acesse a aplicação:

API: http://localhost:8080

Console H2: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb

Usuário: sa

Senha: (em branco)

Endpoints disponíveis
Pacientes
POST /pacientes → Criar paciente
{
  "nome": "Théo Dutra",
  "email": "theo@email.com",
  "telefone": "11999999999"
}
GET /pacientes → Listar pacientes
Resposta:
[
  {
    "id": 1,
    "nome": "Théo Dutra",
    "email": "theo@email.com",
    "telefone": "11999999999"
  }
]

Agendamentos
POST /agendamentos → Criar agendamento

{
  "dataHora": "2026-05-10T14:00:00",
  "especialidade": "Cardiologia",
  "pacienteId": 1
}

GET /agendamentos → Listar agendamentos
Resposta:

[
  {
    "id": 1,
    "dataHora": "2026-05-10T14:00:00",
    "especialidade": "Cardiologia",
    "paciente": {
      "id": 1,
      "nome": "Théo Dutra",
      "email": "theo@email.com",
      "telefone": "11999999999"
    }
  }
]

Testes rápidos com Postman
1. Criar paciente → POST /pacientes

2. Criar agendamento → POST /agendamentos (usando o id do paciente criado)

3. Listar pacientes → GET /pacientes

4. Listar agendamentos → GET /agendamentos
