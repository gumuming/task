package com.liaoin.entity;

import javax.persistence.*;

@Table(name = "t_user")
public class User {
    @Id
    private Integer id;

    private String loginname;

    private String username;

    private String password;

    /**
     * 用户状态描述
     */
    private String status;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return loginname
     */
    public String getLoginname() {
        return loginname;
    }

    /**
     * @param loginname
     */
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取用户状态描述
     *
     * @return status - 用户状态描述
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置用户状态描述
     *
     * @param status 用户状态描述
     */
    public void setStatus(String status) {
        this.status = status;
    }
}