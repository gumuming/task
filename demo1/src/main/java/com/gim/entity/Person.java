package com.gim.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: xiang_chat
 * @Date: 2019/5/21 10:28
 */
@Data
public class Person implements Serializable {
    private String name;
    private String age;
    private String sex;
}
