package com.sav.autobase.webapp.models;

import java.sql.Timestamp;

public class UsersModel extends IdModel {

	private String firstName;
	private String lastName;
	private Timestamp DateBirth;
	private String login;
	private String password;
	private String email;
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

	public Timestamp getDateBirth() {
		return DateBirth;
	}

	public void setDateBirth(Timestamp timestamp) {
		DateBirth = timestamp;
	}

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
