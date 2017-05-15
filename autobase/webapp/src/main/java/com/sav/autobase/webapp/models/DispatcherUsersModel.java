package com.sav.autobase.webapp.models;

/**
 * This class ClientUsersModel serves to store objects with properties user
 * dispatcher
 * 
 * @author AlexStrug
 * @version 1
 *
 */
public class DispatcherUsersModel extends IdModel {

	/**
	 * firstName - the first name user dispatcher <br>
	 * dateBirth - date birth user dispatcher <br>
	 * login - the login user dispatcher <br>
	 * type - the type user dispatcher
	 */
	private String firstName;
	private String login;
	private String email;
	private String type;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
