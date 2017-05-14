package com.sav.autobase.dao.db.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sav.autobase.dao.api.IGenericDao;

@Repository
public abstract class GenericDaoImpl<T> implements IGenericDao<T> {

	@Inject
	private JdbcTemplate jdbcTemplate;

	protected Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
             .getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass
             .getActualTypeArguments()[0];
    }

	protected abstract String getTableName();

	private final String FIND_BY_ID = "SELECT * FROM " + getTableName() + " WHERE id = ?";
	private final String GET_ALL = "SELECT * FROM " + getTableName();
	private final String DELETE = "DELETE FROM " + getTableName() + " WHERE id= ";

	private final static Logger LOGGER = LoggerFactory.getLogger(GenericDaoImpl.class);

	
	@Override
	public T getById(Integer id) {
		try {
			return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[] { id },
					new BeanPropertyRowMapper<T>(entityClass));
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Exception thrown! ", e);
			return null;
		}
	}

	@Override
	public abstract T insert(T entity);

	@Override
	public abstract T update(T entity);

	@Override
	public void delete(Integer id) {
		try {
			jdbcTemplate.update(DELETE + id);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Exception thrown! ", e);
		}
	}

	@Override
	public List<T> getAll() {
		try {
			List<T> rs = jdbcTemplate.query(GET_ALL, new BeanPropertyRowMapper<T>(entityClass));
			return rs;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Exception thrown! ", e);
			return null;
		}
	}

}
