import Vue from 'vue'
import Router from 'vue-router'
import Dashboard from '@/components/Dashboard'
import Clientes from '@/components/Principal/ClientesView'
import Prestamo from '@/components/Principal/PrestamoView'

Vue.use(Router)

export default new Router({
  routes: [

    {
      path: '/',
      name: 'dashboard',
      component: Dashboard,
      children: [
        { path: 'clientes', name: 'clientes', component: Clientes },
        { path: 'prestamos', name: 'prestamos', component: Prestamo }
      ]
    }
  ]
})
