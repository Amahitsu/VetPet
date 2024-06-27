<template>
    <div class="card">
      <div class="card-body">
        <div class="d-flex justify-content-between">
          <h4>Atendimento</h4>
          <div class="d-flex align-items-center">
            <!-- Botão para adicionar serviços (descomente se necessário) -->
            <!-- <button
              type="button"
              class="btn btn-icon btn-primary btn-m me-2"
              @click="openModalService"
            >
              <span class="material-symbols-rounded">add_circle</span>
              <span class="material-symbols-rounded">stethoscope</span>
            </button> -->
  
            <!-- Botão para adicionar vacinas -->
            <button
              type="button"
              class="btn btn-icon btn-primary btn-m me-2"
              @click="openModalVaccine"
            >
              <span class="material-symbols-rounded">add_circle</span>
              <span class="material-symbols-rounded">syringe</span>
            </button>
  
            <!-- Botão para adicionar medicamentos -->
            <button
              type="button"
              class="btn btn-icon btn-primary btn-m"
              @click="openModalMedicine"
            >
              <span class="material-symbols-rounded">add_circle</span>
              <span class="material-symbols-rounded">pill</span>
            </button>
          </div>
        </div>
  
      
        <div>
          <table class="table mt-3" >
            <thead>
              <tr>
                <th>Descrição</th>
                <th>Valor</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="appointment in appointments" :key="appointment.id">
                <td>{{ appointment.id_vaccines }}</td>
                <td class="text-end">
                  <button
                    type="button"
                    class="btn btn-icon btn-sm btn-danger"
                    @click="confirmDelete()"
                  >
                    <span class="material-symbols-rounded">delete</span>
                  </button>
                </td>
              </tr>
              <tr>
                <th>Total</th>
                <th>{{ formatCurrency() }}</th>
              </tr>
            </tbody>
          </table>
          </div>
  
        <!-- Modal de confirmação de exclusão -->
        <div
          class="modal fade"
          id="deleteActivityModal"
          tabindex="-1"
          role="dialog"
          aria-labelledby="deleteActivityModalLabel"
          aria-hidden="true"
        >
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="deleteModalLabel">
                  Confirmar Exclusão
                </h5>
                <button
                  type="button"
                  class="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                  @click="closeDeleteModal"
                ></button>
              </div>
              <div class="modal-body">
                Tem certeza que deseja excluir este atendimento?
              </div>
              <div class="modal-footer">
                <button
                  type="button"
                  class="btn btn-secondary"
                  @click="closeDeleteModal"
                >
                  Cancelar
                </button>
                <button
                  type="button"
                  class="btn btn-danger"
                  @click="deleteActivity"
                >
                  Confirmar
                </button>
              </div>
            </div>
          </div>
        </div>
  
        <!-- Modal para selecionar serviço -->
        <div
          class="modal fade"
          id="modalService"
          tabindex="-1"
          aria-labelledby="modalService"
          aria-hidden="true"
        >
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Selecionar Serviço</h5>
                <button
                  type="button"
                  class="btn-close"
                  aria-label="Close"
                  data-bs-dismiss="modal"
                ></button>
              </div>
              <div class="modal-body">
                <div class="row">
                  <label for="serviceSelect" class="col-sm-2 col-form-label"
                    >Serviço</label
                  >
                  <div class="col-sm-10">
                    <select
                      class="form-select"
                      id="serviceSelect"
                      v-model="selectedServiceId"
                    >
                      <option value="" selected>Selecione</option>
                      <option
                        v-for="service in servicesList"
                        :key="service.id"
                        :value="service.id"
                      >
                        {{ service.name }}
                      </option>
                    </select>
                  </div>
                </div>
              </div>
              <div class="modal-footer">
                <button
                  type="button"
                  class="btn btn-secondary"
                  data-bs-dismiss="modal"
                >
                  Cancelar
                </button>
                <button
                  type="button"
                  class="btn btn-primary me-2"
                  @click="addServiceToAppointment(appointmentId)"
                >
                  Confirmar
                </button>
              </div>
            </div>
          </div>
        </div>
  
        <!-- Modal para selecionar vacina -->
        <div
          class="modal fade"
          id="modalVaccine"
          tabindex="-1"
          aria-labelledby="modalVaccine"
          aria-hidden="true"
        >
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Selecionar Vacina</h5>
                <button
                  type="button"
                  class="btn-close"
                  aria-label="Close"
                  data-bs-dismiss="modal"
                ></button>
              </div>
              <div class="modal-body">
                <div class="row">
                  <label for="vaccineSelect" class="col-sm-2 col-form-label"
                    >Vacina</label
                  >
                  <div class="col-sm-10">
                    <select
                      class="form-select"
                      id="vaccineSelect"
                      v-model="selectedVaccineId"
                    >
                      <option value="" selected>Selecione</option>
                      <option
                        v-for="vaccine in vaccinesList"
                        :key="vaccine.id"
                        :value="vaccine.id"
                      >
                        {{ vaccine.name }}
                      </option>
                    </select>
                  </div>
                </div>
              </div>
              <div class="modal-footer">
                <button
                  type="button"
                  class="btn btn-secondary"
                  data-bs-dismiss="modal"
                >
                  Cancelar
                </button>
                <button
                  type="button"
                  class="btn btn-primary me-2"
                  @click="addVaccineToAppointment(appointmentId)"
                >
                  Confirmar
                </button>
              </div>
            </div>
          </div>
        </div>
  
        <!-- Modal para selecionar medicamento -->
        <div
          class="modal fade"
          id="modalMedicine"
          tabindex="-1"
          aria-labelledby="modalMedicine"
          aria-hidden="true"
        >
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Selecionar Medicamento</h5>
                <button
                  type="button"
                  class="btn-close"
                  aria-label="Close"
                  data-bs-dismiss="modal"
                ></button>
              </div>
              <div class="modal-body">
                <div class="row">
                  <label for="medicineSelect" class="col-sm-3 col-form-label"
                    >Medicamento</label
                  >
                  <div class="col-sm-9">
                    <select
                      class="form-select"
                      id="medicineSelect"
                      v-model="selectedMedicineId"
                    >
                      <option value="" selected>Selecione</option>
                      <option
                        v-for="medicine in medicinesList"
                        :key="medicine.id"
                        :value="medicine.id"
                      >
                        {{ medicine.name }}
                      </option>
                    </select>
                  </div>
                </div>
              </div>
              <div class="modal-footer">
                <button
                  type="button"
                  class="btn btn-secondary"
                  data-bs-dismiss="modal"
                >
                  Cancelar
                </button>
                <button
                  type="button"
                  class="btn btn-primary me-2"
                  @click="addMedicineToAppointment(appointmentId)"
                >
                  Confirmar
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    data() {
      return {
        servicesList: [],
        vaccinesList: [],
        medicinesList: [],
        selectedServiceId: '',
        selectedVaccineId: '',
        selectedMedicineId: '',
      };
    },
    mounted() {
    },
    methods: {
      formatCurrency(value) {
        if (!value) return "";
        return parseFloat(value).toLocaleString("pt-BR", {
          style: "currency",
          currency: "BRL",
        });
      },
      openModalService() {
        $("#modalService").modal("show");
        this.loadServices();
      },
      openModalVaccine() {
        $("#modalVaccine").modal("show");
        this.loadVaccines();
      },
      openModalMedicine() {
        $("#modalMedicine").modal("show");
        this.loadMedicines();
      },
      addVaccineToAppointment() {
        axios.put(`http://localhost:8080/api/v1/appointments/${this.appointmentId}`, {
          id: this.appointmentId,
          id_vaccines: this.selectedVaccineId
        })
        .then(response => {
          console.log("Vacina adicionada com sucesso:", response.data);
          this.loadActivities();
          $("#modalVaccine").modal("hide");
        })
        .catch(error => {
          console.error("Erro ao adicionar vacina:", error);
        });
      },
      addServiceToAppointment() {
        axios.put(`http://localhost:8080/api/v1/appointments/${this.appointmentId}`, {
          id: this.appointmentId,
          id_services: this.selectedServiceId
        })
        .then(response => {
          console.log("Serviço adicionado com sucesso:", response.data);
          this.loadActivities();
          $("#modalService").modal("hide");
        })
        .catch(error => {
          console.error("Erro ao adicionar serviço:", error);
        });
      },
      addMedicineToAppointment() {
        axios.put(`http://localhost:8080/api/v1/appointments/${this.appointmentId}`, {
          id: this.appointmentId,
          id_medicines: this.selectedMedicineId
        })
        .then(response => {
          console.log("Medicamento adicionado com sucesso:", response.data);
          this.loadActivities();
          $("#modalMedicine").modal("hide");
        })
        .catch(error => {
          console.error("Erro ao adicionar medicamento:", error);
        });
      },
      loadServices() {
        axios
          .get("http://localhost:8080/api/v1/services")
          .then(response => {
            this.servicesList = response.data.data;
          })
          .catch(error => {
            console.error("Erro ao listar os serviços:", error);
          });
      },
      loadVaccines() {
        axios
          .get("http://localhost:8080/api/v1/vaccines")
          .then(response => {
            this.vaccinesList = response.data.data;
          })
          .catch(error => {
            console.error("Erro ao listar as vacinas:", error);
          });
      },
      loadMedicines() {
        axios
          .get("http://localhost:8080/api/v1/medicines")
          .then(response => {
            this.medicinesList = response.data.data;
          })
          .catch(error => {
            console.error("Erro ao listar os medicamentos:", error);
          });
      },

      
    },
  };
  </script>
  
  <style>
  /* Adicione qualquer estilo necessário aqui */
  </style>
  