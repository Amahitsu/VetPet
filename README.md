# VetPet

## Descrição

VetPet é um projeto para gerenciamento de clínicas veterinárias.

## Instalação e Uso
Este projeto depende do banco de dados MySql, que pode ser facilmente executado a partir do Docker.

1. Utilize o `docker-compose up` para subir as dependências do projeto
2. Crie após subir as dependências é necessário criar um banco de dados (schema) chamado `petvet`
3. Execute o projeto a partir do arquivo `PetsApplcation.java`, as tabelas serão automaticamente criadas

Caso não deseje utilizar o MySql no Docker, será necessário ajustar o arquivo `applicaiton.properties`.