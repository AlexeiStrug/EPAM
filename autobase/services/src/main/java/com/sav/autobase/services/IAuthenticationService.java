package com.sav.autobase.services;

import org.springframework.transaction.annotation.Transactional;

import com.sav.autobase.datamodel.Users;
import com.sav.autobase.services.exception.ModifyException;
import com.sav.autobase.services.exception.ServiceException;

public interface IAuthenticationService {
	
	Users authenticate(String login, String password) throws ServiceException;
	
	@Transactional
	void register(Users newUser) throws ServiceException, ModifyException;

}
