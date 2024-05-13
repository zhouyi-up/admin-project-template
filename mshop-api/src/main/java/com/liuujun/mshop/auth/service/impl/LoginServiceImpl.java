package com.liuujun.mshop.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.liuujun.mshop.auth.CodeHelper;
import com.liuujun.mshop.auth.dto.CodeDto;
import com.liuujun.mshop.auth.dto.LoginDto;
import com.liuujun.mshop.auth.dto.LoginResult;
import com.liuujun.mshop.auth.mapper.AdminUserMapper;
import com.liuujun.mshop.auth.model.AdminUser;
import com.liuujun.mshop.auth.service.LoginService;
import com.liuujun.mshop.common.BaseResponse;
import com.liuujun.mshop.common.LocalCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Objects;
import java.util.UUID;

/**
 * @author zhouyi
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AdminUserMapper adminUserMapper;
    @Autowired
    private LocalCache localCache;

    @Override
    public BaseResponse<LoginResult> login(LoginDto loginDto) {
        String key = loginDto.getKey();
        Object object = localCache.get(key);
        if (Objects.isNull(object)) {
            return BaseResponse.error("验证码已过期，请重新获取");
        }
        if (!String.valueOf(object).equalsIgnoreCase(loginDto.getCode())) {
            return BaseResponse.error("验证码错误");
        }
        localCache.delete(key);

        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        AdminUser adminUser = adminUserMapper.selectOne(Wrappers.lambdaQuery(AdminUser.class)
                .eq(AdminUser::getUsername, username));
        if (Objects.isNull(adminUser)) {
            return BaseResponse.error("用户不存在");
        }

        Integer encryption = adminUser.getEncryption();
        if (encryption == 0){
            if (!adminUser.getPassword().equals(password)) {
                return BaseResponse.error("密码错误");
            }
        } else {
            String tempPassword = password + adminUser.getEncryptionKey();
            String passwd = DigestUtils.md5DigestAsHex(tempPassword.getBytes());
            if (!passwd.equals(adminUser.getPassword())){
                return BaseResponse.error("密码错误");
            }
        }

        Integer blackList = adminUser.getBlackList();
        if (blackList == 1){
            return BaseResponse.error("该账号已禁用");
        }

        String token = generateToken(adminUser);
        localCache.put(token, adminUser);

        LoginResult loginResult = new LoginResult();
        loginResult.setUserId(adminUser.getId());
        loginResult.setToken(token);
        loginResult.setLoginTime(System.currentTimeMillis());
        loginResult.setUsername(adminUser.getUsername());

        return BaseResponse.success(loginResult);
    }

    private String generateToken(AdminUser adminUser) {
        String tempString = adminUser.getId() + adminUser.getUsername() + System.currentTimeMillis();
        return DigestUtils.md5DigestAsHex(tempString.getBytes());
    }

    @Override
    public BaseResponse<CodeDto> code() {
        String code = CodeHelper.randomCode();
        String image = CodeHelper.generateImage(code);
        String key = UUID.randomUUID().toString();

        localCache.put(key, code);

        CodeDto codeDto = new CodeDto();
        codeDto.setKey(key);
        codeDto.setImageBase64(image);
        return BaseResponse.success(codeDto);
    }
}
