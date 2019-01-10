<template lang='pug'>
  v-form(:value="validForm", ref="form", lazy-validation, style="width: 100%")
    v-snackbar(style="z-index:99999", :timeout='notificacion.timeoutsnackbar', :top="notificacion.snackbary === 'top'", :bottom="notificacion.snackbary === 'bottom'", :right="notificacion.snackbarx === 'right'", :left="notificacion.snackbarx === 'left'", :multi-line="notificacion.mode === 'multi-line'", :vertical="notificacion.modesnackbar === 'vertical'", :color="notificacion.colorsnackbar", v-model='notificacion.snackbar')
      | {{ notificacion.textsnackbar }}
      v-btn(dark='', flat='', @click.native='notificacion.snackbar = false') Cerrar
    spinner(v-if='loading')
    v-dialog(v-model='dialogEliminar', persistent='', max-width='290')
      v-card
        v-card-title.headline Eliminar prestamos
        v-card-text
          | ¿Está seguro de eliminar el prestamos?
        v-card-actions
          v-spacer
          v-btn(color='green darken-1', flat='', @click.native='cancelarEliminar') Cancelar
          v-btn(color='green darken-1', flat='', @click.native='aceptarEliminar') Aceptar
    v-breadcrumbs(divider='/')
      v-breadcrumbs-item(v-for='item in itemsBreadcrumbs', :key='item.text', :disabled='item.disabled')
        | {{ item.text }}
    v-card(style='margin: 10px; margin-bottom: 20px;')
      div
        v-alert(v-model='alert', dismissible='', type='info')
          | Click sobre cada prestamos para visualizar el resto de acciones.
      v-card-title
        v-spacer
        v-tooltip(bottom='', style='width: 50%;')
          v-text-field(slot='activator', v-model='search', append-icon='search', label='Buscar', single-line='', hide-details='')
          span BÚSQUEDA POR CUALQUIER CAMPO
      v-data-table(:headers='headers', :items='prestamos', :search='search', item-key='id', rowsPerPageText='Filas por página: ', noDataText='No tiene prestamos registrados')
        template(slot='items', slot-scope='props')
          tr(@click="props.expanded = !props.expanded")
            td.text-xs-left {{ props.item.id }}
            td.text-xs-left {{ props.item.cedula }}
            td.text-xs-left {{ props.item.nombre }}
        template(slot="expand", slot-scope="props")
          v-layout
            v-tooltip(top='')
              v-btn(flat, icon, slot='activator', @click='editarprestamos(props.item)')
                v-icon mode_edit
              span EDITAR
            v-tooltip(top='')
              v-btn(flat, icon, @click='eliminarprestamos(props.item.id)', slot='activator')
                v-icon delete
              span ELIMINAR
            v-tooltip(top='')
              v-btn(flat, icon, slot='activator', to='/prestamos')
                v-icon attach_money
              span PRESTAMOS
        v-alert(slot='no-results', :value='true', color='error', icon='warning')
          | Su búsqueda para "{{ search }}" no tiene resultados.
        v-flex(xs12='', sm10='', offset-sm1='')
    v-card(style='margin: 10px; background-color: #1976d2;')
      v-expansion-panel(v-model='panel', expand='')
        v-expansion-panel-content(v-for='(item,i) in items', :key='i')
          div(slot='header')
            v-icon(large='', style='margin-right: 20px;') person_add
            strong AGREGAR MODIFICAR prestamoS
          v-layout(row='', wrap='')
            v-flex(xs12='', sm6='', md4='', style='padding: 10px;')
              v-text-field(:mask='masksIdentificacion', v-model = 'form.numeroidentificacion', required = true, label='Identificación', append-icon='account_box', :rules="[rules.required]")
            v-flex(xs12='', sm6='', md4='', style='padding: 10px;')
              v-text-field(v-model = 'form.nombrecompleto', counter=50, required = true, label='Nombre', append-icon='question_answer', :rules="[rules.required, rules.counterNombre]", @keyup='form.nombrecompleto = form.nombrecompleto.toUpperCase()')
            v-flex(md12, sm12, xs12, text-xs-center,style='padding-bottom: 20px;')
              v-tooltip(top)
                v-btn(color='primary', slot='activator', @click='agregarprestamos') {{agregareditartxt}}
</template>

<script>
  import Spinner from '@/Spinner.vue'

  let functions = require('../../Funciones/funciones.js')
  export default {
    components: {
      'spinner': Spinner
    },
    data () {
      return {
        agregareditartxt: 'AGREGAR prestamos',
        editaragregar: true,
        ideditar: '',
        idEliminar: '',
        itemEditar: {},
        dialogEliminar: false,
        validForm: true,
        loading: false,
        alert: true,
        masks: {
          cedulaMask: '##########',
          rucMask: '#############',
          phoneMask: '(##) #-###-###',
          cellMask: '##########'
        },
        masksIdentificacion: '',
        rules: {
          required: value => !!value || 'Campo Requerido.',
          counter: value => value.length <= 20 || 'Max 20 characters',
          email: value => {
            const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
            return pattern.test(value) || 'Email Incorrecto.'
          },
          counterNombre: (v) => v.length <= 50 || 'No puede ingresar mas de 50 caracteres.',
          counterDireccion: (v) => v.length <= 100 || 'No puede ingresar mas de 100 caracteres.'
        },
        tiposIdentificacion: [],
        paises: [],
        provincias: [],
        ciudades: [],
        form: {
          numeroidentificacion: '',
          nombrecompleto: ''
        },
        itemsBreadcrumbs: [
          {
            text: 'Dashboard',
            disabled: false
          },
          {
            text: 'prestamos',
            disabled: false
          }
        ],
        search: '',
        headers: [
          {text: 'Id', align: 'left', value: 'id'},
          {text: 'Identificación', align: 'left', value: 'cedula'},
          {text: 'Nombre', align: 'left', value: 'nombre'}
        ],
        prestamos: [],
        panel: [],
        items: 1,
        notificacion: {
          snackbar: false,
          snackbary: 'top',
          snackbarx: 'right',
          modesnackbar: '',
          timeoutsnackbar: 6000,
          textsnackbar: 'Proceso realizado de manera correcta',
          colorsnackbar: 'success'
        }
      }
    },
    methods: {
      editarprestamo () {
        const data = {
          id: this.ideditar,
          cedula: this.form.numeroidentificacion,
          nombre: this.form.nombrecompleto
        }
        this.$axios.put('/prestamos/' + this.ideditar, data).then(response => {
          console.log(response)
          if (response.status.toString() === '200') {
            this.obtenerprestamos()
            this.notificacionOpen('Operación realizada de manera correcta.')
            this.agregareditartxt = 'AGREGAR prestamos'
            this.editaragregar = true
          } else {
            this.notificacionOpen('Error al realizar la operación.', 'error')
          }
          this.loading = false
        }).catch(() => {
          this.notificacionOpen('Error al realizar la operación.', 'error')
          this.loading = false
        })
      },
      agregarprestamos () {
        if (this.$refs.form.validate()) {
          if (this.editaragregar) {
            if (functions.validarCedula(this.form.numeroidentificacion)) {
              this.loading = true
              const data = {
                cedula: this.form.numeroidentificacion,
                nombre: this.form.nombrecompleto
              }
              this.$axios.post('/prestamos', data).then(response => {
                console.log(response)
                if (response.status.toString() === '200') {
                  this.obtenerprestamos()
                  this.notificacionOpen('Operación realizada de manera correcta.')
                } else {
                  this.notificacionOpen('Error al realizar la operación.', 'error')
                }
                this.loading = false
              }).catch(() => {
                this.notificacionOpen('Error al realizar la operación.', 'error')
                this.loading = false
              })
            } else {
              this.notificacionOpen('El número de identificación ingresado es incorrecto.', 'error')
            }
          } else {
            this.editarprestamo()
          }
        }
      },
      eliminarprestamos (id) {
        this.idEliminar = id
        this.dialogEliminar = true
      },
      aceptarEliminar () {
        this.dialogEliminar = false
        this.loading = true
        const data = {
          id: this.ideditar,
          cedula: this.form.numeroidentificacion,
          nombre: this.form.nombrecompleto
        }
        this.$axios.delete('/prestamos/' + this.idEliminar, data).then(response => {
          if (response.status.toString() === '200') {
            this.obtenerprestamos()
            this.notificacionOpen('prestamos eliminado de manera correcta.')
          } else {
            this.notificacionOpen('prestamos duplicado.', 'error')
          }
          this.loading = false
        }).catch(() => {
          this.notificacionOpen('Error al eliminar prestamos', 'error')
          this.loading = false
        })
        this.idEliminar = ''
      },
      cancelarEliminar () {
        this.idEliminar = ''
        this.dialogEliminar = false
      },
      editarprestamos (item) {
        console.log(item)
        this.editaragregar = false
        this.ideditar = item.id
        this.agregareditartxt = 'EDITAR prestamo'
        this.notificacionOpen('Los datos aparecerán dentro de la sección AGREGAR MODIFICAR prestamos.', 'info', 6000, 'right', 'bottom')
        this.form.numeroidentificacion = item.cedula
        this.form.nombrecompleto = item.nombre
      },
      notificacionOpen (txt = 'Proceso realizado de manera correcta', tipo = 'success', timeoutsnackbar = 6000, snackbarx = 'right', snackbary = 'top') {
        this.notificacion.snackbar = true
        this.notificacion.textsnackbar = txt
        this.notificacion.colorsnackbar = tipo
        this.notificacion.snackbary = snackbary
      },
      obtenerprestamos () {
        this.$axios.get(`/prestamos`).then(response => {
          console.log(response)
          this.prestamos = response.data
        }).catch(e => {
          this.notificacionOpen('Error al cargar los tipos de identificación.', 'error')
        })
      }
    },
    created: function () {
      this.editaragregar = true
      this.loading = true
      this.alert = true
      this.masksIdentificacion = this.masks.cedulaMask
      this.$axios.get(`/prestamos`).then(response => {
        console.log(response['data'])
        this.prestamos = response.data
        this.loading = false
      }).catch(e => {
        this.notificacionOpen('Error al cargar los tipos de identificación.', 'error')
        this.loading = false
      })
    }
  }
</script>
