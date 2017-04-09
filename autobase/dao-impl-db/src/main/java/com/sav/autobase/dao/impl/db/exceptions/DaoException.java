package com.sav.autobase.dao.impl.db.exceptions;

public class DaoException extends Exception {
	 
	private String message;

	    public DaoException() {
	        message = "DAO connection failure";
	    }

	    @Override
	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

}
