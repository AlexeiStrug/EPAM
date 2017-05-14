package com.sav.autobase.webapp.converter.model2entity;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.TypeUsers;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.webapp.models.UsersModel;

public class Model2User implements Converter<UsersModel, Users> {

	public Users convert(UsersModel userModel) {
		return model2user(userModel);
	}
	
	private Users model2user(UsersModel userModel) {
		Users user = new Users();
		user.setId(userModel.getId());
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setDateBirth(userModel.getDateBirth());
		user.setLogin(userModel.getLogin());
		user.setPassword(userModel.getPassword());
		user.setEmail(userModel.getEmail());
		user.setType(TypeUsers.valueOf(userModel.getType()));
		return user;

	}
}
