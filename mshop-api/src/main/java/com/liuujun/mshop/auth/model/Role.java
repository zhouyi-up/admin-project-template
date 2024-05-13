package com.liuujun.mshop.auth.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.liuujun.mshop.common.MybatisList2JsonTypeHandler;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 该model脚本生成 by jun
 */
@Data
public class Role {

    /**
     * null
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * null
     */
    private String roleName;
    /**
     * null
     */
    @TableField(typeHandler = MybatisList2JsonTypeHandler.class)
    private List<Integer> permissions;
    /**
     * null
     */
    private Integer delFlag;
    /**
     * null
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    private String roleDesc;
}
