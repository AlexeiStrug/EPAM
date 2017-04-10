package com.sav.autobase.dao.impl.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.sav.autobase.dao.impl.db.ITypeVehicleDao;
import com.sav.autobase.dao.impl.db.exceptions.DaoException;
import com.sav.autobase.datamodel.TypeVehicle;

@Repository
public class TypeVehicleDaoImpl implements ITypeVehicleDao {

	private final String FIND_TYPE_BY_ID = "SELECT * FROM type_vehicle WHERE id = ? ";
	private final String GET_ALL_TYPE = "SELECT * FROM type_vehicle";
	private final String INSERT_SQL = "INSERT INTO type_vehicle (type_name) VALUES(?)";
	private final String DELETE_TYPE = "DELETE FROM type_vehicle WHERE id = ?";

	private final static Logger LOGGER = LoggerFactory.getLogger(TypeVehicleDaoImpl.class);

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public TypeVehicle getById(Integer id) throws DaoException {
		try {
			return jdbcTemplate.queryForObject(FIND_TYPE_BY_ID, new Object[] { id },
					new BeanPropertyRowMapper<TypeVehicle>(TypeVehicle.class));
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
			return null;
		}
	}

	@Override
	public List<TypeVehicle> getAll() throws DaoException {
		try {
			List<TypeVehicle> rs = jdbcTemplate.query(GET_ALL_TYPE,
					new BeanPropertyRowMapper<TypeVehicle>(TypeVehicle.class));
			return rs;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
			return null;
		}
	}

	@Override
	public TypeVehicle insert(TypeVehicle type) throws DaoException {

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
	public void delete(Integer id) throws DaoException {
		jdbcTemplate.update(DELETE_TYPE + id);

	}

	@Override
	public TypeVehicle update(TypeVehicle entity) throws DaoException {
		 LOGGER.debug("Used UnsupportedOperationException");
		 throw new UnsupportedOperationException();
	}

}
