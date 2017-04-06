package com.sav.autobase.services;

import java.util.List;

import com.sav.autobase.dao.impl.db.filters.UserSearchCriteria;
import com.sav.autobase.datamodel.Users;

public interface IUsersService {
	
	Users getById(Integer id);
	
	void save(Users user);
	
	List<Users> findByCriteria(UserSearchCriteria criteria);
	

}
