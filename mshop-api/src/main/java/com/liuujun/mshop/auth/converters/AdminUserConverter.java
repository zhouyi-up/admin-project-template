package com.liuujun.mshop.auth.converters;

import com.liuujun.mshop.auth.dto.AdminUserDto;
import com.liuujun.mshop.auth.model.AdminUser;
import org.mapstruct.Mapper;

/**
 * @author zhouyi
 */
@Mapper(componentModel = "spring")
public interface AdminUserConverter {

    AdminUser to(AdminUserDto adminUserDto) ;

    AdminUserDto toDto(AdminUser adminUser);
}
