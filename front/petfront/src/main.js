import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'

import { createRouter, createWebHistory } from 'vue-router'
import Login from './components/Login.vue'
import PrincipalScreen from './components/PrincipalScreen.vue'
import footer from './components/footer/footer.vue'
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
import ListClient from './components/screenList/ListClient.vue'
import ListPet from './components/screenList/ListPet.vue'



const routes = [
    //pagina inicial options | login
    {path: '/', component: Login},
    {path: '/principal', component: PrincipalScreen},

    //rotas de agenda
    {path: '/agenda/day', component:Day},
    {path: '/agenda/month', component:Month},
    {path: '/agenda/week', component:Week},
    
    //Registros de cadastro
    {path: '/cadastro/cliente', component:RegisterClient},
    {path: '/cadastro/atividade', component:RegisterActivity},
    {path: '/cadastro/agenda', component:RegisterAgenda},
    {path: '/cadastro/funcionario', component:RegisterEmployee},
    {path: '/cadastro/petcliente', component:RegisterPetClient},
    {path: '/cadastro/raça', component:RegisterRace},
    {path: '/cadastro/serviço', component:RegisterService},

    //Footer
    {path: '/footer', component:footer}
    
    //Visualizar Listas Existentes
    {path: '/list/cliente', component:ListClient},
    {path: '/list/pet', component:ListPet},
    
]
const router = createRouter({
    history: createWebHistory(),
    routes, 
})

createApp(App).use(router).mount('#app')
