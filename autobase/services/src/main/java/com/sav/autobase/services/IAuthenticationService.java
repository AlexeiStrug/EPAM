package com.sav.autobase.services;

import com.sav.autobase.datamodel.Users;
import com.sav.autobase.services.exception.DAOexception;

public interface IAuthenticationService {
	
	Boolean authenticate(String login, String password) throws DAOexception;
	
	void register(Users newUser) throws DAOexception;

}
