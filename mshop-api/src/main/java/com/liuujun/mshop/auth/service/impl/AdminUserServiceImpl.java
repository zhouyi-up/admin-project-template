package com.liuujun.mshop.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liuujun.mshop.auth.AuthProperties;
import com.liuujun.mshop.auth.RandomUtils;
import com.liuujun.mshop.auth.converters.AdminUserConverter;
import com.liuujun.mshop.auth.dto.AdminUserDto;
import com.liuujun.mshop.auth.mapper.AdminUserMapper;
import com.liuujun.mshop.auth.model.AdminUser;
import com.liuujun.mshop.auth.service.AdminUserService;
import com.liuujun.mshop.common.BasePage;
import com.liuujun.mshop.common.BaseResponse;
import com.liuujun.mshop.common.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;
    @Autowired
    private AuthProperties authProperties;
    @Autowired
    private AdminUserConverter adminUserConverter;

    @Override
    public BaseResponse<BasePage<AdminUserDto>> list(PageDto page) {

        Page<AdminUser> pageResult = PageHelper.startPage(page.getPage(), page.getPageSize())
                .doSelectPage(() -> {
                    this.adminUserMapper.selectList(Wrappers.lambdaQuery(AdminUser.class)
                            .eq(AdminUser::getHideUser, 0)
                    );
                });

        BasePage<AdminUserDto> adminUserBasePage = BasePage.of(pageResult, this.adminUserConverter::toDto);
        return BaseResponse.success(adminUserBasePage);
    }

    @Override
    public BaseResponse<Boolean> save(AdminUserDto adminUserDto) {
        if (Objects.isNull(adminUserDto.getId())) {
            AdminUser adminUser = new AdminUser();
            adminUser.setUsername(adminUserDto.getUsername());
            adminUser.setMobile(adminUserDto.getMobile());
            adminUser.setEmail(adminUserDto.getEmail());

            Integer passwdEncryption = this.authProperties.getPasswdEncryption();
            String defaultPasswd = this.authProperties.getDefaultPasswd();
            Integer passwdGenerate = this.authProperties.getPasswdGenerate();
            if (passwdGenerate == 1){
                defaultPasswd = RandomUtils.string(6);
            }
            if (passwdEncryption == 0){
                adminUser.setPassword(defaultPasswd);
            }else {
                String string = RandomUtils.string(6);
                adminUser.setEncryptionKey(string);
                String passwd = DigestUtils.md5DigestAsHex((defaultPasswd+string).getBytes(StandardCharsets.UTF_8));
                adminUser.setPassword(passwd);
            }
            adminUser.setEncryption(passwdEncryption);
            adminUser.setModifyTime(LocalDateTime.now());
            adminUser.setNickName(adminUserDto.getNickName());
            adminUser.setAuthType(adminUserDto.getAuthType());
            adminUser.setBlackList(0);
            adminUser.setRoles(adminUserDto.getRoles());
            adminUser.setHideUser(0);
            this.adminUserMapper.insert(adminUser);
        }else {
            AdminUser adminUser = new AdminUser();
            adminUser.setMobile(adminUserDto.getMobile());
            adminUser.setEmail(adminUserDto.getEmail());
            adminUser.setModifyTime(LocalDateTime.now());
            adminUser.setNickName(adminUserDto.getNickName());
            adminUser.setAuthType(adminUserDto.getAuthType());
            adminUser.setRoles(adminUserDto.getRoles());
            adminUser.setId(adminUserDto.getId());
            adminUser.setAssets(adminUserDto.getAssets());
            this.adminUserMapper.updateById(adminUser);
        }

        return BaseResponse.success();
    }
}
