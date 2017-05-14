package com.sav.autobase.webapp.converter.entity2model;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.dao.api.filter.UserSearchCriteria;
import com.sav.autobase.webapp.models.UserSearchModel;

public class UserSearch2Model implements Converter<UserSearchCriteria, UserSearchModel> {

	public UserSearchModel convert(UserSearchCriteria criteria) {
		return userSearch2model(criteria);
	}
	
	private UserSearchModel userSearch2model(UserSearchCriteria criteria) {
		UserSearchModel criteriaModel = new UserSearchModel();
		criteriaModel.setFirstName(criteria.getFirstName());
		criteriaModel.setLastName(criteria.getLastName());
		criteriaModel.setDateBirth(criteria.getDateBirth());
		criteriaModel.setEmail(criteria.getEmail());
		criteriaModel.setLogin(criteria.getLogin());
		criteriaModel.setType(criteria.getType());
		return criteriaModel;
	}

}
