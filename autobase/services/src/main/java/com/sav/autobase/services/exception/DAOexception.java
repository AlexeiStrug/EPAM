package com.sav.autobase.services.exception;

public class DAOexception extends Exception {

	private String msg;

	public DAOexception(String message) {
		this.msg = "DAO connection failure, " + message;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
