package com.liuujun.mshop.auth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhouyi
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "auth")
public class AuthProperties {

    private String defaultPasswd = "123456";
    private Integer passwdEncryption = 1;
    /**
     * 密码生成方式 0 默认密码 1随机生成
     */
    private Integer passwdGenerate = 0;
}
