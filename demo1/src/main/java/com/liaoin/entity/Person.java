package com.liaoin.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: xiang_chat
 * @Date: 2019/5/21 10:28
 */
 @Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @KeySql(genId = UUIdGenId.class)
    private String id;

    @Column(name = "create_at",insertable = false,updatable = false)
    private Date createAt;

    @Column(name = "update_at",insertable = false,updatable = false)
    private Date updateAt;

    @Column(name = "c_user_name")
    private String username;

    @Column(name = "c_phone_num")
    private String phoneNum;


}
