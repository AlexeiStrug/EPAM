package com.sav.autobase.dao.api;

import java.util.List;

import com.sav.autobase.dao.api.filter.UserSearchCriteria;
import com.sav.autobase.datamodel.Users;

public interface IUsersDao extends IGenericDao<Users> {
	
	Users findByloginPassword (String login, String password);

	List<Users> findByCriteria(UserSearchCriteria criteria);

}
