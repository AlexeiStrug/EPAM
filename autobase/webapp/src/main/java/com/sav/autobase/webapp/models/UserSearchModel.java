package com.sav.autobase.webapp.models;

import java.sql.Timestamp;

/**
 * This class UserSearchModel serves to store objects with properties search
 * user by criteria
 * 
 * @author AlexStrug
 * @version 1
 *
 */
public class UserSearchModel {

	/**
	 * firstName - the first name user client <br>
	 * lastName - the second name user client <br>
	 * dateBirth - date birth user client <br>
	 * login - the login user client <br>
	 * email - the email user client
	 */
	private String firstName;
	private String lastName;
	private String login;
	private String email;
	private Timestamp dateBirth;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Timestamp dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
