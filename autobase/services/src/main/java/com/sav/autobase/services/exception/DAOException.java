package com.sav.autobase.services.exception;

public class DAOException extends Exception {

	private String msg;

	public DAOException() {
		this.msg = "DAO connection failure " ;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}



}
