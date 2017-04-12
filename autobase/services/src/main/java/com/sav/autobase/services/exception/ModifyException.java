package com.sav.autobase.services.exception;

public class ModifyException extends Exception {

	private String msg;

	public ModifyException() {
		this.msg = "Status ready. Failure modify data." ;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
