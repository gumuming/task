package com.liaoin;

import org.frame.exception.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ComponentScan(basePackages = {"org.frame","com.liaoin"})
@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
@EnableTransactionManagement
//@EnableCaching
//@EnableRedisHttpSession(redisFlushMode = RedisFlushMode.IMMEDIATE, maxInactiveIntervalInSeconds = 30*60)
@Import(value = {GlobalExceptionHandler.class})
public class WebApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) { return builder.sources(WebApplication.class); }

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
