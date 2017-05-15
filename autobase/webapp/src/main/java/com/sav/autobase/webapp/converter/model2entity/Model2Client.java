package com.sav.autobase.webapp.converter.model2entity;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.TypeUsers;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.webapp.models.ClientUsersModel;

public class Model2Client implements Converter<ClientUsersModel, Users> {

	public Users convert(ClientUsersModel userModel) {
		return model2client(userModel);
	}
	
	private Users model2client(ClientUsersModel userModel) {
		Users user = new Users();
		user.setId(userModel.getId());
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setLogin(userModel.getLogin());
		user.setType(TypeUsers.valueOf(userModel.getType()));
		return user;

	}
}
