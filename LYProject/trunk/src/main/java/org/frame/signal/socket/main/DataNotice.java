package org.frame.signal.socket.main;

import java.nio.ByteBuffer;

/**
 * 作者：Li.Wei
 * 时间：2018/7/25
 * 描述：数据通知接口
 */
public interface DataNotice {
    /**
     * 数据接收
     * @param buffer
     */
    void receive(ByteBuffer buffer, SocketSession session);
}
