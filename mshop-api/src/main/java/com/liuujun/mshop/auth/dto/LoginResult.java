package com.liuujun.mshop.auth.dto;

import lombok.Data;

/**
 * @author zhouyi
 */
@Data
public class LoginResult {

    private Long userId;
    private String token;
    private Long loginTime;
    private String username;

}
