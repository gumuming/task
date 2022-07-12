package com.gim.controller;

import com.gim.live.ApiUserDao;
import com.gim.live.GuardUserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Gim
 */
@RestController
@RequestMapping("/sql")
public class SqlController {

    @Resource
    private ApiUserDao apiUserDao;


    @GetMapping("/guard")
    public String guard(){
        final List<GuardUserDto> guardUserDtoPageLists = apiUserDao.getGuardUserDtoPageLists(1, 10, 10L);
        return "success";
    }

}
