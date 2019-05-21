package org.frame.signal.socket.service;


import org.frame.common.Common;
import org.frame.signal.socket.main.SocketServer;
import org.frame.util.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 作者：Li.Wei
 * 时间：2018/7/25 11:45
 * 描述：启动socket通信服务
 */
@Component
@Order(value = 1)
@Slf4j
public class SocketMain implements ApplicationRunner {

    /**
     * 作者： Li.Wei
     * 时间： 2018/7/25 11:45
     * 描述： 启动socket通信
     **/
    @Override
    public void run(ApplicationArguments args) {
        Integer SOCKET_SWITCH = Integer.valueOf(PropertiesUtil.get("SOCKET_SWITCH"));
        if (Common.isEqual(SOCKET_SWITCH,0)){
            //单独开启一个新的线程，socket中的while循环会造成线程阻塞
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        log.info("socket已启动...");
                        ServiceNotice notice = new ServiceNotice();
                        new SocketServer(Integer.valueOf(PropertiesUtil.get("PORT_SOCKET")), notice).run();
                    } catch (Exception e) {
                        log.info("socket启动发送异常...");
                        log.error(e.getMessage());
                    }
                }
            }).start();
        }
    }
}