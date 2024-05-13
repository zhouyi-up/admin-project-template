package com.liuujun.mshop.auth.dto;

import lombok.Data;

/**
 * @author zhouyi
 */
@Data
public class LoginDto {
    /**
     * 登录账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String code;
    private String key;
}
