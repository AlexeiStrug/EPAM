package com.sav.autobase.services;

import org.springframework.transaction.annotation.Transactional;

import com.sav.autobase.datamodel.Users;
import com.sav.autobase.services.exception.DAOException;

public interface IAuthenticationService {
	
	Boolean authenticate(String login, String password) throws DAOException;
	
	@Transactional
	void register(Users newUser) throws DAOException;

}
