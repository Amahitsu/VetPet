<script setup>
import { ref, onMounted, defineEmits, defineProps } from "vue";

const props = defineProps({
  tableStructure: {
    type: Array,
    required: true,
  },
  idField: {
    type: String,
    required: true,
  },
  title: {
    type: String,
    required: true,
  },
  routerLinkAdd: {
    type: String,
    required: false,
  },
  rows: {
    type: Array,
    required: true,
  },
});

// Linhas da tabela, inicialmente vazia
const rows = ref(props.rows);

// Define os eventos que este componente pode emitir
const emit = defineEmits(["onEdit", "onDelete", "onAdd"]);

// Funções para emitir eventos
const emitirOnEdit = (id) => {
  emit("onEdit", id);
};

const emitirOnDelete = (id) => {
  emit("onDelete", id);
};

const emitirOnAdd = () => {
  emit("onAdd");
};

onMounted(() => {
  console.log("Valores recebidos na tabela", props);
});
</script>

<template>
  <div>
    <h1>{{ props.title }}</h1>

    <router-link :to="props.routerLinkAdd" v-if="props.routerLinkAdd">
      <button class="btn btn-success m-3" @click="emitirOnAdd">
        Adicionar
      </button>
    </router-link>

    <button class="btn btn-success m-3" @click="emitirOnAdd" v-else>
      Adicionar
    </button>

    <table class="table table-dark table-striped table-hover">
      <thead>
        <tr>
          <th v-for="(col, index) in tableStructure" :key="index">
            {{ col.label }}
          </th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(r, index) in rows" :key="index">
          <td v-for="(col, index) in tableStructure" :key="index">
            {{ r[col.field] }}
          </td>

          <td>
            <!-- Ações, como botões de editar/excluir -->
            <button
              class="btn btn-primary mx-2"
              @click="emitirOnEdit(r[props.idField])"
            >
              Editar
            </button>
            <button
              class="btn btn-danger mx-2"
              @click="emitirOnDelete(r[props.idField])"
            >
              Excluir
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>