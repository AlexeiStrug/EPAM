package com.sav.autobase.dao.db.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public abstract class Cache<T> implements ICache<T> {

	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<String, T> template;

	protected abstract String getCacheName();
	protected abstract Integer getLifeTime();
	
	@Override
	public T get(String key) {

		return template.opsForValue().get(getCacheName() + key);
	}

	@Override
	public void set(String key, T value) {

		template.opsForValue().set(getCacheName() + key, value);

		// set a expire for a message
		template.expire(getCacheName() + key, getLifeTime(), TimeUnit.SECONDS);
	}

}
