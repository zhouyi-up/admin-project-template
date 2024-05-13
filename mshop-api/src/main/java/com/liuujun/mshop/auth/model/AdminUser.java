package com.liuujun.mshop.auth.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 该model脚本生成 by jun
 */
@Data
public class AdminUser {

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
     * 密码
     */
    private String password;
    /**
     * 加密方式0无加密1MD5
     */
    private Integer encryption;
    /**
     * 账号创建时间
     */
    private LocalDateTime createTime;
    /**
     * 资料更新时间
     */
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
    private List<Integer> roles;
    /**
     * 是否隐藏用户，隐藏用户可登录，在用户列表不可见 0非 1是
     */
    private Integer hideUser;
    /**
     * 加密KEY，当密码为MD5时存储盐值
     */
    private String encryptionKey;
    /**
     * 资产管理权限
     */
    private List<Integer> assets;
}
