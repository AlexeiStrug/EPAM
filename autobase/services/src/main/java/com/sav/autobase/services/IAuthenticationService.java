package com.sav.autobase.services;

import com.sav.autobase.dao.impl.db.exceptions.DaoException;
import com.sav.autobase.datamodel.Users;

public interface IAuthenticationService {
	
	boolean authenticate(String login, String password) throws DaoException;
	
	void create(Users newUser) throws DaoException;

}
