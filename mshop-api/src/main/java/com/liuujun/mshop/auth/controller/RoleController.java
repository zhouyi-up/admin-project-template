package com.liuujun.mshop.auth.controller;

import com.liuujun.mshop.auth.model.Role;
import com.liuujun.mshop.auth.service.RoleService;
import com.liuujun.mshop.common.BasePage;
import com.liuujun.mshop.common.BaseResponse;
import com.liuujun.mshop.common.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhouyi
 */
@RequestMapping("role")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("list")
    public BaseResponse<BasePage<Role>> list(PageDto pageDto){
        return this.roleService.list(pageDto);
    }

    @PostMapping("save")
    public BaseResponse<Boolean> save(@RequestBody Role role){
        return this.roleService.save(role);
    }

    @GetMapping("auth")
    public BaseResponse<List<Role>> auth(){
        return this.roleService.auth();
    }
}
