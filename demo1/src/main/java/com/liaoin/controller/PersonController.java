package com.liaoin.controller;

import com.liaoin.entity.Person;
import lombok.extern.java.Log;

/**
 * @Author: xiang_chat
 * @Date: 2019/5/21 10:27
 */
public class PersonController {
    public static void main(String[] args){
        Person p1 = new Person();
        p1.setName("向世林");
        p1.setAge("20");
        p1.setSex("男");
        System.out.println(p1.toString());

        Person p2 = new Person();
        p2.setName("向世林");
        p2.setAge("20");
        p2.setSex("男");
        System.out.println(p2.equals(p1));

        p1.setName("秦始皇");
        System.out.println(p2.equals(p1));

    }
}
