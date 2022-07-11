package com.gim.redis;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/api")
public class MQController {

    @Resource
    private MQService mQService;

    @RequestMapping(value = "/produce", method = RequestMethod.GET)
    public void produce(@RequestParam(name = "key") String key) {
        mQService.produce(key);
    }

    @RequestMapping(value = "/consume", method = RequestMethod.GET)
    public void consume() {
        mQService.consume();
    }

    @RequestMapping(value = "/bconsume", method = RequestMethod.GET)
    public void bconsume() {
        while (true) {
            mQService.blockingConsume();
        }
    }


}
