package com.gim.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Component;
import org.tio.common.starter.annotation.TioServerMsgHandler;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.server.handler.IWsMsgHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ： zgc
 * @date ： 2020/8/17 14:44
 * @versions : 1.0
 * @project theone-xwyj
 * @content
 */
@TioServerMsgHandler
@Component
public class TioWebSocket implements IWsMsgHandler {

    private static Map<String, ChannelContext> userSocketMap = new ConcurrentHashMap<>();

    @Override
    public HttpResponse handshake(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
        return httpResponse;
    }

    //http握手成功后触发该方法，一般用于绑定一些参数
    @Override
    public void onAfterHandshaked(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
        Message message = new Message();
        String userId = httpRequest.getParam("userId");
        if (userSocketMap.get(userId) == null) {
            userSocketMap.put(userId, channelContext);
            Tio.bindUser(channelContext, userId);
            message.setSuccess(true);
            message.setMsg(userId + "  连接成功！");
            message.setCode(0);
            message.setMsgType(3);
        } else {
            userSocketMap.put(userId, channelContext);
            Tio.bindUser(channelContext, userId);
            message.setSuccess(false);
            message.setMsg(userId + "  请勿重复连接！");
            message.setCode(107);
            message.setMsgType(3);
        }
        //用tio-websocket，服务器发送到客户端的Packet都是WsResponse
        WsResponse wsResponse = WsResponse.fromText(JSON.toJSONString(message), "UTF-8");
        System.out.println("收到消息：" + JSON.toJSONString(message));
        //点对点发送
        if (userSocketMap != null && userSocketMap.size() > 0) {
            Tio.sendToUser(channelContext.tioConfig, userId, wsResponse);
        } else {
            Tio.send(channelContext, wsResponse);
        }
    }

    @Override
    public Object onBytes(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
        return null;
    }

    @Override
    public Object onClose(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
        return null;
    }

    @Override
    public Object onText(WsRequest wsRequest, String text, ChannelContext channelContext) throws Exception {
        if (Objects.equals("心跳内容", text)) {
            return null;
        }
        //服务器发送到客户端的Packet都是WsResponse
        Message tioSendMessage = JSONArray.parseObject(text, Message.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tioSendMessage.setReceiveTime(sdf.format(new Date()));
        WsResponse wsResponse;
        wsResponse = WsResponse.fromText(JSONArray.toJSONString(tioSendMessage), "UTF-8");
        Tio.sendToUser(channelContext.tioConfig, tioSendMessage.getToUserID(), wsResponse);
        tioSendMessage.setSuccess(true);
        tioSendMessage.setMsg("发送个人消息成功！");
        tioSendMessage.setMsgType(3);
        System.out.println("收到客户端发来信息：" + JSONArray.toJSONString(tioSendMessage));
        return JSONArray.toJSONString(tioSendMessage);
    }
}
