package com.sav.autobase.dao.impl.db;

import java.util.List;

import com.sav.autobase.dao.impl.db.exceptions.DaoException;
import com.sav.autobase.dao.impl.db.filters.UserSearchCriteria;
import com.sav.autobase.datamodel.Users;

public interface IUsersDao extends IAbstractModelDao<Users> {
	
	Users findByloginPassword (String login, String password) throws DaoException;

	List<Users> getAll() throws DaoException;

	List<Users> findByCriteria(UserSearchCriteria criteria) throws DaoException;

}
