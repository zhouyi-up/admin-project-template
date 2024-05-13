package com.liuujun.mshop.auth.service;

import com.liuujun.mshop.auth.dto.AdminUserDto;
import com.liuujun.mshop.common.BasePage;
import com.liuujun.mshop.common.BaseResponse;
import com.liuujun.mshop.common.PageDto;

public interface AdminUserService {

    BaseResponse<BasePage<AdminUserDto>> list(PageDto page);

    BaseResponse<Boolean> save(AdminUserDto adminUserDto);
}
