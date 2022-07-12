package com.gim.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class MQServiceImpl implements MQService {


    private static Logger log = LoggerFactory.getLogger(MQServiceImpl.class);

    private static final String MESSAGE_KEY = "message:queue";

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private JedisPool jedisPool;

    @Override
    public void produce(String string) {
        redisTemplate.opsForList().leftPush(MESSAGE_KEY, string);
    }

    @Override
    public void consume() {
        final Object o = redisTemplate.opsForList().leftPop(MESSAGE_KEY, 10, TimeUnit.SECONDS);
//        String string = (String) redisTemplate.opsForList().rightPop(MESSAGE_KEY);
        assert o != null;
        log.info("consume : {}", o.toString());
    }

    @Override
    public void blockingConsume() {
        final Jedis jedis = jedisPool.getResource();
        final List<String> blpop = jedis.blpop(10, MESSAGE_KEY);

//        List<Object> obj = redisTemplate.executePipelined(new RedisCallback<Object>() {
//            @Nullable
//            @Override
//            public Object doInRedis(RedisConnection connection) throws DataAccessException {
//                //队列没有元素会阻塞操作，直到队列获取新的元素或超时
//                return connection.bLPop(10, MESSAGE_KEY.getBytes());
//            }
//        }, new StringRedisSerializer());
        if(!CollectionUtils.isEmpty(blpop)){
            blpop.forEach(str -> {
                if (!StringUtils.isEmpty(str)) {
                    log.info("blockingConsume : {}", str);
                }
            });
        }
    }

}
