import './theme/index.sass'

import { createApp } from 'vue'
import pinia from "@/stores";
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import 'element-plus/theme-chalk/dark/css-vars.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'

import App from './App.vue'
import router from './router'

const app = createApp(App)

app.use(pinia)
app.use(router)
app.use(ElementPlus, {
    locale: zhCn
})

for (const [key, component] of Object.entries(ElementPlusIconsVue)){
    app.component("Icon-" + key, component)
}

app.mount('#app')