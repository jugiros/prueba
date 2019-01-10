<template lang='pug'>
  div
    v-navigation-drawer(fixed='', :mini-variant='miniVariant', :clipped='clipped', v-model='drawer', app='', style='background-color: black; opacity: 0.9;')
      v-layout(align-center='', justify-space-around='', wrap='')
        v-avatar.avatar
          img(src='https://cdn.vuetifyjs.com/images/john.jpg', alt='John', style='margin-top: 20px;')
      v-list
        v-list-tile(router='', :to='item.to', :key='i', v-for='(item, i) in items', exact='')
          v-tooltip(right='')
            v-list-tile-action(slot='activator')
              v-icon(v-html='item.icon')
            span {{item.title}}
          v-list-tile-content
            v-list-tile-title(v-text='item.title')
    v-toolbar(fixed='', app='', :clipped-left='clipped', style="height: 60px")
      v-toolbar-side-icon(@click.native.stop='drawer = !drawer')
      v-btn(icon='', @click.native.stop='miniVariant = !miniVariant')
        v-icon(v-html="miniVariant ? 'chevron_right' : 'chevron_left'")
      v-btn(icon='', @click.native.stop='clipped = !clipped')
        v-icon web
      v-btn(icon='', @click.native.stop='fixed = !fixed')
        v-icon remove
      v-toolbar-title(v-text='title')
      v-spacer
      v-btn(icon='', @click.native.stop='rightDrawer = !rightDrawer')
        v-icon more_horiz
      v-btn(icon='', @click.native='exit')
        v-icon close
    v-content(style='padding-top: 0px;')
      v-container(fluid='', fill-height='', style='padding: 0px;')
        v-slide-y-transition(mode='out-in')
          router-view
    v-navigation-drawer(temporary='', fixed='', :right='right', v-model='rightDrawer', app='', style='background-color: black; opacity: 0.9;')
      v-list
        v-list-tile
          v-list-tile-action
            v-icon(light='') compare_arrows
          v-list-tile-title Switch drawer (click me)
</template>

<script>
export default {
  name: 'app_istitucional',
  data: () => ({
    clipped: true,
    drawer: true,
    fixed: false,
    items: [
      {icon: 'group', title: 'Clientes', to: '/clientes'},
      {icon: 'attach_money', title: 'Prestamos', to: '/prestamos'}
    ],
    miniVariant: true,
    right: true,
    rightDrawer: false,
    title: 'PRUEBA'
  }),
  methods: {
    exit () {
      this.$router.push('/')
      location.reload()
    }
  },
  mounted: function () {
    // this.$router.replace({name: 'usuarios'})
  }
}
</script>

<style>
  .avatar {
    height: 70% !important;
    width: 70% !important;
  }
</style>
