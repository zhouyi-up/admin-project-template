package com.liuujun.mshop.auth;

import com.liuujun.mshop.common.BaseException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

/**
 * @author zhouyi
 */
public class AuthHelper {

    public static AuthDto getAuth(){
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        Object loginInfo = requestAttributes.getAttribute("loginInfo", RequestAttributes.SCOPE_REQUEST);
        if (Objects.isNull(loginInfo)) {
            throw BaseException.exception(403, "用户未登录");
        }
        return (AuthDto) loginInfo;
    }

    public static void setAuth(AuthDto authDto){
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        requestAttributes.setAttribute("loginInfo", authDto, RequestAttributes.SCOPE_REQUEST);
    }
}
