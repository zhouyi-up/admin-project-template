<script setup lang="ts">

import {Close} from "@element-plus/icons-vue";
import {useRouter} from "vue-router";
import {useUiConfigState} from "@/stores/uiConfigState";
import pinia from "@/stores";

const props = defineProps(["label", "route", "keyId", "closed", "isActive", "icon", "query"])
const emits = defineEmits(["close"])

const router = useRouter()
const uiConfig = useUiConfigState(pinia)

const onGoTo = () => {
  router.push({
    path: props.route,
    query: props.query
  })
}
const onClose = () => {
  emits("close")
}
</script>

<template>
  <div :class="isActive? 'tag-view active': 'tag-view no-active'" >
    <div @click="onGoTo" class="label">
      <el-icon v-if="uiConfig.tagViewShowIcon" size="12">
        <component :is="icon"/>
      </el-icon>
      {{label}}
    </div>
    <el-button link @click="onClose" v-if="closed" :class="isActive? 'close-btn':''">
      <el-icon>
        <Close />
      </el-icon>
    </el-button>
  </div>
</template>

<style scoped>
.close-btn:hover{
  color: var(--el-color-white);
}
.active{
  color: var(--el-color-white);
  border: 1px solid var(--el-color-primary);
  background-color: var(--el-color-primary);
}
.no-active{
  border: 1px solid var(--el-border-color);
}
.tag-view{
  font-size: 0.5em;
  cursor: pointer;
  height: 24px;
  line-height: 24px;
  text-align: center;
  min-width: 60px;
  display: inline-block;
  padding-left: 10px;
  padding-right: 5px;
  margin-right: 3px;
  transition: 0.3s;
  .label{
    display: inline;
    padding-right: 10px;
  }
}
.tag-view:hover{
  border: 1px solid var(--el-color-primary);
}
</style>