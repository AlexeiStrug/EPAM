package com.sav.autobase.webapp.converter.entity2model;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.Users;
import com.sav.autobase.webapp.models.DriverUsersModel;

public class Driver2Model implements Converter<Users, DriverUsersModel> {

	public DriverUsersModel convert(Users user) {
		return driver2model(user);
	}
	
	private DriverUsersModel driver2model(Users user) {
		DriverUsersModel userModel = new DriverUsersModel();
		userModel.setId(user.getId());
		userModel.setFirstName(user.getFirstName());
		userModel.setLastName(user.getLastName());
		userModel.setLogin(user.getLogin());
		userModel.setType(user.getType().name());
		return userModel;
	}

}
