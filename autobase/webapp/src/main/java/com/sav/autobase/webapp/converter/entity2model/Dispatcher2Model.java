package com.sav.autobase.webapp.converter.entity2model;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.Users;
import com.sav.autobase.webapp.models.DispatcherUsersModel;

public class Dispatcher2Model implements Converter<Users, DispatcherUsersModel> {

	public DispatcherUsersModel convert(Users user) {
		return dispatcher2model(user);
	}
	
	private DispatcherUsersModel dispatcher2model(Users user) {
		DispatcherUsersModel userModel = new DispatcherUsersModel();
		userModel.setFirstName(user.getFirstName());
		userModel.setLogin(user.getLogin());
		userModel.setEmail(user.getEmail());
		userModel.setType(user.getType().name());
		return userModel;

	}

}
