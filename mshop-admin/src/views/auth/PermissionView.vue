<script setup lang="ts">

import MyRow from "@/components/MyRow.vue";
import {usePermissionApi} from "@/apis/PermissionApi";
import {tree} from "@/utils/TreeUtils";
import {ref} from "vue";
import {ElMessageBox} from "element-plus";


const permissionApi = usePermissionApi()
const views = import.meta.glob("@/views/**/*.vue")
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

let dataList = ref()
let showAdd = ref(false)
let showMove = ref(false)
let permission = ref<any>({})
let componentList = ref(Object.keys(views))
let iconList = ref(Object.keys(ElementPlusIconsVue))
let permissionList = ref<Array<any>>([])
initData()
function initData() {
  permissionApi.list().then((res:any) => {
    permissionList.value = res
    const permissionTree = tree(res)
    dataList.value = permissionTree
  })
}
const onSave = () => {
  permissionApi.save(permission.value).then(() => {
    showAdd.value = false
    initData()
  })
}
const onShowAdd = (row: any) => {
  permission.value = {}
  if (row){
    permission.value.parentId = row.id
    permission.value.routePath = row.routePath
  }
  showAdd.value = true
}
const onEdit = (row:any) => {
  permission.value = row
  showAdd.value = true
}
const onMove = (row:any) => {
  permission.value = row
  showMove.value = true
}
const onDel = (row:any) => {
  ElMessageBox.confirm("确认删除吗？", "提示", {
    confirmButtonText: "删除",
    type: "warning",
    cancelButtonText: "取消",
  }).then(() => {
    permissionApi.del({id: row.id}).then(() => {
      initData()
    })
  })
}
</script>

<template>
  <my-row>
    <el-button type="primary" @click="onShowAdd" size="small" plain>新增权限</el-button>
  </my-row>
  <el-table border :data="dataList" row-key="id" default-expand-all>
<!--    <el-table-column prop="id" label="ID" width="70"></el-table-column>-->
    <el-table-column prop="routeTitle" label="名称" width="200">
      <template #default="scope">
        <div style="display: inline-flex;align-items: center;">
          <el-icon v-if="scope.row.icon" style="margin-right: 5px"><component :is="scope.row.icon"/></el-icon>
          <span>{{scope.row.routeTitle}}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column prop="routeName" label="路由标识" width="180"></el-table-column>
    <el-table-column prop="routePath" label="请求路径" width="220"></el-table-column>
    <el-table-column prop="componentPath" label="页面" min-width="280"></el-table-column>
    <el-table-column label="隐藏" width="70">
      <template #default="scope">
        <el-tag type="primary" v-if="scope.row.hide == 1">是</el-tag>
        <el-tag type="primary" v-else>否</el-tag>
      </template>
    </el-table-column>
    <el-table-column prop="sortId" label="排序" width="70"></el-table-column>
    <el-table-column label="操作" width="260" fixed="right">
      <template #default="scope">
        <el-button type="primary" plain size="small" @click="onShowAdd(scope.row)">新增</el-button>
        <el-button type="primary" plain size="small" @click="onEdit(scope.row)">编辑</el-button>
        <el-button type="primary" plain size="small" @click="onMove(scope.row)">移动</el-button>
        <el-button type="primary" plain size="small" @click="onDel(scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>

  <el-dialog v-model="showAdd" width="800">
    <el-form label-width="70">
      <el-row>
        <el-col :sm="12">
          <el-form-item label="名称">
            <el-input v-model="permission.routeTitle"/>
          </el-form-item>
        </el-col>
        <el-col :sm="12">
          <el-form-item label="标识">
            <el-input v-model="permission.routeName" placeholder="仅支持英文"/>
          </el-form-item>
        </el-col>
        <el-col :sm="6">
          <el-form-item label="图标">
            <el-select v-model="permission.icon" placeholder="选择图标" filterable>
              <el-option v-for="item in iconList" :value="`Icon-${item}`" :label="`Icon-${item}`">
                <el-icon><component :is="`Icon-${item}`"/></el-icon><span style="margin-left: 8px">{{`Icon-${item}`}}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :sm="6">
          <el-form-item label="排序">
            <el-input v-model="permission.sortId" type="number"/>
          </el-form-item>
        </el-col>
        <el-col :sm="12">
          <el-form-item label="路径">
            <el-input v-model="permission.routePath"/>
          </el-form-item>
        </el-col>

        <el-col :sm="20">
          <el-form-item label="页面组件">
            <el-select v-model="permission.componentPath" placeholder="选择页面组件">
              <el-option v-for="item in componentList" :key="item" :label="item" :value="item"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :sm="4">
          <el-form-item label="隐藏">
            <el-switch v-model="permission.hide" :active-value="1" :inactive-value="0"/>
          </el-form-item>
        </el-col>
        <el-col>
          <el-button type="primary" @click="onSave">保存</el-button>
        </el-col>
      </el-row>
    </el-form>
  </el-dialog>
  <el-dialog v-model="showMove" width="20%">
    <el-select style="width: 100%" v-model="permission.parentId">
      <div v-for="item in permissionList">
        <el-option v-if="permission.id != item.id"
                   :label="`${item.routeTitle}(${item.routePath})`" :value="item.id" :key="item.id"/>
      </div>
    </el-select>
    <el-button type="primary" @click="onSave">保存</el-button>
  </el-dialog>
</template>

<style scoped>

</style>