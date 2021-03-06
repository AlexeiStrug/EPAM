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

import com.sav.autobase.datamodel.TypeVehicle;

@Repository
public class TypeVehicleDaoImpl extends GenericDaoImpl<TypeVehicle> {

	private final String INSERT_SQL = "INSERT INTO type_vehicle (type_name) VALUES(?)";

	private final static Logger LOGGER = LoggerFactory.getLogger(TypeVehicleDaoImpl.class);

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	protected String getTableName() {
		return "type_vehicle";
	}

	@Override
	public TypeVehicle insert(TypeVehicle type) {

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, type.getTypeName());
				return ps;
			}
		}, keyHolder);

		Number key = keyHolder.getKey();
		type.setId(key.intValue());
		return type;
	}

	@Override
	public TypeVehicle update(TypeVehicle entity) throws UnsupportedOperationException {
		LOGGER.error("Used UnsupportedOperationException");
		throw new UnsupportedOperationException();
	}

}
