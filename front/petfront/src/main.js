import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'

import { createRouter, createWebHistory} from 'vue-router'
import Login from './components/Login.vue'
import PrincipalScreen from './components/PrincipalScreen.vue'

const routes = [
    {path: '/', component: PrincipalScreen},
    {path: '/Login', component: Login},
]
const router = createRouter({
    history: createWebHistory(),
    routes, 
})

createApp(App).use(router).mount('#app')
