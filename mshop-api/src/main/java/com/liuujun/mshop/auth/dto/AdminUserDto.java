package com.liuujun.mshop.auth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouyi
 */
@Data
public class AdminUserDto {
    /**
     * id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 加密方式0无加密1MD5
     */
    private Integer encryption;
    /**
     * 账号创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 资料更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifyTime;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 权限类型 0普通用户1管理员
     */
    private Integer authType;
    /**
     * 是否黑名单用户,无法登录 0否 1是
     */
    private Integer blackList;
    /**
     * 角色列表
     */
    private List<Integer> roles = new ArrayList<>();
    /**
     * 加密KEY，当密码为MD5时存储盐值
     */
    private String encryptionKey;
    /**
     * 资产管理权限
     */
    private List<Integer> assets = new ArrayList<>();
}
