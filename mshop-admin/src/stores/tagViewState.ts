import {defineStore} from "pinia";
import {ref} from "vue";

export const useTagViewState = defineStore("tagView", () => {
    const tagViewList = ref<Array<any>>([])
    const showClose = ref(false)

    const addTagView = (tagView:any) => {
        tagViewList.value.push(tagView)
        if (tagViewList.value.length > 1){
            showClose.value = true
        }
    }
    const getTagViews = () => {
        return tagViewList.value
    }
    const delTagView = (key:string) => {
        const index = tagViewList.value.findIndex(item => item.key === key)
        if (index != -1){
            tagViewList.value.splice(index, 1)
            if (tagViewList.value.length <= 1){
                showClose.value = false
            }
        }
        return index
    }
    return { tagViewList, addTagView, getTagViews, delTagView, showClose}
})