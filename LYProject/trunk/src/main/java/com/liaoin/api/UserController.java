package com.liaoin.api;

import com.liaoin.service.UserService;
import com.liaoin.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xiang_chat
 * @Date: 2019/5/18 19:57
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserServiceI userServiceI;

    @GetMapping("/get")
    public String findUesr(){
        return userServiceI.findUse().getUsername();
    }


}
