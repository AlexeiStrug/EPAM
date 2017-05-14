package com.sav.autobase.dao.db.cache;

public interface ICache <T>{
	
	T get(String key);
	
	void set(String key, T value);

}
