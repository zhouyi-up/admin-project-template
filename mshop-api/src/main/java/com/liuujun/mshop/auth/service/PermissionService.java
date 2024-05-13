package com.liuujun.mshop.auth.service;

import com.liuujun.mshop.auth.model.Permission;
import com.liuujun.mshop.common.BaseResponse;

import java.util.List;

/**
 * @author zhouyi
 */
public interface PermissionService {
    BaseResponse<List<Permission>> list();

    BaseResponse<Boolean> save(Permission permission);

    BaseResponse<Boolean> del(Long id);

    BaseResponse<List<Permission>> auth();
}
