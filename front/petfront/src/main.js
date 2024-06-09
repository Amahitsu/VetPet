import './assets/main.css';

import { createApp } from 'vue';
import App from './App.vue';

import { createRouter, createWebHistory } from 'vue-router';
import Login from './components/Login.vue';
import PrincipalScreen from './components/PrincipalScreen.vue';
import Day from './components/screenAgenda/Day.vue';
import Month from './components/screenAgenda/Month.vue';
import Week from './components/screenAgenda/Week.vue';
import ListClient from './components/screenList/ListClient.vue';
import ListEmployee from './components/screenList/ListEmployee.vue';
import ListPetClient from './components/screenList/ListPetClient.vue';
import ListRace from './components/screenList/ListRace.vue';
import ListServiceType from './components/screenList/ListServiceType.vue';
import ListSpecie from './components/screenList/ListSpecie.vue';
import RegisterActivity from './components/screensRegister/RegisterActivity.vue';
import RegisterAgenda from './components/screensRegister/RegisterAgenda.vue';
import RegisterClient from './components/screensRegister/RegisterClient.vue';
import RegisterEmployee from './components/screensRegister/RegisterEmployee.vue';
import RegisterPetClient from './components/screensRegister/RegisterPetClient.vue';
import RegisterService from './components/screensRegister/RegisterService.vue';

import VCalendar from 'v-calendar';
import 'v-calendar/style.css';

const routes = [
  // Página inicial options | login
  { path: '/', component: Login },
  {
    path: '/principal',
    component: PrincipalScreen,
    children: [
      // Novos cadastros
      { path: '/cliente/cadastro', component: RegisterClient },
      { path: '/cliente/:customerId/adicionar-pet', component: RegisterPetClient },
      { path: '/atividade/cadastro', component: RegisterActivity },
      { path: '/agenda/cadastro', component: RegisterAgenda },
      { path: '/petCliente/cadastro', component: RegisterPetClient },
      { path: '/atendimento/cadastro', component: RegisterService },
      { path: '/funcionario/cadastro', component: RegisterEmployee },

      // Edições cadastros
      { path: '/cliente/:customerId', component: RegisterClient, props: true },

      // Listas
      { path: '/clientes', component: ListClient },
      { path: '/funcionarios', component: ListEmployee },
      { path: '/petsClientes', component: ListPetClient },
      { path: '/racas', component: ListRace },
      { path: '/servicos', component: ListServiceType },
      { path: '/especies', component: ListSpecie },

      // Rotas de agenda
      { path: '/agenda/day', component: Day },
      { path: '/agenda', component: Month },
      { path: '/agenda/week', component: Week }
    ]
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

const app = createApp(App);
app.use(router).use(VCalendar, {}).mount('#app');
