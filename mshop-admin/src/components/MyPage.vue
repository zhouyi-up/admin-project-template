<script setup lang="ts">

import {ref} from "vue";

const props = defineProps(["page"])
const emits = defineEmits(["pageChange"])

let data = ref()
data.value = props.page

const pageChange = (currentPage:number, pageSize:number) => {
  // emits("pageChange", currentPage, pageSize)
}
const currentChange = (page:number) => {
  emits("pageChange", page, props.page.pageSize)
}
const pageSizeChange = (pageSize:number) => {
  emits("pageChange", props.page.page, pageSize)
}
</script>

<template>
  <div class="page">
    <el-pagination
        layout="prev, sizes, pager, next"
        :total="data.total" :page-size="data.pageSize"
        :current-page="data.page" @change="pageChange" @current-change="currentChange" @update:page-size="pageSizeChange"/>
  </div>
</template>

<style scoped>
.page{
  display: flex;
  align-content: center;
  justify-content: center;
  padding: 10px 0;
}
</style>