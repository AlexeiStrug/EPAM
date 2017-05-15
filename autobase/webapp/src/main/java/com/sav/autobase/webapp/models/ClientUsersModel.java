package com.sav.autobase.webapp.models;

/**
 * This class ClientUsersModel serves to store objects with properties user
 * client
 * 
 * @author AlexStrug
 * @version 1
 */
public class ClientUsersModel extends IdModel {

	/**
	 * firstName - the first name user client <br>
	 * lastName - the second name user client <br>
	 * login - the login user <br>
	 * type - the type user client
	 */
	private String firstName;
	private String lastName;
	private String login;
	private String type;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
