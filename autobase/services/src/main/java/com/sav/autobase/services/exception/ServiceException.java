package com.sav.autobase.services.exception;

public class ServiceException extends Exception {

	private String msg;

	public ServiceException() {
		this.msg = "Service connection failure " ;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}



}
