package com.sav.autobase.services.exception;

public class DAOException extends Exception {

	private String msg;

	public DAOException(String message) {
		this.msg = "DAO connection failure, " + message;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
