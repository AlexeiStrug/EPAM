package com.sav.autobase.webapp.models;

/**
 * This class DriverUsersModel serves to store objects with properties user
 * driver
 * 
 * @author AlexStrug
 * @version 1
 */
public class DriverUsersModel extends IdModel {

	/**
	 * firstName - the first name user driver <br>
	 * lastName - the second name user driver<br>
	 * login - the login user driver
	 */
	private String firstName;
	private String lastName;
	private String login;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
