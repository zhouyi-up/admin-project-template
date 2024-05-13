<script setup lang="ts">

import MyPage from "@/components/MyPage.vue";
import {ref} from "vue";

const props = defineProps(["data", "page"])
const emits = defineEmits(["pageChange"])

const pageChange = (currentPage:number, pageSize:number) => {
  emits("pageChange", currentPage, pageSize)
}
</script>

<template>
  <div>
    <el-table border :data="data" style="width: 100%; margin-top: 10px;">
      <slot/>
    </el-table>
    <my-page :page="page" @page-change="pageChange"/>
  </div>
</template>

<style scoped>

</style>