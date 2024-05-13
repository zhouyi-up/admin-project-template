<script setup lang="ts">

import {RouterView} from "vue-router";
import TagViewList from "@/layout/TagViewList.vue";
import AmHeader from "@/layout/AmHeader.vue";
import MenuList from "@/layout/menu/MenuList.vue";
</script>

<template>
  <el-container>
    <el-aside style="width: unset">
      <menu-list style="height: 100vh;"/>
    </el-aside>
    <el-container>
      <el-header class="header">
        <am-header class="am-header"/>
        <tag-view-list class="tag-views" />
      </el-header>
      <el-main class="main">
        <router-view v-slot="{ Component }">
          <transition name="nested">
            <el-card shadow="hover">
              <component :is="Component" />
            </el-card>

          </transition>
        </router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<style scoped>
.header{
  --header-height: 50px;
  padding: 0;
  height: calc( var(--header-height) + 36px );
  display: flex;
  flex-direction: column;
}
.am-header{
  flex: 1;
  border-bottom: 1px solid var(--el-border-color);
}
.tag-views{
  height: 26px;
  padding: 5px;
}
.main{
  border-top: 1px solid var(--el-border-color);
  padding: 10px;
}


.nested-enter-active .inner,
.nested-leave-active .inner {
  transition: all 0.3s ease-in-out;
}

.nested-enter-from .inner,
.nested-leave-to .inner {
  transform: translateX(30px);
  opacity: 0;
}
</style>