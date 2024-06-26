import './assets/main.css';

import { createApp } from 'vue';

import App from './App.vue';

import { createRouter, createWebHistory } from 'vue-router';
import RegisterVaccines from '././components/screensRegister/RegisterVaccines.vue';
import Login from './components/Login.vue';
import PrincipalScreen from './components/PrincipalScreen.vue';
import Day from './components/screenAgenda/Day.vue';
import Month from './components/screenAgenda/Month.vue';
import Week from './components/screenAgenda/Week.vue';
import ListClient from './components/screenList/ListClient.vue';
import ListEmployee from './components/screenList/ListEmployee.vue';
import ListMedicines from './components/screenList/ListMedicines.vue';
import ListPetClient from './components/screenList/ListPetClient.vue';
import ListRace from './components/screenList/ListRace.vue';
import ListServiceType from './components/screenList/ListServiceType.vue';
import ListSpecie from './components/screenList/ListSpecie.vue';
import ListVaccines from './components/screenList/ListVaccines.vue';
import PainelDeControle from './components/screenList/PainelDeControle.vue';
import RegisterActivity from './components/screensRegister/RegisterActivity.vue';
import RegisterAgenda from './components/screensRegister/RegisterAgenda.vue';
import RegisterClient from './components/screensRegister/RegisterClient.vue';
import RegisterEmployee from './components/screensRegister/RegisterEmployee.vue';
import RegisterMedicines from './components/screensRegister/RegisterMedicines.vue';
import RegisterPetClient from './components/screensRegister/RegisterPetClient.vue';

import VCalendar from 'v-calendar';
import 'v-calendar/style.css';

import vSelect from "vue-select";

const routes = [
  // Página inicial options | login
  { path: '/', component: Login },
  {
    path: '/principal',
    component: PrincipalScreen,
    meta: { requiresAuth: true },
    children: [
      // Novos cadastros
      { path: '/cliente/cadastro', component: RegisterClient, meta: { requiresAuth: true } },
      { path: '/cliente/:customerId/adicionar-pet', component: RegisterPetClient, meta: { requiresAuth: true } },
      { path: '/atividade/cadastro', component: RegisterActivity, meta: { requiresAuth: true } },
      { path: '/agenda/cadastro', component: RegisterAgenda, meta: { requiresAuth: true } },
      { path: '/funcionario/cadastro', component: RegisterEmployee, meta: { requiresAuth: true } },
      { path: '/medicamento/cadastro', component: RegisterMedicines, meta: { requiresAuth: true } },
      { path: '/vacina/cadastro', component: RegisterVaccines, meta: { requiresAuth: true } },

      // Edições cadastros
      { path: '/cliente/:customerId', component: RegisterClient, meta: { requiresAuth: true } },
      { path: '/funcionario/:workerId', component: RegisterEmployee, meta: { requiresAuth: true } },
      { path: '/agenda/:appointmentId', component: RegisterAgenda, meta: { requiresAuth: true } },
      { path: '/cliente/:animalId/editar-pet', component: RegisterPetClient, meta: { requiresAuth: true } },


      // Listas
      { path: '/clientes', component: ListClient, meta: { requiresAuth: true } },
      { path: '/funcionarios', component: ListEmployee, meta: { requiresAuth: true } },
      { path: '/petsClientes', component: ListPetClient, meta: { requiresAuth: true } },
      { path: '/racas', component: ListRace, meta: { requiresAuth: true } },
      { path: '/servicos', component: ListServiceType, meta: { requiresAuth: true } },
      { path: '/especies', component: ListSpecie, meta: { requiresAuth: true } },
      { path: '/medicamentos', component: ListMedicines, meta: { requiresAuth: true } },
      { path: '/vacinas', component: ListVaccines, meta: { requiresAuth: true } },
      { path: '/painel-de-controle', component: PainelDeControle, meta: { requiresAuth: true } },

      // Rotas de agenda
      { path: '/agenda/day', component: Day, meta: { requiresAuth: true } },
      { path: '/agenda', component: Month, meta: { requiresAuth: true } },
      { path: '/agenda/week', component: Week, meta: { requiresAuth: true } }
    ]
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

// // router.beforeEach((to, from, next) => {
//   const isAuthenticated = localStorage.getItem('user') !== null;
//   if (to.matched.some(record => record.meta.requiresAuth) && !isAuthenticated) {
//     next('/');
//   } else {
//     next();
//   }
// });

const app = createApp(App);

app
.use(router)
.use(VCalendar, {})
.component("v-select", vSelect)
.mount('#app');
