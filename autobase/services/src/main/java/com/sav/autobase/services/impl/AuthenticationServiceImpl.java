package com.sav.autobase.services.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sav.autobase.dao.impl.db.IUsersDao;
import com.sav.autobase.dao.impl.db.exceptions.DaoException;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.services.IAuthenticationService;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

	private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

	@Inject
	private IUsersDao usersDao;

	@Override
	public boolean authenticate(String UserLogin, String UserPassword) throws DaoException {
		boolean flag = false;
		Users authentication = usersDao.findByloginPassword(UserLogin, UserPassword);
		if (authentication != null) {
			LOGGER.info("Success authenticate User = " + UserLogin);
			flag = true;
		} else
			LOGGER.info("Failed authenticate");
		return flag;
	}

	@Override
	public void register(Users newUsers) throws DaoException {
		LOGGER.info("Register new User");
		usersDao.update(newUsers);
	}

}
