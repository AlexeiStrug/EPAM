package com.sav.autobase.webapp.converter.model2entity;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.Users;
import com.sav.autobase.webapp.models.AuthUserModel;

public class Model2AuthUser implements Converter<AuthUserModel, Users> {

	public Users convert(AuthUserModel authUserModel) {
		return model2authUser(authUserModel);
	}
	
	private Users model2authUser(AuthUserModel authUserModel) {
		Users user = new Users();
		user.setLogin(authUserModel.getLogin());
		user.setPassword(authUserModel.getPassword());
		return user;

	}

}
