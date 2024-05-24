<script setup>
import { ref } from "vue";
import { DadosModel } from "../dados/dados.model";
import Dados from "../dados/dados.vue";

const colunas = [
  new DadosModel("Id", "id"),
  new DadosModel("Nome", "nome"),
  new DadosModel("Telefone", "telefone"),
  new DadosModel("Status", "status"),
];

const rows = ref([
  {
    id: 1,
    nome: "Vitor",
    telefone: "47 991457854",
    status: "Garanhão",
  },
]);

// Funções para tratar os eventos emitidos pelo Componente Dados
const tratarEventoEditar = (id) => {
  console.log("Editar cliente com ID:", id);
};

const tratarEventoExcluir = (id) => {
  console.log("Excluir cliente com ID:", id);
};

const tratarEventoAdicionar = () => {
  console.log("Adicionar novo cliente");
};
</script>

<template>
  <Dados
    :title="'Funcionários'"
    :tableStructure="colunas"
    :routerLinkAdd="'/funcionario/cadastro'"
    :rows="rows"
    :idField="'id'"
    @onEdit="tratarEventoEditar"
    @onDelete="tratarEventoExcluir"
    @onAdd="tratarEventoAdicionar"
  ></Dados>
</template>