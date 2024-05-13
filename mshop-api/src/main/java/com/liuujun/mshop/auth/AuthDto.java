package com.liuujun.mshop.auth;

import lombok.Data;

/**
 * @author zhouyi
 */
@Data
public class AuthDto {

    private Long userId;
    private String token;
    /**
     * 0 普通 1 管理员
     */
    private Integer role = 0;


}
