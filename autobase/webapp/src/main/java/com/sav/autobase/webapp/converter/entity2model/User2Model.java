package com.sav.autobase.webapp.converter.entity2model;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.Users;
import com.sav.autobase.webapp.models.UsersModel;

public class User2Model implements Converter<Users, UsersModel> {

	public UsersModel convert(Users user) {
		return user2model(user);
	}
	
	private UsersModel user2model(Users user) {
		UsersModel userModel = new UsersModel();
		userModel.setId(user.getId());
		userModel.setFirstName(user.getFirstName());
		userModel.setLastName(user.getLastName());
		userModel.setDateBirth(user.getDateBirth());
		userModel.setLogin(user.getLogin());
		userModel.setPassword(user.getPassword());
		userModel.setEmail(user.getEmail());
		userModel.setType(user.getType().name());
		return userModel;

	}
}
