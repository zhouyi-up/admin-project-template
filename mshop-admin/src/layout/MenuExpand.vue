<script setup lang="ts">

import {useUiConfigState} from "@/stores/uiConfigState";
import pinia from "@/stores";

const uiConfigState = useUiConfigState(pinia)
</script>

<template>
  <div class="expand">
    <el-button link @click="uiConfigState.menuCollapse = !uiConfigState.menuCollapse">
      <el-icon :size="18" v-if="!uiConfigState.menuCollapse"><Icon-Fold /></el-icon>
      <el-icon :size="18" v-else><Icon-Expand /></el-icon>
    </el-button>
  </div>
</template>

<style scoped>
.expand{
  width: 40px;
  height: 40px;
  line-height: 40px;
  display: inline;
}
</style>