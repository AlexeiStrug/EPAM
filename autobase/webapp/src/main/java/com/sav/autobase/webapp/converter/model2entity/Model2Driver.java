package com.sav.autobase.webapp.converter.model2entity;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.TypeUsers;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.webapp.models.DriverUsersModel;

public class Model2Driver implements Converter<DriverUsersModel, Users> {

	public Users convert(DriverUsersModel userModel) {
		return model2driver(userModel);
	}
	
	private Users model2driver(DriverUsersModel userModel) {
		Users user = new Users();
		user.setId(userModel.getId());
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setLogin(userModel.getLogin());
		user.setType(TypeUsers.valueOf(userModel.getType()));
		return user;
	}
}
