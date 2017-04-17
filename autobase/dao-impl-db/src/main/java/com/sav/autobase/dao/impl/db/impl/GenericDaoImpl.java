package com.sav.autobase.dao.impl.db.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sav.autobase.dao.impl.db.IGenericDao;

@Repository
public abstract class GenericDaoImpl<T> implements IGenericDao<T> {
	
	@Inject
	private JdbcTemplate jdbcTemplate;

	protected abstract String getTableName();

	private final String FIND_BY_ID = "SELECT * FROM " + getTableName() + " WHERE id = ?";
	private final String GET_ALL = "SELECT * FROM " + getTableName();
	private final String DELETE = "DELETE FROM " + getTableName() + " WHERE id= ";

	private final static Logger LOGGER = LoggerFactory.getLogger(BrandVehicleDaoImpl.class);

	@Override
	public T getById(Object id) {
		try {
			return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[] { id }, new BeanPropertyRowMapper<T>());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
			return null;
		}
	}

	@Override
	public abstract T insert(T entity);

	@Override
	public abstract T update(T entity);

	@Override
	public void delete(Object id) {
		try {
			jdbcTemplate.update(DELETE + id);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
		}
	}

	@Override
	public List<T> getAll() {
		try {
			List<T> rs = jdbcTemplate.query(GET_ALL, new BeanPropertyRowMapper<T>());
			return rs;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
			return null;
		}
	}

}
