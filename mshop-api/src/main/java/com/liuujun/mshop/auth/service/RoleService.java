package com.liuujun.mshop.auth.service;

import com.liuujun.mshop.auth.model.Role;
import com.liuujun.mshop.common.BasePage;
import com.liuujun.mshop.common.BaseResponse;
import com.liuujun.mshop.common.PageDto;

import java.util.List;

/**
 * @author zhouyi
 */
public interface RoleService {
    BaseResponse<BasePage<Role>> list(PageDto pageDto);

    BaseResponse<Boolean> save(Role role);

    BaseResponse<List<Role>> auth();
}
