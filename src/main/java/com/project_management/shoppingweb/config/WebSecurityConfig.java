package com.project_management.shoppingweb.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter{

	/*
	 * 登录session key
	 * */
	
	public final static String SESSION_KEY = "admin";
	
	@Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }
	
	public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
       
       /* // 排除配置
        addInterceptor.excludePathPatterns("/error");

        addInterceptor.excludePathPatterns("/admin/login**");
        addInterceptor.excludePathPatterns("/admin/addlogin");
        addInterceptor.excludePathPatterns("/admin/addregister");
        addInterceptor.excludePathPatterns("/admin/register");
        

        // 拦截配置
        addInterceptor.addPathPatterns("/**");*/
    }
	
	 private class SecurityInterceptor extends HandlerInterceptorAdapter {

	        @Override
	        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	                throws Exception {
	            HttpSession session = request.getSession();
	            if (session.getAttribute(SESSION_KEY) != null) {
	            	return true;
	            }
	                
	            // 跳转登录
	            String url = "/admin/login";
	            response.sendRedirect(url);	      
	            return false;
	        }
	    }
}
