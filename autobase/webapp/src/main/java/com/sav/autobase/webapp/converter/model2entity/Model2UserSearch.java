package com.sav.autobase.webapp.converter.model2entity;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.dao.api.filter.UserSearchCriteria;
import com.sav.autobase.webapp.models.UserSearchModel;

public class Model2UserSearch implements Converter<UserSearchModel, UserSearchCriteria> {

	public UserSearchCriteria convert(UserSearchModel criteriaModel) {
		return model2userSearch(criteriaModel);
	}
	
	private UserSearchCriteria model2userSearch(UserSearchModel criteriaModel) {
		UserSearchCriteria criteria = new UserSearchCriteria();
		criteria.setFirstName(criteriaModel.getFirstName());
		criteria.setLastName(criteriaModel.getLastName());
		criteria.setDateBirth(criteriaModel.getDateBirth());
		criteria.setEmail(criteriaModel.getEmail());
		criteria.setLogin(criteriaModel.getLogin());
		criteria.setType(criteriaModel.getType());
		return criteria;
	}

}
