package com.sav.autobase.webapp.converter.entity2model;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.Users;
import com.sav.autobase.webapp.models.AuthUserModel;

public class AuthUser2Model implements Converter<Users, AuthUserModel> {

	public AuthUserModel convert(Users user) {
		return authUser2model(user);
	}
	
	private AuthUserModel authUser2model(Users user) {
		AuthUserModel authUserModel = new AuthUserModel();
		authUserModel.setLogin(user.getLogin());
		authUserModel.setPassword(user.getPassword());
		return authUserModel;

	}

}
