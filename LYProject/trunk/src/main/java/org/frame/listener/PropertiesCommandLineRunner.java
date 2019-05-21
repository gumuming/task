package org.frame.listener;

import org.frame.util.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Properties;

@Component
@Order(value = 0)
@Slf4j
public class PropertiesCommandLineRunner implements CommandLineRunner {

    @Value("${project.properties.files}")
    private String files;

    @Override
    public void run(String... args) throws Exception {
        log.info("开始读取配置文件...");
        try {
            if (StringUtils.isNoneBlank(files)) {
                Properties prop = new Properties();
                files = files.trim();
                String[] f = files.split(",");
                for (String fileName : f) {
                    InputStreamReader sr = new InputStreamReader(PropertiesCommandLineRunner.class.getClassLoader().getResourceAsStream(fileName),"UTF-8");
                    prop.load(sr);
                }
                PropertiesUtil.init(prop);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
