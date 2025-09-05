# Sistema de Agendamento de Transferências Financeiras

Este projeto implementa um sistema de agendamento de transferências financeiras, conforme especificado no documento de requisitos. A aplicação é desenvolvida em Java com Spring Boot para o backend e utiliza um banco de dados H2 em memória para persistência de dados.

## Decisões Arquiteturais

- **Backend**: Spring Boot 2.7.5
- **Linguagem**: Java 11
- **Persistência**: H2 Database (em memória)
- **Construção**: Maven
- **Testes**: JUnit 5 e Mockito

Optou-se por uma arquitetura RESTful para a API, separando claramente as responsabilidades entre Model, Repository, Service e Controller. O banco de dados H2 em memória foi escolhido para simplificar a execução e atender ao requisito de persistência em memória.

## Ferramentas Utilizadas

- **JDK 11**: Ambiente de desenvolvimento Java.
- **Apache Maven**: Ferramenta de automação de construção de projetos.
- **Spring Tool Suite (STS) / IntelliJ IDEA / VS Code**: IDEs recomendadas para desenvolvimento.

## Instruções para a Subida do Projeto

Para executar o projeto, siga os passos abaixo:

1.  **Pré-requisitos**:
    - Certifique-se de ter o JDK 11 ou superior instalado em sua máquina.
    - Certifique-se de ter o Apache Maven instalado.

2.  **Clonar o Repositório**:
    ```bash
    git clone <URL_DO_SEU_REPOSITORIO> # Substitua pela URL do seu repositório GitHub
    cd financial-transfer-scheduler
    ```

3.  **Compilar e Executar**:
    Navegue até o diretório raiz do projeto (`financial-transfer-scheduler`) e execute o seguinte comando Maven:
    ```bash
    mvn spring-boot:run
    ```

    A aplicação será iniciada na porta padrão 8080. Você pode acessar o console do H2 em `http://localhost:8080/h2-console` (com `jdbc:h2:mem:testdb` como URL JDBC, `sa` como usuário e senha vazia).

## Endpoints da API

-   **POST /transferencias**
    -   **Descrição**: Agenda uma nova transferência financeira.
    -   **Corpo da Requisição (JSON)**:
        ```json
        {
            "contaOrigem": "XXXXXXXXXX",
            "contaDestino": "YYYYYYYYYY",
            "valorTransferencia": 1000.00,
            "dataTransferencia": "2025-09-10" # Formato YYYY-MM-DD
        }
        ```
    -   **Resposta (JSON)**: A transferência agendada com a taxa calculada.

-   **GET /transferencias**
    -   **Descrição**: Lista todas as transferências financeiras agendadas.
    -   **Resposta (JSON)**: Uma lista de transferências.

## Regras de Taxa

A taxa é calculada com base na data da transferência em relação à data de agendamento (hoje):

| Dias para Transferência | Taxa Fixa (R$) | Percentual (%) |
| :---------------------- | :------------- | :------------- |
| 0                       | 3.00           | 2.5%           |
| 1 a 10                  | 12.00          | 0.0%           |
| 11 a 20                 | 0.00           | 8.2%           |
| 21 a 30                 | 0.00           | 6.9%           |
| 31 a 40                 | 0.00           | 4.7%           |
| 41 a 50                 | 0.00           | 1.7%           |

**Observação**: Caso não haja taxa aplicável (mais de 50 dias), um alerta será lançado e a transferência não será permitida.

