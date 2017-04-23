package com.sav.autobase.dao.api.filter;

import java.sql.Timestamp;

public class UserSearchCriteria {

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

	public boolean isEmpty() {
		return firstName == null 
				&& lastName == null 
				&& login == null 
				&& email == null 
				&& dateBirth == null
				&& type == null;
	}


}
