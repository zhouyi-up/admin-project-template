package com.liuujun.mshop.auth.service;

import com.liuujun.mshop.auth.dto.CodeDto;
import com.liuujun.mshop.auth.dto.LoginDto;
import com.liuujun.mshop.auth.dto.LoginResult;
import com.liuujun.mshop.common.BaseResponse;

/**
 * @author zhouyi
 */
public interface LoginService {

    BaseResponse<LoginResult> login(LoginDto loginDto);

    BaseResponse<CodeDto> code();
}
