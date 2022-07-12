package com.gim.controller;

import com.gim.redis.MQService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Gim
 */
@Component
@Order(1)
public class StarterRunner implements CommandLineRunner {
    @Resource
    private MQService mqService;

    @Resource
    ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        final TrqTioConfig trqTioConfig =(TrqTioConfig) applicationContext.getBean("trqTioConfig");
        trqTioConfig.start();
        mqService.blockingConsume();
        System.out.println("redis 消息队列 启动 tio 配置加载");
    }
}
