package com.gim.controller;

import java.io.Serializable;

/**
 * Socket 发送消息模板
 */
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer msgType; // 0事件信息 1预警信息 2地图同步 3系统返回信息 4心跳监测
	private Integer sendType;// 1发送 0接收
	private Boolean success;
	private String msg;
	private Object data;
	private Integer code;
    private String toUserID;//接收人ID
    private String receiveTime;//接收时间
	public Message() {
	}

	public Message(Integer msgType, Object data) {
		this.msgType = msgType;
		this.data = data;
	}
    public String getToUserID() {
        return toUserID;
    }

    public void setToUserID(String toUserID) {
        this.toUserID = toUserID;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getSendType() {
		return sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}

