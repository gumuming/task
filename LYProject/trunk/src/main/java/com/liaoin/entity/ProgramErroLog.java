package com.liaoin.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "program_erro_log")
public class ProgramErroLog {
    /**
     * id主键
     */
    @Id
    private Integer id;

    /**
     * 错误编码
     */
    @Column(name = "erroCode")
    private String errocode;

    /**
     * 设备标识
     */
    @Column(name = "deviceId")
    private String deviceid;

    /**
     * 创建时间
     */
    @Column(name = "createDate")
    private Date createdate;

    /**
     * 错误信息
     */
    @Column(name = "erroMsg")
    private String erromsg;

    /**
     * 错误提示
     */
    @Column(name = "erroDesc")
    private String errodesc;

    /**
     * 请求参数
     */
    @Column(name = "requestParm")
    private String requestparm;

    /**
     * 请求人信息
     */
    @Column(name = "requestUser")
    private String requestuser;

    /**
     * 获取id主键
     *
     * @return id - id主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id主键
     *
     * @param id id主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取错误编码
     *
     * @return erroCode - 错误编码
     */
    public String getErrocode() {
        return errocode;
    }

    /**
     * 设置错误编码
     *
     * @param errocode 错误编码
     */
    public void setErrocode(String errocode) {
        this.errocode = errocode;
    }

    /**
     * 获取设备标识
     *
     * @return deviceId - 设备标识
     */
    public String getDeviceid() {
        return deviceid;
    }

    /**
     * 设置设备标识
     *
     * @param deviceid 设备标识
     */
    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    /**
     * 获取创建时间
     *
     * @return createDate - 创建时间
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * 设置创建时间
     *
     * @param createdate 创建时间
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * 获取错误信息
     *
     * @return erroMsg - 错误信息
     */
    public String getErromsg() {
        return erromsg;
    }

    /**
     * 设置错误信息
     *
     * @param erromsg 错误信息
     */
    public void setErromsg(String erromsg) {
        this.erromsg = erromsg;
    }

    /**
     * 获取错误提示
     *
     * @return erroDesc - 错误提示
     */
    public String getErrodesc() {
        return errodesc;
    }

    /**
     * 设置错误提示
     *
     * @param errodesc 错误提示
     */
    public void setErrodesc(String errodesc) {
        this.errodesc = errodesc;
    }

    /**
     * 获取请求参数
     *
     * @return requestParm - 请求参数
     */
    public String getRequestparm() {
        return requestparm;
    }

    /**
     * 设置请求参数
     *
     * @param requestparm 请求参数
     */
    public void setRequestparm(String requestparm) {
        this.requestparm = requestparm;
    }

    /**
     * 获取请求人信息
     *
     * @return requestUser - 请求人信息
     */
    public String getRequestuser() {
        return requestuser;
    }

    /**
     * 设置请求人信息
     *
     * @param requestuser 请求人信息
     */
    public void setRequestuser(String requestuser) {
        this.requestuser = requestuser;
    }
}