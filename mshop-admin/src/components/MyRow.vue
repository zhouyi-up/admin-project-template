<template>
    <div class="my-row">
        <slot/>
    </div>
</template>
<style>
.my-row{
  border: 1px solid var(--el-border-color);
  border-radius: 3px;
  padding: 10px;
  margin-bottom: 10px;
}
</style>