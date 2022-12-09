import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import store from './components/store/index.js';
Vue.config.productionTip = false
Vue.use(ElementUI);

new Vue({
  store,
  render: h => h(App),
}).$mount('#app')
