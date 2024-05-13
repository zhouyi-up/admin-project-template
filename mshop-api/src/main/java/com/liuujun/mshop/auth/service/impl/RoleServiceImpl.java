package com.liuujun.mshop.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liuujun.mshop.auth.AuthDto;
import com.liuujun.mshop.auth.AuthHelper;
import com.liuujun.mshop.auth.mapper.AdminUserMapper;
import com.liuujun.mshop.auth.mapper.RoleMapper;
import com.liuujun.mshop.auth.model.AdminUser;
import com.liuujun.mshop.auth.model.Role;
import com.liuujun.mshop.auth.service.RoleService;
import com.liuujun.mshop.common.BasePage;
import com.liuujun.mshop.common.BaseResponse;
import com.liuujun.mshop.common.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author zhouyi
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public BaseResponse<BasePage<Role>> list(PageDto pageDto) {

        Page<Role> dataRes = PageHelper.startPage(pageDto.getPage(), pageDto.getPageSize()).doSelectPage(() -> {
            this.roleMapper.selectList(Wrappers.lambdaQuery(Role.class)
                    .eq(Role::getDelFlag, 0));
        });

        BasePage<Role> roleBasePage = BasePage.of(dataRes);
        return BaseResponse.success(roleBasePage);
    }

    @Override
    public BaseResponse<Boolean> save(Role role) {
        Long id = role.getId();
        if (Objects.isNull(id)) {
            this.roleMapper.insert(role);
        }else {
            this.roleMapper.updateById(role);
        }
        return BaseResponse.success();
    }

    @Override
    public BaseResponse<List<Role>> auth() {
        AuthDto auth = AuthHelper.getAuth();
        if (auth.getRole() == 1) {
            List<Role> roles = this.roleMapper.selectList(Wrappers.lambdaQuery(Role.class)
                    .eq(Role::getDelFlag, 0));
            return BaseResponse.success(roles);
        }

        Long userId = auth.getUserId();
        AdminUser adminUser = this.adminUserMapper.selectById(userId);
        if (Objects.isNull(adminUser)) {
            return BaseResponse.success();
        }
        List<Integer> roles = adminUser.getRoles();
        List<Role> adminUsers = this.roleMapper.selectList(Wrappers.lambdaQuery(Role.class)
                .in(Role::getId, roles));
        return BaseResponse.success(adminUsers);
    }
}
