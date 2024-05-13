<script setup lang="ts">

import MyRow from "@/components/MyRow.vue";
import MyList from "@/components/MyList.vue";
import {ref} from "vue";
import {useRoleApi} from "@/apis/RoleApi";
import {usePermissionApi} from "@/apis/PermissionApi";
import {tree} from "@/utils/TreeUtils";

const roleApi = useRoleApi()
const permissionApi = usePermissionApi()

let roleList = ref<Array<any>>([])
let page = ref({
  page: 1,
  pageSize: 10,
  total:0
})
let role = ref<any>({})
let showAdd = ref(false)
let permissionAuth = ref<Array<any>>([])

loadData()
function loadData(){
  roleApi.list({
    page: page.value.page,
    pageSize: page.value.pageSize
  }).then((res:any) => {
    roleList.value = res.records
  })
}

const onAdd = () => {
  permissionApi.auth().then((res:any) =>{
    const root = tree(res)
    permissionAuth.value = root
    showAdd.value = true
    role.value = {}
  })
}
const onCheckPermission = (node:any, status:any) => {
  const checkPermission = [...status.checkedKeys]
  role.value.permissions = checkPermission
}
const onSave = () => {
  roleApi.save(role.value).then(() => {
    showAdd.value = false
    loadData()
  })
}
const onEditRole = (row: any) =>{
  permissionApi.auth().then((res:any) =>{
    const root = tree(res)
    permissionAuth.value = root
    role.value = row
    showAdd.value = true
  })

}
</script>

<template>
  <my-row>
    <el-button type="primary" @click="loadData">搜索</el-button>
  </my-row>
  <my-row>
    <el-button size="small" plain type="primary" @click="onAdd">新增角色</el-button>
  </my-row>
  <my-list :data="roleList" :page="page">
    <el-table-column prop="id" label="编号" width="70" />
    <el-table-column prop="roleName" label="角色名称" />
    <el-table-column label="操作" width="180">
      <template #default="scope">
        <el-button type="primary" plain size="small" @click="onEditRole(scope.row)">编辑</el-button>
      </template>
    </el-table-column>
  </my-list>
  <el-dialog v-model="showAdd">
    <el-form>
      <el-row>
        <el-col :sm="12">
          <el-form-item label="角色名称">
            <el-input v-model="role.roleName" placeholder="角色名称"/>
          </el-form-item>
        </el-col>
        <el-col :sm="24">
          <el-form-item label="角色备注">
            <el-input type="textarea" v-model="role.roleDesc" placeholder="备注"/>
          </el-form-item>
        </el-col>
        <el-col :sm="24">
          <el-form-item label="角色权限">
            <el-tree show-checkbox style="width: 100%;"
                     :data="permissionAuth" node-key="id"
                     :props="{label: 'routeTitle'}" default-expand-all
                     :default-checked-keys="role.permissions"
                      @check="onCheckPermission"
            />
          </el-form-item>
        </el-col>
        <el-col :sm="24">
          <el-button type="primary" @click="onSave">保存</el-button>
        </el-col>
      </el-row>
    </el-form>
  </el-dialog>
</template>

<style scoped>

</style>