<script setup lang="ts">

import TagView from "@/layout/TagView.vue";
import {useTagViewState} from "@/stores/tagViewState";
import pinia from "@/stores";
import {onBeforeRouteUpdate, useRoute, useRouter} from "vue-router";
import {ref} from "vue";

const tagViewState = useTagViewState(pinia)
const route = useRoute()
const router = useRouter()
setTagView(route)
function setTagView(route:any){
  let name = route.meta.name
  let key = route.name
  const tagName = route.query.tagName
  if (tagName){
    name = tagName
    key = `${route.name}-${tagName}`
  }
  const isExist = tagViewState.getTagViews().some(item => item.key === key)
  if (!isExist){
    tagViewState.addTagView({
      name: name,
      path: route.path,
      key: key,
      icon: route.meta.icon,
      query: route.query
    })
  }
}
onBeforeRouteUpdate((to) => {
  setTagView(to)
})

const onClose = (item:any) => {
  const index = tagViewState.delTagView(item.key)
  if (item.key == route.name){
    const beforeTagView = tagViewState.getTagViews()[index - 1]
    router.push({
      path: beforeTagView.path,
      query: beforeTagView.query
    })
  }
}
</script>

<template>
  <div>
    <tag-view :label="item.name" :key="item.key"
              :key-id="item.key" :route="item.path"
              :closed="tagViewState.showClose"
              :is-active="(route.query.tagName?`${route.name}-${route.query.tagName}`:route.name) == item.key"
              :icon="item.icon" :query="item.query"
              v-for="item in tagViewState.tagViewList" @close="onClose(item)" />
  </div>
</template>

<style scoped>

</style>