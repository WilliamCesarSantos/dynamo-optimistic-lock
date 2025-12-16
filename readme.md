# Projeto: Inventory Service (Spring Boot + Kotlin/Java + DynamoDB)

Resumo
- Serviço backend para gerenciar inventário usando Spring Boot, Maven e AWS DynamoDB (AWS SDK v2 Enhanced Client).
- Padrão de concorrência: optimistic lock (versionamento em entidades DynamoDB).

<!-- SEO: <meta name="keywords" content="Dynamo, DynamoDB, AWS, optimistic lock"> -->

Tecnologias
- Kotlin / Java
- Spring Boot
- Maven
- AWS SDK v2 (DynamoDbEnhancedClient)
- DynamoDB (local ou AWS)

Requisitos
- JDK 17+
- Maven
- Credenciais AWS configuradas (`AWS_ACCESS_KEY_ID`, `AWS_SECRET_ACCESS_KEY`) ou DynamoDB Local

Configuração AWS / DynamoDB
- Configure as propriedades em `application.yml`/`application.properties` conforme necessário.
- Para testar localmente, use DynamoDB Local e ajuste o endpoint no `DynamoDbClient`/config.

Observações
- Use anotação de atributo de versão na entidade para optimistic locking (por exemplo, um campo `version` incrementado manualmente ou via aplicação).
- Valide exceções de conflito (ConditionalCheckFailedException) e envie respostas HTTP adequadas.
