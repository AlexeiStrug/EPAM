package com.sav.autobase.services.exception;

public class DAOException extends Exception {

	private String msg;

	public DAOException(String msg) {
		this.msg = "DAO connection failure, " + msg;
	}



}
