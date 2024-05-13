<script setup lang="ts">

import MyRow from "@/components/MyRow.vue";
import MyList from "@/components/MyList.vue";
import {ref} from "vue";
import {useUserApi} from "@/apis/UserApi";
import {useRoleApi} from "@/apis/RoleApi";

const userApi = useUserApi()
const roleApi = useRoleApi()

let userList = ref([])
let page = ref({
  total: 0,
  page: 1,
  pageSize: 10
})
let showAdd = ref(false)
let user = ref<any>({})
let authRoleList = ref<Array<any>>([])
let assetList = ref<Array<any>>()

roleApi.auth({}).then((res:any) => {
  authRoleList.value = res
})

loadData()
function loadData() {
  userApi.list({
    page: page.value.page,
    pageSize: page.value.pageSize
  }).then((res:any) =>{
    userList.value = res.records
    page.value.total = res.total
  })
}

const pageChange = (pageNo:number, pageSize:number) => {
  page.value.page = pageNo
  page.value.pageSize = pageSize
  loadData()
}
const onEdit = (row:any) => {
  user.value = row
  showAdd.value = true
}
const onDisable = (row:any) => {

}
const onSave = () => {
  userApi.save(user.value).then(() =>{
    showAdd.value = false
  })
}
const onAdd = () => {
  user.value = {}
  showAdd.value = true
}
</script>
<template>
  <div>
    <my-row>
      <el-button type="primary" @click="loadData">搜索</el-button>
    </my-row>
    <my-row>
      <el-button size="small" plain type="primary" @click="onAdd">新增用户</el-button>
    </my-row>
    <my-list :data="userList" :page="page" @page-change="pageChange">
      <el-table-column label="编号" prop="id" width="70"/>
      <el-table-column label="名称" prop="nickName" width="120"/>
      <el-table-column label="账号" prop="username" width="160"/>
      <el-table-column label="手机号" prop="mobile" width="140"/>
      <el-table-column label="邮箱" prop="email" width="240"/>
      <el-table-column label="账号类型" prop="authType" width="100">
        <template #default="scope">
          {{scope.row.authType == 1? "管理员" : "普通用户"}}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" prop="createTime" width="200"/>
      <el-table-column label="操作" fixed="right" width="300">
        <template #default="scope">
          <el-button size="small" plain type="primary" @click="onEdit(scope.row)">编辑</el-button>
          <el-button size="small" plain type="primary" @click="onDisable(scope.row)">禁用</el-button>
        </template>
      </el-table-column>
    </my-list>
    <el-dialog v-model="showAdd" draggable>
      <el-form label-width="70">
        <el-row :gutter="10">
          <el-col :sm="12">
            <el-form-item label="登录名称">
              <el-input disabled v-model="user.username" placeholder="登录名"/>
            </el-form-item>
          </el-col>
          <el-col :sm="12">
            <el-form-item label="用户名称">
              <el-input v-model="user.nickName" placeholder="用户名称"/>
            </el-form-item>
          </el-col>
          <el-col :sm="12">
            <el-form-item label="手机号">
              <el-input v-model="user.mobile" placeholder="手机号"/>
            </el-form-item>
          </el-col>
          <el-col :sm="12">
            <el-form-item label="角色">
              <el-select v-model="user.roles" multiple>
                <el-option v-for="item in authRoleList"
                           :key="item.id"
                           :label="item.roleName" :value="item.id"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :sm="24">
            <el-form-item label="邮箱">
              <el-input v-model="user.email" placeholder="邮箱"/>
            </el-form-item>
          </el-col>
          <el-divider />
          <el-col :sm="24">
            <el-button type="primary" @click="onSave">保存</el-button>
          </el-col>
        </el-row>
      </el-form>
    </el-dialog>
  </div>
</template>
<style>

</style>