package com.sav.autobase.webapp.models;

/**
 * This class AuthUserModel need for authorization user by login and password
 * 
 * @author AlexStrug
 * @version 1
 * 
 */
public class AuthUserModel {

	/**
	 * login - the user login <br>
	 * password - the user password
	 */
	private String login;
	private String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
