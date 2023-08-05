
import { createApp } from 'vue'
import App from './App.vue'

// 全局引入element-plus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// 引入element-plus图标
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

const app = createApp(App)
// element-plus图标配置
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
// element-plus配置
app.use(ElementPlus, { size: 'small', zIndex: 3000 })
app.mount('#app')
