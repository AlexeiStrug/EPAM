package com.sav.autobase.dao.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.sav.autobase.datamodel.BrandVehicle;

@Repository
public class BrandVehicleDaoImpl extends GenericDaoImpl<BrandVehicle> {

	private final String INSERT_SQL = "INSERT INTO brand_vehicle (brand_name) VALUES(?)";

	private final static Logger LOGGER = LoggerFactory.getLogger(BrandVehicleDaoImpl.class);

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	protected String getTableName() {
		return "brand_vehicle";
	}

	@Override
	public BrandVehicle insert(BrandVehicle brand) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, brand.getBrandName());
				return ps;
			}
		}, keyHolder);

		Number key = keyHolder.getKey();
		brand.setId(key.intValue());
		return brand;
	}

	@Override
	public BrandVehicle update(BrandVehicle entity) throws UnsupportedOperationException {
		LOGGER.debug("Used UnsupportedOperationException");
		throw new UnsupportedOperationException();
	}

}
