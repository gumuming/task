package com.gim;

import com.JpaRepositoryReBuild;
import com.gim.controller.TrqTioConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.tio.websocket.starter.EnableTioWebSocketServer;

import javax.annotation.Resource;
import java.io.IOException;


@EnableJpaRepositories(value = "com.gim", repositoryBaseClass = JpaRepositoryReBuild.class)
//@EnableDiscoveryClient // 即消费也注册
@SpringBootApplication

public class Demo1Application {


    public static void main(String[] args) {

        SpringApplication.run(Demo1Application.class, args);

    }

}
