package com.sav.autobase.dao.impl.db;

import java.util.List;

import com.sav.autobase.dao.impl.db.filters.UserSearchCriteria;
import com.sav.autobase.datamodel.Users;

public interface IUsersDao {
	
	Users getById(Integer id);
	
	Users insert(Users user);
	
	Users update(Users user);
	
	void delete(Integer id);
	
	List<Users> getAll();
	
	List<Users> findByCriteria(UserSearchCriteria criteria);
	
	

}
