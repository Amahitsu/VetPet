<template>
    <div class="d-flex justify-content-between">
        <h2>Painel de controle</h2>
    </div>
    <div class="mt-3">
        <div class="row">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-body">
                        <div class="chart-container">
                            <BarChart :data="chartData" :options="chartOptions" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="d-flex flex-column justify-content-between h-100">
                    <div class="card flex-fill mb-4">
                        <div class="card-body d-flex align-items-center">
                            <div class="circle mr-4">
                                <svg xmlns="http://www.w3.org/2000/svg" height="48px" viewBox="0 -960 960 960" width="48px" fill="#444"><path d="M355.84-303.39q-41.46 0-69.98-28.63-28.51-28.63-28.51-70.09 0-41.47 28.63-69.98 28.63-28.52 70.09-28.52 41.46 0 69.98 28.63 28.52 28.63 28.52 70.09 0 41.47-28.63 69.98-28.63 28.52-70.1 28.52ZM184.35-61.04q-33.5 0-58.34-25.24-24.84-25.23-24.84-57.94v-611.87q0-33.73 24.84-58.73 24.84-25.01 58.34-25.01H239v-59.69h78.57v59.69h324.86v-59.69H721v59.69h54.65q33.74 0 58.74 25.01 25 25 25 58.73v611.87q0 32.71-25 57.94-25 25.24-58.74 25.24h-591.3Zm0-83.18h591.3v-429h-591.3v429Z"/></svg>
                            </div>
                            <div>
                                Total de Agendamentos
                                <h1 class="mt-2 mb-0">{{ totalAppointments }}</h1>
                            </div>
                        </div>
                    </div>
                    <div class="card flex-fill mb-4">
                        <div class="card-body d-flex align-items-center">
                            <div class="circle mr-4">
                                <svg xmlns="http://www.w3.org/2000/svg" height="48px" viewBox="0 -960 960 960" width="48px" fill="#444"><path d="M16.35-243.09q0-40.66 19.91-72.81 19.91-32.14 57.22-48.3 74.96-32.41 136.09-47.41 61.14-15 125.17-15 65.5 0 125.97 15 60.46 15 135.46 47.44 37.02 15.26 57.55 47.47 20.54 32.22 20.54 73.53v30q0 34.95-24.39 59.06T610.52-130h-511q-34.39 0-58.78-24.11t-24.39-59.06v-29.92ZM730.17-130q8.7-19.26 16.4-39.8 7.69-20.55 7.69-43.37v-25.17q0-66.01-29-111.73-29-45.71-95.26-77.97 68.96 8.56 132.72 24.13 63.76 15.56 104.37 35.74 35.43 19.87 56.5 52.41 21.06 32.54 21.06 74.76v27.83q0 34.95-24.89 59.06T861.32-130H730.17ZM355.3-490.09q-72.43 0-116.8-44.37-44.37-44.37-44.37-116.3 0-71.94 44.37-116.81 44.37-44.86 116.3-44.86 71.37 0 116.81 44.75 45.43 44.76 45.43 116.42 0 72.43-45.32 116.8-45.32 44.37-116.42 44.37Zm392.96-161.17q0 71.87-45.03 116.52-45.03 44.65-116.11 44.65-13.47 0-27.01-1.71-13.54-1.72-27.11-6.72 25.57-27.85 38.07-66.35 12.5-38.49 12.5-86.1 0-47.03-12.5-84.1Q558.57-772.13 533-804q12.05-3.75 26.76-6.09t27.33-2.34q71.09 0 116.13 45.03 45.04 45.04 45.04 116.14Z"/></svg>
                            </div>
                            <div>
                                Total de Clientes Cadastrados
                                <h1 class="mt-2 mb-0">{{ totalCustomers }}</h1>
                            </div>
                        </div>
                    </div>
                    <div class="card flex-fill">
                        <div class="card-body d-flex align-items-center">
                            <div class="circle mr-4">
                                <svg xmlns="http://www.w3.org/2000/svg" height="48px" viewBox="0 -960 960 960" width="48px" fill="#444"><path d="M157.58-489.17q-40.02 0-67.76-28.02-27.73-28.02-27.73-68.06 0-40.04 27.88-67.57 27.87-27.53 67.72-27.53 40.31 0 67.66 27.46 27.35 27.45 27.35 67.75 0 40.53-27.43 68.25t-67.69 27.72Zm192.26-176.09q-39.81 0-67.17-27.86-27.37-27.86-27.37-67.31 0-39.44 27.52-67.44t67.37-28q-67.37 0-112.75-45.38-45.38-45.38-45.38-112.75 0-67.37 45.38-112.75 45.38-45.38 112.75-45.38 67.37 0 112.75 45.38 45.38 45.38 45.38 112.75 0 67.37-45.38 112.75-28.01 27.88-67.37 28Zm260 0q-39.75 0-67.75-27.88t-28-67.72q0-40.32 27.76-67.66 27.76-27.35 67.97-27.35 39.97 0 67.71 28.04 27.73 28.03 27.73 67.73 0 39.46-27.84 67.15-27.83 27.69-67.58 27.69Zm192.47 176.09q-40.31 0-67.66-27.79-27.35-27.78-27.35-68.01 0-40.71 27.43-68.05 27.43-27.33 67.69-27.33 40.02 0 67.76 27.65 27.73 27.65 27.73 67.69t-27.88 67.94q-27.87 27.9-67.72 27.9ZM261.17-62.5q-33.85 0-58.34-24.79-24.49-24.79-24.49-58.48 0-33.69 24.49-58.57 24.49-24.88 58.34-24.88 33.59 0 58.08 24.88 24.49 24.88 24.49 58.57 0 33.69-24.49 58.48-24.49 24.79-58.08 24.79Zm387.84 0q-33.85 0-58.34-24.79-24.49-24.79-24.49-58.48 0-33.69 24.49-58.57 24.49-24.88 58.34-24.88 33.59 0 58.08 24.88 24.49 24.88 24.49 58.57 0 33.69-24.49 58.48-24.49 24.79-58.08 24.79Z"/></svg>
                            </div>
                            <div>
                                Total de Pets Cadastrados
                                <h1 class="mt-2 mb-0">{{ totalPets }}</h1>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { defineComponent, ref, onMounted } from 'vue';
import { Bar } from 'vue-chartjs';
import axios from 'axios';
import { Chart, registerables } from 'chart.js';

Chart.register(...registerables);

export default {
    components: {
        BarChart: defineComponent({
            // Componente do gráfico
        })
    },
    data() {
        return {
            chartData: {
                labels: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
                datasets: [
                    {
                        label: 'Agendamentos Mensais',
                        backgroundColor: '#4CAF50',
                        data: [40, 20, 30, 10, 50, 25, 45, 30, 20, 35, 40, 55],
                    },
                ],
            },
            chartOptions: {
                responsive: true,
                maintainAspectRatio: false,
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true,
                        },
                    }],
                },
            },
            totalAppointments: 0,
            totalCustomers: 0,
            totalPets: 0
        };
    },
    methods: {
        fetchAppointments() {
            axios.get('http://localhost:8080/api/v1/appointments')
                .then(response => {
                    if (Array.isArray(response.data)) {
                        
                        let labels = [];
                        let data = [];
                        response.data.forEach(appointment => {
                            labels.push(appointment.date); 
                            data.push(appointment.count); 
                        });
                        this.chartData.labels = labels;
                        this.chartData.datasets[0].data = data;
                        this.totalAppointments = response.data.length;
                    } else if (response.data.data && Array.isArray(response.data.data)) {
                       
                        let labels = [];
                        let data = [];
                        response.data.data.forEach(appointment => {
                            labels.push(appointment.date); 
                            data.push(appointment.count); 
                        });
                        this.chartData.labels = labels;
                        this.chartData.datasets[0].data = data;
                        this.totalAppointments = response.data.data.length;
                    } else {
                        console.error('Formato de dados de agendamentos não reconhecido:', response.data);
                    }
                })
                .catch(error => {
                    console.error('Erro ao buscar agendamentos:', error);
                });
        },
        fetchCustomers() {
    axios.get('http://localhost:8080/api/v1/customers')
        .then(response => {
            console.log('Dados de clientes recebidos:', response.data.data);
            if (Array.isArray(response.data.data)) {
                this.totalCustomers = response.data.data.length;
            } else {
                console.error('Resposta inesperada ao buscar clientes:', response.data);
            }
        })
        .catch(error => {
            console.error('Erro ao buscar clientes:', error);
        });
},
fetchPets() {
    axios.get('http://localhost:8080/api/v1/animals')
        .then(response => {
            console.log('Dados de pets recebidos:', response.data.data);
            if (Array.isArray(response.data.data)) {
                this.totalPets = response.data.data.length;
            } else {
                console.error('Resposta inesperada ao buscar pets:', response.data);
            }
        })
        .catch(error => {
            console.error('Erro ao buscar pets:', error);
        });
}

    },
    created() {
        this.fetchAppointments();
        this.fetchCustomers();
        this.fetchPets();
    }
};
</script>


<style scoped>
.chart-container {
    position: relative;
    width: 100%;
    height: 400px;
}

.circle {
    width: 80px;
    height: 80px;
    background-color: #e0e0e0;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 48px;
    color: #555;
}
</style>
