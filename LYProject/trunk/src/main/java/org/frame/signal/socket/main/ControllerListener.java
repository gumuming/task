package org.frame.signal.socket.main;/**
 * Created by Set on 2018/8/19.
 */

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 作者：Li.Wei
 * 时间：2018/7/25
 * 描述：管理器监听
 */
public interface ControllerListener {

    void execute(SocketController controller, SelectionKey selectionKey, SocketSession session,
                 ThreadPoolExecutor pool) throws IOException;

    void notice(DataNotice listener);
}
