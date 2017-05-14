package com.sav.autobase.dao.db.cache;

import org.springframework.stereotype.Component;

import com.sav.autobase.datamodel.Users;

@Component
public class UserCache extends Cache<Users>{

	@Override
	protected String getCacheName() {
		return "user:";
	}

	@Override
	protected Integer getLifeTime() {
		return 240;
	}
	

}
