package com.sav.autobase.webapp.converter.model2entity;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.TypeUsers;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.webapp.models.DispatcherUsersModel;

public class Model2Dispathcer implements Converter<DispatcherUsersModel, Users> {

	public Users convert(DispatcherUsersModel userModel) {
		return model2dispatcher(userModel);
	}
	
	private Users model2dispatcher(DispatcherUsersModel userModel) {
		Users user = new Users();
		user.setId(userModel.getId());
		user.setFirstName(userModel.getFirstName());
		user.setLogin(userModel.getLogin());
		user.setEmail(userModel.getEmail());
		user.setType(TypeUsers.valueOf(userModel.getType()));
		return user;

	}
}
