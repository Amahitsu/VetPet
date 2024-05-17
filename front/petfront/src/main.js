import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'

import { createRouter, createWebHistory } from 'vue-router'
import Footer from './components/footer/Footer.vue'
import Login from './components/Login.vue'
import PrincipalScreen from './components/PrincipalScreen.vue'
import Day from './components/screenAgenda/Day.vue'
import Month from './components/screenAgenda/Month.vue'
import Week from './components/screenAgenda/Week.vue'
import RegisterActivity from './components/screensRegister/RegisterActivity.vue'
import RegisterAgenda from './components/screensRegister/RegisterAgenda.vue'
import RegisterClient from './components/screensRegister/RegisterClient.vue'
import RegisterEmployee from './components/screensRegister/RegisterEmployee.vue'
import RegisterPetClient from './components/screensRegister/RegisterPetClient.vue'
import RegisterRace from './components/screensRegister/RegisterRace.vue'
import RegisterService from './components/screensRegister/RegisterService.vue'
import RegisterSpecie from './components/screensRegister/RegisterSpecie.vue'
import ListSpecie from './components/screenList/ListSpecie.vue'
import ListClient from './components/screenList/ListClient.vue'
import ListService from './components/screenList/ListService.vue'
import ListEmployee from './components/screenList/ListEmployee.vue'
import ListPetClient from './components/screenList/ListPetClient.vue'
import ListRace from './components/screenList/ListRace.vue'


const routes = [
    //pagina inicial options | login
    { path: '/', component: Login },
    { path: '/principal', component: PrincipalScreen },

    //rotas de agenda
    { path: '/agenda/day', component: Day },
    { path: '/agenda/month', component: Month },
    { path: '/agenda/week', component: Week },

    //Registros de cadastro
    { path: '/cadastro/cliente', component: RegisterClient },
    { path: '/cadastro/atividade', component: RegisterActivity },
    { path: '/cadastro/agenda', component: RegisterAgenda },
    { path: '/cadastro/funcionario', component: RegisterEmployee },
    { path: '/cadastro/petcliente', component: RegisterPetClient },
    { path: '/cadastro/raça', component: RegisterRace },
    { path: '/cadastro/servico', component: RegisterService },
    { path: '/cadastro/especie', component: RegisterSpecie},

    //Footer
    { path: '/footer', component: Footer },

    //Lista de cadastros
    { path: '/list/cliente', component: ListClient },
    { path: '/list/funcionario', component: ListEmployee },
    { path: '/list/petclient', component: ListPetClient },
    { path: '/list/petclient', component: ListRace },
    { path: '/list/servico', component: ListService },
    { path: '/list/specie', component: ListSpecie}
]
const router = createRouter({
    history: createWebHistory(),
    routes,
})

createApp(App).use(router).mount('#app')
