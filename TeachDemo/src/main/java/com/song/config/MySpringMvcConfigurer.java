package com.song.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.song.interceptor.LoginHandlerInterceptor;


@Configuration
public class MySpringMvcConfigurer extends WebMvcConfigurerAdapter{
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		System.out.println("添加拦截器");
		//拦截器按照顺序执行
		registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/parent/**");
		// TODO Auto-generated method stub
		super.addInterceptors(registry);
	}
	
}
