package com.sav.autobase.services;

import org.springframework.transaction.annotation.Transactional;

import com.sav.autobase.datamodel.Users;
import com.sav.autobase.services.exception.DAOexception;

public interface IAuthenticationService {
	
	Boolean authenticate(String login, String password) throws DAOexception;
	
	@Transactional
	void register(Users newUser) throws DAOexception;

}
