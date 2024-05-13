package com.liuujun.mshop.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuujun.mshop.auth.model.AdminUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jun
 */
@Repository
@org.apache.ibatis.annotations.Mapper
public interface AdminUserMapper extends BaseMapper<AdminUser> {
    List<AdminUser> selectAll();
}
