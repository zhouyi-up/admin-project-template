<script setup lang="ts">
import MenuExpand from "@/layout/MenuExpand.vue";
import DarkToggle from "@/layout/DarkToggle.vue";
import UserView from "@/layout/UserView.vue";
</script>

<template>
  <div class="base-header">
    <div>
      <menu-expand/>
    </div>
    <div class="header-right">
<!--      <user-view />-->
      <dark-toggle/>
    </div>
  </div>
</template>

<style scoped>
.base-header{
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;

  .header-right{
    display: flex;
  }
}
</style>