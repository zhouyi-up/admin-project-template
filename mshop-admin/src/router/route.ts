import router from "@/router/index";
import {usePermissionApi} from "@/apis/PermissionApi";
import {tree} from "@/utils/TreeUtils";
import {useRouteState} from "@/stores/routeState";
import pinia from "@/stores";

const views = import.meta.glob("@/views/**/*.vue")
const permissionApi = usePermissionApi()
const routeState = useRouteState(pinia)

const default_route = [
    {
        path: '/',
        name: 'Layout',
        component: () => import('../layout/BaseLoyout.vue'),
        children: [
            {
                path: '/permissionAdmin',
                name: 'permissionAdmin',
                component: () => import("../views/auth/PermissionView.vue"),
                meta: {
                    name: "权限资源管理"
                }
            }
        ],
        meta: {
            name: "首页"
        }
    },{
        path: '/login',
        name: 'login',
        component: () => import('../views/auth/LoginView.vue'),
        meta: {
            name: "登录"
        }
    }
]
const initRoute = async () => {
    const permissions:Array<any> = await permissionApi.auth() as Array<any>;
    //预处理信息
    const routeItems:Array<any> = []
    permissions.forEach((item:any) =>{
        const component = views[item.componentPath]
        //取组件信息
        const routeItem = {
            id: item.id,
            parentId: item.parentId,
            path: item.routePath,
            name: item.routeName,
            component: component,
            meta:{
                name: item.routeTitle,
                icon: item.icon,
                hide: item.hide
            }
        }

        routeItems.push(routeItem)
    })
    const routeRoot = tree(routeItems)
    routeState.setRouteTree(routeRoot)
    route(routeRoot)
}
const route = (routes:Array<any>) =>{
    default_route[0].children = routes
    default_route.forEach((item:any) => {
        router.addRoute(item)
    })
}

export {default_route, initRoute}