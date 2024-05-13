<script setup lang="ts">

import {useUiConfigState} from "@/stores/uiConfigState";
import pinia from "@/stores";

const props = defineProps(["data"])
const uiConfig = useUiConfigState(pinia)
</script>

<template>
  <el-sub-menu v-if="data.children.length > 0 && data.meta.hide === 0"
               :index="data.name" :key="data.id">
    <template #title>
      <el-icon v-if="uiConfig.menuShowIcon">
        <component :is="data.meta.icon"/>
      </el-icon>
      <span>{{data.meta.name}}</span>
    </template>
    <menu-item v-for="item in data.children" :data="item" :key="item.id"/>
  </el-sub-menu>
  <el-menu-item v-if="data.children.length <= 0 && data.meta.hide === 0"
                :route="data.path" :key="data.id" :index="data.name">
    <el-icon v-if="uiConfig.menuShowIcon">
      <component :is="data.meta.icon"/>
    </el-icon>
    <span>{{data.meta.name}}</span>
  </el-menu-item>
</template>
<style scoped>

</style>