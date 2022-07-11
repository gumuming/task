package com.gim.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tio.core.Tio;
import org.tio.server.ServerTioConfig;
import org.tio.websocket.server.WsServerStarter;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author Gim
 */
@Component
public class TrqTioConfig {

    @Value("${tio.websocket.server.port}")
    private Integer port;

    @Resource
    private TioWebSocket wsMsgHandler;

    /**
     *  //WsServerStarter是你创建的，置为全局变量
     */

    public static WsServerStarter wsServerStarter = null;

    /**
      此处把serverTioConfig置为全局变量存为全局变量即可
     */
    public static ServerTioConfig serverTioConfig = null;



    public  void start() throws IOException {
        wsServerStarter = new WsServerStarter(port, wsMsgHandler);
        wsServerStarter.start();
        serverTioConfig = wsServerStarter.getServerTioConfig();
    }

}
