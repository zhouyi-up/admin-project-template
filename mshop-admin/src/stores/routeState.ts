import {defineStore} from "pinia";
import {ref} from "vue";

export const useRouteState = defineStore("routeList", () =>{
    const routeTree = ref<Array<any>>([])

    const setRouteTree = (data:any) => {
        routeTree.value = data
    }
    const getRouteTree = () => {
        return routeTree
    }
    return {setRouteTree, getRouteTree}
})