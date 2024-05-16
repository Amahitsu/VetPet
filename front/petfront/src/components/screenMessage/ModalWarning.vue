<script setup>
import { ref } from 'vue';
import WarningPng from '../../assets/warning.png';

const props = defineProps({
  modalText: String
});

const modalVisible = ref(true);

function closeModal() {
  modalVisible.value = false;
  window.location.reload();
}
</script>

<template>
  <div class="modal" tabindex="-1" role="dialog" v-if="modalVisible">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title"><img :src="WarningPng"></h5>
        </div>
        <div class="modal-body">
          {{ modalText }}
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" @click="closeModal">Ok</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
img {
  width: 15%;
}
.modal-header,
.modal-body {
  text-align: center;
}
</style>
