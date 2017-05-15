package com.sav.autobase.webapp.converter.entity2model;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.Users;
import com.sav.autobase.webapp.models.ClientUsersModel;

public class Client2Model implements Converter<Users, ClientUsersModel> {

	public ClientUsersModel convert(Users user) {
		return client2model(user);
	}
	
	private ClientUsersModel client2model(Users user) {
		ClientUsersModel userModel = new ClientUsersModel();
		userModel.setId(user.getId());
		userModel.setFirstName(user.getFirstName());
		userModel.setLastName(user.getLastName());
		userModel.setLogin(user.getLogin());
		userModel.setType(user.getType().name());
		return userModel;

	}
}
