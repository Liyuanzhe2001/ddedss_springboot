package com.lyz.ddedss_springboot.config;

import com.lyz.ddedss_springboot.component.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自己的拦截器,并设置拦截的请求路径
        //addPathPatterns：拦截此请求路径的请求
        //excludePathPatterns：不拦截此路径的请求
        registry
                .addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/**");
//                .excludePathPatterns("/user/login");
    }
}
