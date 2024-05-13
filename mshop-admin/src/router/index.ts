import { createRouter, createWebHashHistory } from 'vue-router'
import {default_route, initRoute} from "@/router/route";
import {useRouteState} from "@/stores/routeState";
import pinia from "@/stores";

const routeState = useRouteState(pinia)

const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL),
  routes: [...default_route]
})
router.beforeEach(  (to, from, next) => {
  const token = localStorage.getItem(import.meta.env.VITE_TOKEN_KEY)
  if (to.name != "login" && !token){
    next({name: "login"})
  } else {
    if (to.name == "login" && !token){
      next()
    }else {
      const routeTree = routeState.getRouteTree()
      if (routeTree.value.length <= 0){
          initRoute().then(() =>{
          router.replace({path: to.path, query: to.query})
        })
      }else {
        next()
      }
    }
    
  }

})
export default router
