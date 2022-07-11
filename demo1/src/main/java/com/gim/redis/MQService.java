package com.gim.redis;

public interface MQService {

    void produce(String string);

    void consume();

     void blockingConsume();
}
