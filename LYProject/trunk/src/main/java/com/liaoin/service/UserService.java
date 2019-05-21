package com.liaoin.service;

import com.liaoin.dao.UserMapper;
import com.liaoin.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: xiang_chat
 * @Date: 2019/5/21 14:44
 */
@Service
public class UserService implements UserServiceI {

    @Resource
    UserMapper userMapper;


    @Override
    public User findUse() {
        return userMapper.;
    }
}
