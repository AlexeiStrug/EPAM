package com.sav.autobase.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sav.autobase.dao.impl.db.IUsersDao;
import com.sav.autobase.dao.impl.db.filters.UserSearchCriteria;
import com.sav.autobase.datamodel.TypeUsers;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.services.IUsersService;

@Service
public class UsersService implements IUsersService{
	
	@Inject IUsersDao usersDao;

	@Override
	public List<Users> findByCriteria(UserSearchCriteria criteria) {
		return usersDao.findByCriteria(criteria);
	}

	@Override
	public Users getById(Integer id) {
		return usersDao.getById(id);
	}

	@Override
	public void save(Users user) {
		if (user.getId() == null) {
			System.out.print("Insert new user");
			if (user.getType() == null){
				user.setType(TypeUsers.client);
			}
			usersDao.insert(user);
		} else {
			System.out.print("Update new user");
			usersDao.update(user);
		}
		
	}
}
