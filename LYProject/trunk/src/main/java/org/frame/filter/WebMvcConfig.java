package org.frame.filter;

import org.frame.util.PropertiesUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @项目名：公司内部模板项目
 * @作者：zhou.ning
 * @描述：Swagger配置
 * @日期：Created in 2018/6/8 16:47
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        //路径磁盘映射_以下配置测试：http://localhost:8099/upload/image/图片.png
//        registry.addResourceHandler("/upload/**")
//                .addResourceLocations("file:E:/upload/");
    }

}
