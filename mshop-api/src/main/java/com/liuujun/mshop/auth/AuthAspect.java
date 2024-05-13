package com.liuujun.mshop.auth;

import com.google.common.collect.Lists;
import com.liuujun.mshop.auth.model.AdminUser;
import com.liuujun.mshop.common.BaseResponse;
import com.liuujun.mshop.common.LocalCache;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Objects;

/**
 * @author zhouyi
 */
@Aspect
@Component
public class AuthAspect {

    private static final List<String> ignore_url = Lists.newArrayList("/login/code", "/login");

    @Autowired
    private LocalCache localCache;

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void pointcut(){};

    @Around("pointcut()")
    public Object invoke(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) requestAttributes;

        HttpServletRequest request = sra.getRequest();
        String contextPath = request.getServletPath();
        if (ignore_url.contains(contextPath)){
            return pjp.proceed();
        }
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return BaseResponse.error(403, "用户未登录");
        }
        Object object = localCache.get(token);
        if (Objects.isNull(object)) {
            return BaseResponse.error(403,  "登录过期");
        }
        AdminUser user = (AdminUser) object;
        localCache.put(token, user);

        AuthDto authDto = new AuthDto();
        authDto.setUserId(user.getId());
        authDto.setToken(token);
        authDto.setRole(user.getAuthType());
        AuthHelper.setAuth(authDto);
        return pjp.proceed();
    }

}
