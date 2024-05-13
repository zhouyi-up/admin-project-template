package com.liuujun.mshop.auth.controller;

import com.liuujun.mshop.auth.model.Permission;
import com.liuujun.mshop.auth.service.PermissionService;
import com.liuujun.mshop.common.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhouyi
 */
@RequestMapping("permission")
@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("auth")
    public BaseResponse<List<Permission>> auth(){
        return this.permissionService.auth();
    }

    @GetMapping("list")
    public BaseResponse<List<Permission>> list() {
        return this.permissionService.list();
    }

    @PostMapping("save")
    public BaseResponse<Boolean> save(@RequestBody Permission permission) {
        return this.permissionService.save(permission);
    }

    @GetMapping("del")
    public BaseResponse<Boolean> del(Long id){
        return this.permissionService.del(id);
    }
}
