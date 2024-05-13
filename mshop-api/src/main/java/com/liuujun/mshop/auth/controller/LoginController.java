package com.liuujun.mshop.auth.controller;

import com.liuujun.mshop.auth.dto.CodeDto;
import com.liuujun.mshop.auth.dto.LoginDto;
import com.liuujun.mshop.auth.dto.LoginResult;
import com.liuujun.mshop.auth.service.LoginService;
import com.liuujun.mshop.common.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhouyi
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     * @param loginDto
     * @return
     */
    @PostMapping("")
    public BaseResponse<LoginResult> login(@RequestBody LoginDto loginDto){
        return this.loginService.login(loginDto);
    }

    @GetMapping("code")
    public BaseResponse<CodeDto> code(){
        return this.loginService.code();
    }
}
