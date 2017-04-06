package com.sav.autobase.dao.impl.db.filters;

import java.sql.Timestamp;

import com.sav.autobase.datamodel.Users;

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

	public boolean accept(Users user) {
		return acceptFirstName(user.getFirstName()) 
				&& acceptLasttName(user.getLastName())
				&& acceptLogin(user.getLogin())
				&& acceptEmail(user.getEmail()) 
				&& acceptDateBirth(user.getDateBirth())
				&& acceptType(user.getType().name());
	}

	private boolean acceptFirstName(String firstName) {
		return this.firstName == null || firstName.equals(firstName);
	}

	private boolean acceptLasttName(String lastName) {
		return this.lastName == null || lastName.equals(lastName);
	}

	private boolean acceptLogin(String login) {
		return this.login == null || login.equals(login);
	}

	private boolean acceptEmail(String email) {
		return this.email == null || email.equals(email);
	}

	private boolean acceptDateBirth(Timestamp date) {
		return this.dateBirth == null || date.equals(date);
	}

	private boolean acceptType(String type) {
		return this.type == null || type.equals(type);
	}

}
