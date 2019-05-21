package org.frame.bean;

public class SessionBean {
	
	private Object uid;//用户id
	private Object userAccount;
	private Object userNickName;


	public Object getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(Object userAccount) {
		this.userAccount = userAccount;
	}

	public Object getUid() {
		return uid;
	}

	public void setUid(Object uid) {
		this.uid = uid;
	}

	public Object getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(Object userNickName) {
		this.userNickName = userNickName;
	}
}
