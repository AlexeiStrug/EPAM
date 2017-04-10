package com.sav.autobase.services;

import javax.naming.AuthenticationException;

import com.sav.autobase.dao.impl.db.exceptions.DaoException;
import com.sav.autobase.datamodel.Users;

public interface IAuthenticationService {
	
	boolean authenticate(String login, String password) throws DaoException;
	
	void register(Users newUser) throws DaoException;

}
