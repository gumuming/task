package com.gim.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {
    public void receiveMessage(String message, String channel) {
        System.out.println("---频道---: " + channel);
        System.out.println("---消息内容---: " + message);
    }

    /**
     * 这个地方 是给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“receiveMessage”
     * 也有好几个重载方法，这边默认调用处理器的方法 叫handleMessage 可以自己到源码里面看
     *
     * @param receiver
     * @return
     */
    @Bean
    public MessageListenerAdapter listenerAdapter(MessageReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory factory,
                                                   MessageListenerAdapter adapter
    ) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        // 一次订阅多个匹配的频道
        container.addMessageListener(adapter, patternTopic());
        return container;
    }

    /**
     * 订阅匹配的多个频道
     */
    @Bean
    public PatternTopic patternTopic() {
        return new PatternTopic("redis.*");
    }
}
