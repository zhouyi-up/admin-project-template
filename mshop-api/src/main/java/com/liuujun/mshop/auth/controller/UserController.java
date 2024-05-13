package com.liuujun.mshop.auth.controller;

import com.liuujun.mshop.auth.dto.AdminUserDto;
import com.liuujun.mshop.auth.service.AdminUserService;
import com.liuujun.mshop.common.BasePage;
import com.liuujun.mshop.common.BaseResponse;
import com.liuujun.mshop.common.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("auth/adminUser")
@RestController
public class UserController {

    @Autowired
    private AdminUserService adminUserService;

    /**
     * 管理用户列表
     * @param page
     * @return
     */
    @GetMapping("list")
    public BaseResponse<BasePage<AdminUserDto>> list(PageDto page){
        return this.adminUserService.list(page);
    }


    @PostMapping("save")
    public BaseResponse<Boolean> save(@RequestBody AdminUserDto adminUserDto){
        return this.adminUserService.save(adminUserDto);
    }
}
