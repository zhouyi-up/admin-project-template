<script setup lang="ts">
const model = defineModel()
</script>

<template>
  <img :src="'data:image/png;base64,'+model" alt="验证码"/>
</template>

<style scoped>
img{
  height: 100%;
}
</style>