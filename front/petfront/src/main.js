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
import RegisterClient from './components/screensRegister/RegisterClient.vue'


const routes = [
    //pagina inicial options | login
    {path: '/', component: PrincipalScreen},
    {path: '/Login', component: Login},

    //rotas de agenda
    {path: '/agenda/day', component:Day},
    {path: '/Agenda/Month', component:Month},
    {path: '/Agenda/Week', component:Week},
    
    //Registros de cadastro
    {path: '/Cadastro/Cliente', component:RegisterClient},

    //Footer
    {path: '/Footer', component:footer},

    
]
const router = createRouter({
    history: createWebHistory(),
    routes, 
})

createApp(App).use(router).mount('#app')
