<script setup lang="ts">

import {useRouteState} from "@/stores/routeState";
import MenuItem from "@/layout/menu/MenuItem.vue";
import pinia from "@/stores";
import {useRoute} from "vue-router";
import {useUiConfigState} from "@/stores/uiConfigState";

const routeState = useRouteState(pinia)
const routeList = routeState.getRouteTree()
const uiConfigState = useUiConfigState(pinia)

const route = useRoute()
</script>

<template>
  <el-menu router :default-active="route.name" :collapse="uiConfigState.menuCollapse">
    <menu-item v-for="item in routeList" :data="item" :key="item.id"/>
  </el-menu>
</template>

<style scoped>

</style>