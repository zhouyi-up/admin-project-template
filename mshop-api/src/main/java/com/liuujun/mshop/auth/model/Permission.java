package com.liuujun.mshop.auth.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author 该model脚本生成 by jun
 */
@Data
public class Permission {

    /**
     * null
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 页面路径
     */
    private String routePath;
    /**
     * 页面路由标识
     */
    private String routeName;
    /**
     * 页面描述
     */
    private String routeTitle;
    /**
     * 页面组件路径
     */
    private String componentPath;
    /**
     * 父级ID
     */
    private Long parentId;
    /**
     * 排序ID
     */
    private Integer sortId;
    private Integer delFlag;
    private String icon;
    private Integer hide;
}
