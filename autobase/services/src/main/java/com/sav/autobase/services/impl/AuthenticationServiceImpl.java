package com.sav.autobase.services.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sav.autobase.dao.impl.db.IUsersDao;
import com.sav.autobase.dao.impl.db.exceptions.DaoException;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.services.IAuthenticationService;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

	@Inject
	private IUsersDao usersDao;

	@Override
	public boolean authenticate(String UserLogin, String UserPassword) throws DaoException {
		boolean flag = false;

		Users authentication = usersDao.findByloginPassword(UserLogin, UserPassword);

		if (authentication != null) {
			flag = true;
		}
		return flag;
	}

	@Override
	public void create(Users newUsers) throws DaoException {

		usersDao.update(newUsers);
	}

}
