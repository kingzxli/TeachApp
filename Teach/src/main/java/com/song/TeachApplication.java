package com.song;

import java.util.Properties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import com.github.pagehelper.PageHelper;


/**
 * 
 * @author Song
 *
 */

@SpringBootApplication
@MapperScan("com.song.mapper")
public class TeachApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TeachApplication.class, args);
	}

	/*
	 * @Bean public Servlet baiduProxyServlet(){ return new ProxyServlet(); }
	 * 
	 * @Bean public ServletRegistrationBean proxyServletRegistration(){
	 * ServletRegistrationBean registrationBean = new
	 * ServletRegistrationBean(baiduProxyServlet(), "/proxy"); Map<String, String>
	 * params = ImmutableMap.of( "targetUri", "http://www.baidu.com", "log",
	 * "true"); registrationBean.setInitParameters(params); return registrationBean;
	 * }
	 */
	
	
	@Bean
     public EmbeddedServletContainerCustomizer containerCustomizer(){
         return container -> {
             container.setSessionTimeout(0);/*单位为S*/
         };
     }
	
	@Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
        pageHelper.setProperties(properties);
        return pageHelper;
    }
	

	
}
