<script setup lang="ts">

import {UseDark} from "@vueuse/components";
</script>

<template>
  <div class="dark-div">
    <UseDark v-slot="{ isDark, toggleDark }">
      <button @click="toggleDark()"
              class="dark-style"
      >
        <el-icon v-if="!isDark"><Icon-Sunny /></el-icon>
        <span v-if="!isDark"></span>
        <span v-if="isDark" ></span>
        <el-icon v-if="isDark"><Icon-Moon /></el-icon>
      </button>
    </UseDark>
  </div>
</template>

<style scoped>
.dark-div{
  width: 40px;
  height: 40px;
  line-height: 40px;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
}
.dark-style{
  border: 1px solid var(--el-border-color);
  background-color: transparent;
  cursor: pointer;
  display: inline-flex;
  justify-content: space-between;
  align-items: center;
  padding: 5px;
  border-radius: 20px;
  width: 50px;
  height: 25px;
}
</style>