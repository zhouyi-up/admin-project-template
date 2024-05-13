import {defineStore} from "pinia";
import {ref} from "vue";

export const useUiConfigState = defineStore("uiConfig", ()=>{
    return {
        menuCollapse: ref(false),
        menuShowIcon: ref(true),
        tagViewShowIcon: ref(false)
    }
})