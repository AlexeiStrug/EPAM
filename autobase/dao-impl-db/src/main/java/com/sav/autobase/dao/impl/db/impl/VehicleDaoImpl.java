package com.sav.autobase.dao.impl.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.sav.autobase.dao.impl.db.IVehicleDao;
import com.sav.autobase.dao.impl.db.filters.VehicleSerachCriteria;
import com.sav.autobase.dao.impl.db.mapper.VehicleMapper;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;

@Repository
public class VehicleDaoImpl extends GenericDaoImpl<Vehicle> implements IVehicleDao {

	final String FIND_VEHICLE_BY_ID = "SELECT * FROM vehicle " + "INNER JOIN users ON users.id=vehicle.driver_id "
			+ "INNER JOIN model_vehicle ON model_vehicle.id=vehicle.model_id "
			+ "INNER JOIN brand_vehicle ON brand_vehicle.id=model_vehicle.brand_id  "
			+ "INNER JOIN type_vehicle  ON model_vehicle.type_id=type_vehicle.id WHERE vehicle.id = ?";
	final String GET_ALL_VEHICLE = "SELECT * FROM vehicle " + "INNER JOIN users ON users.id=vehicle.driver_id "
			+ "INNER JOIN model_vehicle ON model_vehicle.id=vehicle.model_id "
			+ "INNER JOIN brand_vehicle ON brand_vehicle.id=model_vehicle.brand_id  "
			+ "INNER JOIN type_vehicle  ON model_vehicle.type_id=type_vehicle.id ";
	final String GET_ALL_READY_VEHICLE = "SELECT * FROM vehicle " + "INNER JOIN users ON users.id=vehicle.driver_id "
			+ "INNER JOIN model_vehicle ON model_vehicle.id=vehicle.model_id "
			+ "INNER JOIN brand_vehicle ON brand_vehicle.id=model_vehicle.brand_id  "
			+ "INNER JOIN type_vehicle  ON model_vehicle.type_id=type_vehicle.id WHERE ready_crash_car = ?";
	final String GET_BY_USER = "SELECT * FROM vehicle "
			+ "INNER JOIN users ON users.id=vehicle.driver_id "
			+ "INNER JOIN model_vehicle ON model_vehicle.id=vehicle.model_id "
			+ "INNER JOIN brand_vehicle ON brand_vehicle.id=model_vehicle.brand_id  "
			+ "INNER JOIN type_vehicle  ON model_vehicle.type_id=type_vehicle.id "
			+ "WHERE users.id = ?";
	String FIND_CRITERIA_VEHICLE = "SELECT * FROM vehicle " + "INNER JOIN users ON users.id=vehicle.driver_id "
			+ "INNER JOIN model_vehicle ON model_vehicle.id=vehicle.model_id "
			+ "INNER JOIN brand_vehicle ON brand_vehicle.id=model_vehicle.brand_id  "
			+ "INNER JOIN type_vehicle  ON model_vehicle.type_id=type_vehicle.id WHERE true ";
	final String UPDATE_VEHICLE = "UPDATE vehicle SET driver_id = ?, model_id = ?, ready_crash_car = ? WHERE id = ?; ";
	final String INSERT_VEHICLE = "INSERT INTO vehicle (driver_id, model_id, ready_crash_car) VALUES(?,?,?)";

	private final static Logger LOGGER = LoggerFactory.getLogger(VehicleDaoImpl.class);

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Inject
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	protected String getTableName() {
		return "vehicle";
	}
	
	@Override
	public Vehicle joinGetById(Integer id) {
		try {
			return jdbcTemplate.queryForObject(FIND_VEHICLE_BY_ID, new Object[] { id }, new VehicleMapper());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
			return null;
		}
	}

	@Override
	public Vehicle update(Vehicle vehicle) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(UPDATE_VEHICLE);
				ps.setInt(1, vehicle.getDriver().getId());
				ps.setInt(2, vehicle.getModel().getId());
				ps.setBoolean(3, vehicle.isReadyCrashCar());
				ps.setInt(4, vehicle.getId());
				return ps;
			}
		});
		return vehicle;
	}

	@Override
	public Vehicle insert(Vehicle vehicle) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_VEHICLE, new String[] { "id" });
				ps.setInt(1, vehicle.getDriver().getId());
				ps.setInt(2, vehicle.getModel().getId());
				ps.setBoolean(3, vehicle.isReadyCrashCar());
				return ps;
			}
		}, keyHolder);

		Number key = keyHolder.getKey();
		vehicle.setId(key.intValue());
		return vehicle;
	}

	@Override
	public List<Vehicle> joinGetAll() {
		try {
			List<Vehicle> rs = jdbcTemplate.query(GET_ALL_VEHICLE, new VehicleMapper());
			return rs;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
			return null;
		}
	}

	@Override
	public List<Vehicle> getFiltered(VehicleSerachCriteria criteria) {
		try {
			if (criteria.isEmpty()) {
				getAll();
			}
			if (criteria.getBrand() != null && criteria.getBrand().length() > 0) {
				FIND_CRITERIA_VEHICLE += "AND brand_model=:brand";
			}
			if (criteria.getBrand() != null && criteria.getBrand().length() > 0) {
				FIND_CRITERIA_VEHICLE += "AND type_model=:type";
			}
			if (criteria.getBrand() != null && criteria.getBrand().length() > 0) {
				FIND_CRITERIA_VEHICLE += "AND name_model=:nameModel";
			}
			if (criteria.getBrand() != null && criteria.getBrand().length() > 0) {
				FIND_CRITERIA_VEHICLE += "AND passenger_of_count=:passengerOfCount";
			}
			if (criteria.getBrand() != null && criteria.getBrand().length() > 0) {
				FIND_CRITERIA_VEHICLE += "AND register_number=:registerNumber";
			}
			if (criteria.getBrand() != null && criteria.getBrand().length() > 0) {
				FIND_CRITERIA_VEHICLE += "AND ready_crash_car=:readyCrashCar";
			}
			BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(criteria);
			return namedParameterJdbcTemplate.query(FIND_CRITERIA_VEHICLE, namedParameters, new VehicleMapper());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
			return null;
		}
	}

	@Override
	public List<Vehicle> joinGetAllReadyCar(Boolean ready) {
		try {
			List<Vehicle> rs = jdbcTemplate.query(GET_ALL_READY_VEHICLE, new Object[] { ready }, new VehicleMapper());
			return rs;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
			return null;
		}
	}

	@Override
	public Vehicle getByUser(Users user) {
		try {
			return jdbcTemplate.queryForObject(GET_BY_USER, new Object[] { user.getId() }, new VehicleMapper());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
			return null;
		}
	}
}
