package com.song;

import java.util.Properties;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

import com.github.pagehelper.PageHelper;


/**
 * 
 * @author Song
 *
 */

@SpringBootApplication
@MapperScan("com.song.mapper")
public class TeachDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeachDemoApplication.class, args);
		System.out.println(args);
	}
	
//	@Bean
//     public EmbeddedServletContainerCustomizer containerCustomizer(){
//         return container -> {
//             container.setSessionTimeout(120);/*单位为S*/
//         };
//     }
	
	
	//分页
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
