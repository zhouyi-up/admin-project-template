package com.liuujun.mshop.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.liuujun.mshop.auth.AuthDto;
import com.liuujun.mshop.auth.AuthHelper;
import com.liuujun.mshop.auth.mapper.PermissionMapper;
import com.liuujun.mshop.auth.model.Permission;
import com.liuujun.mshop.auth.service.PermissionService;
import com.liuujun.mshop.common.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author zhouyi
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public BaseResponse<List<Permission>> list() {
        List<Permission> permissions = this.permissionMapper.selectList(Wrappers.lambdaQuery(Permission.class)
                .eq(Permission::getDelFlag, 0).orderByAsc(Permission::getSortId));
        return BaseResponse.success(permissions);
    }

    @Override
    public BaseResponse<Boolean> save(Permission permission) {
        Long id = permission.getId();
        if (Objects.isNull(id)){
            this.permissionMapper.insert(permission);
            return BaseResponse.success();
        }

        this.permissionMapper.updateById(permission);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse<Boolean> del(Long id) {
        if (Objects.isNull(id)) {
            return BaseResponse.error("ID为空");
        }

        Permission permission = new Permission();
        permission.setId(id);
        permission.setDelFlag(1);
        this.permissionMapper.updateById(permission);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse<List<Permission>> auth() {
        AuthDto auth = AuthHelper.getAuth();
        Integer role = auth.getRole();

        List<Permission> ps = Lists.newArrayList();
        if (role == 1){
            List<Permission> data = list().getData();
            ps.addAll(data);
        }
        return BaseResponse.success(ps);
    }
}
