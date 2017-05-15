package com.sav.autobase.dao.db.impl;

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

import com.sav.autobase.dao.api.IVehicleDao;
import com.sav.autobase.dao.api.filter.VehicleSerachCriteria;
import com.sav.autobase.dao.db.cache.SearchVehicleCache;
import com.sav.autobase.dao.db.mapper.VehicleMapper;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;

@Repository
public class VehicleDaoImpl extends GenericDaoImpl<Vehicle> implements IVehicleDao {

	final String FIND_VEHICLE_BY_ID = "SELECT vehicle.id as vehicle_id, vehicle.driver_id, users.first_name, users.last_name, users.login, users.type, vehicle.model_id, vehicle.ready_crash_car, model_vehicle.id, model_vehicle.brand_id, model_vehicle.name_model, model_vehicle.register_number, model_vehicle.type_id, model_vehicle.count_of_passenger, brand_vehicle.brand_name, type_vehicle.type_name FROM vehicle "
			+ "INNER JOIN users ON users.id=vehicle.driver_id "
			+ "INNER JOIN model_vehicle ON model_vehicle.id=vehicle.model_id "
			+ "INNER JOIN brand_vehicle ON brand_vehicle.id=model_vehicle.brand_id  "
			+ "INNER JOIN type_vehicle  ON model_vehicle.type_id=type_vehicle.id WHERE vehicle.id = ?";
	final String GET_ALL_VEHICLE = "SELECT vehicle.id as vehicle_id, vehicle.driver_id, users.first_name, users.last_name, users.login, users.type, vehicle.model_id, vehicle.ready_crash_car, model_vehicle.id, model_vehicle.brand_id, model_vehicle.name_model, model_vehicle.register_number, model_vehicle.type_id, model_vehicle.count_of_passenger, brand_vehicle.brand_name, type_vehicle.type_name FROM vehicle "
			+ "INNER JOIN users ON users.id=vehicle.driver_id "
			+ "INNER JOIN model_vehicle ON model_vehicle.id=vehicle.model_id "
			+ "INNER JOIN brand_vehicle ON brand_vehicle.id=model_vehicle.brand_id  "
			+ "INNER JOIN type_vehicle  ON model_vehicle.type_id=type_vehicle.id ";
	final String GET_ALL_READY_VEHICLE = "SELECT vehicle.id as vehicle_id, vehicle.driver_id, users.first_name, users.last_name,users.login, users.type, vehicle.model_id, vehicle.ready_crash_car, model_vehicle.id, model_vehicle.brand_id, model_vehicle.name_model, model_vehicle.register_number, model_vehicle.type_id, model_vehicle.count_of_passenger, brand_vehicle.brand_name, type_vehicle.type_name FROM vehicle "
			+ "INNER JOIN users ON users.id=vehicle.driver_id "
			+ "INNER JOIN model_vehicle ON model_vehicle.id=vehicle.model_id "
			+ "INNER JOIN brand_vehicle ON brand_vehicle.id=model_vehicle.brand_id  "
			+ "INNER JOIN type_vehicle  ON model_vehicle.type_id=type_vehicle.id WHERE ready_crash_car = ?";
	final String GET_BY_USER = "SELECT vehicle.id as vehicle_id, vehicle.driver_id, users.first_name, users.last_name, users.login, users.type, vehicle.model_id, vehicle.ready_crash_car, model_vehicle.id, model_vehicle.brand_id, model_vehicle.name_model, model_vehicle.register_number, model_vehicle.type_id, model_vehicle.count_of_passenger, brand_vehicle.brand_name, type_vehicle.type_name FROM vehicle "
			+ "INNER JOIN users ON users.id=vehicle.driver_id "
			+ "INNER JOIN model_vehicle ON model_vehicle.id=vehicle.model_id "
			+ "INNER JOIN brand_vehicle ON brand_vehicle.id=model_vehicle.brand_id  "
			+ "INNER JOIN type_vehicle  ON model_vehicle.type_id=type_vehicle.id " + "WHERE users.id = ?";
	String FIND_CRITERIA_VEHICLE = "SELECT vehicle.id as vehicle_id, vehicle.driver_id, users.first_name, users.last_name, users.login, users.type, vehicle.model_id, vehicle.ready_crash_car, model_vehicle.id, model_vehicle.brand_id, model_vehicle.name_model, model_vehicle.register_number, model_vehicle.type_id, model_vehicle.count_of_passenger, brand_vehicle.brand_name, type_vehicle.type_name FROM vehicle "
			+ "INNER JOIN users ON users.id=vehicle.driver_id "
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

	@Inject
	private SearchVehicleCache searchCache;

	@Override
	protected String getTableName() {
		return "vehicle";
	}

	@Override
	public Vehicle joinGetById(Integer id) {
		try {
			return jdbcTemplate.queryForObject(FIND_VEHICLE_BY_ID, new Object[] { id }, new VehicleMapper());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Exception thrown! ", e);
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
			LOGGER.error("Exception thrown! ", e);
			return null;
		}
	}

	@Override
	public List<Vehicle> getFiltered(VehicleSerachCriteria criteria) {

		String query = FIND_CRITERIA_VEHICLE;
		List<Vehicle> searchFromCache = searchCache.get(new Integer(criteria.hashCode()).toString());

		if (searchFromCache != null) {
			LOGGER.info("from cahce search by Filter");
			return searchFromCache;
		}
		try {
			if (criteria.isEmpty()) {
				getAll();
			}
			if (criteria.getBrand() != null && criteria.getBrand().length() > 0) {
				query += " AND brand_model=:brand";
			}
			if (criteria.getType() != null && criteria.getType().length() > 0) {
				query += " AND type_model=:type";
			}
			if (criteria.getNameModel() != null && criteria.getNameModel().length() > 0) {
				query += " AND name_model=:nameModel";
			}
			if (criteria.getCountOfPassenger() != null) {
				query += " AND count_of_passenger>=:countOfPassenger";
			}
			if (criteria.getRegisterNumber() != null && criteria.getRegisterNumber().length() > 0) {
				query += " AND register_number=:registerNumber";
			}
			if (criteria.getReadyCrashCar() != null) {
				query += " AND ready_crash_car=:readyCrashCar";
			}
			BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(criteria);
			List<Vehicle> vehicle = namedParameterJdbcTemplate.query(query, namedParameters, new VehicleMapper());
			searchCache.set(new Integer(criteria.hashCode()).toString(), vehicle);
			return vehicle;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Exception thrown! ", e);
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
			LOGGER.error("Exception thrown! ", e);
			return null;
		}
	}
}
