#!/bin/bash

# Comando para criar a tabela no DynamoDB usando o AWS CLI
awslocal dynamodb create-table \
  --table-name inventory \
  --attribute-definitions AttributeName=id,AttributeType=S \
  --key-schema AttributeName=id,KeyType=HASH \
  --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5

echo "Tabela 'inventory' criada com sucesso!"

awslocal dynamodb put-item \
  --table-name inventory \
  --item '{
    "id": {"S": "1"},
    "quantity": {"N": "42"},
    "version": {"N": "1"}
  }'
echo "Item adicionado à tabela 'inventory' com sucesso!"
