package org.frame.signal.websocket;


import org.frame.common.Common;
import org.frame.util.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.WebSocketImpl;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *
 * 作者： liwei
 * 时间：2018年2月28日
 * 描    述：开启websocket线程
 */
@Component
@Order(value = 1)
@Slf4j
public class StartFilter implements ApplicationRunner {
	/**
	 *
	 * 作者： liwei
	 * 时间：2018年2月28日
	 * 描述： 启动即时socket服务
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Integer WEBSOCKET_SWITCH = Integer.valueOf(PropertiesUtil.get("WEBSOCKET_SWITCH"));
		if (Common.isEqual(WEBSOCKET_SWITCH,0)){
			log.info("websocket已启动...");
			WebSocketImpl.DEBUG = false;
			WsServer s;
			s = new WsServer(Integer.valueOf(PropertiesUtil.get("PORT_WEBSOCKET")));
			s.start();
		}
	}
}
