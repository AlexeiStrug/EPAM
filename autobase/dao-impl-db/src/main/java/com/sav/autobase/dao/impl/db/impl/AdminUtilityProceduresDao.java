package com.sav.autobase.dao.impl.db.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sav.autobase.dao.impl.db.IAdminUtilityProcedures;

@Repository
public class AdminUtilityProceduresDao implements IAdminUtilityProcedures {

	private final static Logger LOGGER = LoggerFactory.getLogger(AdminUtilityProceduresDao.class);

	@Inject
	JdbcTemplate jdbcTemplate;

	private final String DELETE = "TRUNCATE TABLE test.request,test.trip, test.users, test.place, test.vehicle, test.model_vehicle, test.brand_vehicle, test.type_vehicle";

	@Override
	public void delete() {
		try {
			jdbcTemplate.update(DELETE);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
		}
	}

}
