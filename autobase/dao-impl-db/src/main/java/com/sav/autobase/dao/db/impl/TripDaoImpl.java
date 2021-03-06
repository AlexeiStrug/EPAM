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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.sav.autobase.dao.api.ITripDao;
import com.sav.autobase.dao.db.mapper.TripMapper;
import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.Users;

@Repository
public class TripDaoImpl extends GenericDaoImpl<Trip> implements ITripDao {

	final String FIND_TRIP_BY_ID = "SELECT trip.id as trip_id, trip.end_trip, trip.vehicle_id, trip.request_id, request.id, request.dispatcher_id, request.client_id,  vehicle.driver_id, DRIVER.first_name, DRIVER.last_name,DRIVER.login, DRIVER.type, CLIENT.first_name as client_first_name,DISPATCHER.first_name as dispatcher_first_name, CLIENT.last_name as client_last_name, DISPATCHER.email as dispatcher_email,  CLIENT.login as client_login, DISPATCHER.login as dispatcher_login, DISPATCHER.type as dispatcher_type, CLIENT.type as client_type, request.place_id, place.place_start, place.place_end, "
			+ "place.distance, request.start_date, request.end_date, request.count_of_passenger,request.comment, request.processed, vehicle.model_id, vehicle.ready_crash_car, model_vehicle.id, model_vehicle.brand_id, model_vehicle.name_model, model_vehicle.register_number, model_vehicle.type_id, "
			+ "model_vehicle.count_of_passenger, brand_vehicle.brand_name, type_vehicle.type_name FROM trip "
			+ "INNER JOIN request ON request.id = trip.request_id "
			+ "INNER JOIN place ON place.id = request.place_id "
			+ "INNER JOIN vehicle ON vehicle.id = trip.vehicle_id "
			+ "INNER JOIN model_vehicle ON model_vehicle.id=vehicle.model_id "
			+ "INNER JOIN brand_vehicle ON brand_vehicle.id=model_vehicle.brand_id "
			+ "INNER JOIN type_vehicle  ON model_vehicle.type_id=type_vehicle.id "
			+ "JOIN users as DRIVER ON (DRIVER.id = vehicle.driver_id) "
			+ "JOIN users as CLIENT ON (CLIENT.id = request.client_id) "
			+ "JOIN users as DISPATCHER ON (DISPATCHER.id = request.dispatcher_id) "
			+ "WHERE trip.id = ?";
	final String GET_ALL_TRIP = "SELECT trip.id as trip_id, trip.end_trip, trip.vehicle_id, trip.request_id, request.id, request.dispatcher_id, request.client_id,  vehicle.driver_id, DRIVER.first_name, DRIVER.last_name,DRIVER.login, DRIVER.type, CLIENT.first_name as client_first_name,DISPATCHER.first_name as dispatcher_first_name, CLIENT.last_name as client_last_name, DISPATCHER.email as dispatcher_email,  CLIENT.login as client_login, DISPATCHER.login as dispatcher_login, DISPATCHER.type as dispatcher_type, CLIENT.type as client_type, request.place_id,  place.place_start, place.place_end, "
			+ "place.distance, request.start_date, request.end_date, request.count_of_passenger,request.comment, request.processed, vehicle.model_id, vehicle.ready_crash_car, model_vehicle.id, model_vehicle.brand_id, model_vehicle.name_model, model_vehicle.register_number, model_vehicle.type_id, "
			+ "model_vehicle.count_of_passenger, brand_vehicle.brand_name, type_vehicle.type_name FROM trip  "
			+ "INNER JOIN request ON request.id = trip.request_id "
			+ "INNER JOIN place ON place.id = request.place_id "
			+ "INNER JOIN vehicle ON vehicle.id = trip.vehicle_id "
			+ "INNER JOIN model_vehicle ON model_vehicle.id=vehicle.model_id "
			+ "INNER JOIN brand_vehicle ON brand_vehicle.id=model_vehicle.brand_id "
			+ "INNER JOIN type_vehicle  ON model_vehicle.type_id=type_vehicle.id "
			+ "JOIN users as DRIVER ON (DRIVER.id = vehicle.driver_id) "
			+ "JOIN users as CLIENT ON (CLIENT.id = request.client_id) "
			+ "JOIN users as DISPATCHER ON (DISPATCHER.id = request.dispatcher_id) ";
	final String GET_BY_USER = "SELECT trip.id as trip_id, trip.end_trip, trip.vehicle_id, trip.request_id, request.id, request.dispatcher_id, request.client_id,  vehicle.driver_id, DRIVER.first_name, DRIVER.last_name,DRIVER.login, DRIVER.type, CLIENT.first_name as client_first_name,DISPATCHER.first_name as dispatcher_first_name, CLIENT.last_name as client_last_name, DISPATCHER.email as dispatcher_email,  CLIENT.login as client_login, DISPATCHER.login as dispatcher_login, DISPATCHER.type as dispatcher_type, CLIENT.type as client_type, request.place_id, place.place_start, place.place_end, "
			+ "place.distance, request.start_date, request.end_date, request.count_of_passenger,request.comment, request.processed, vehicle.model_id, vehicle.ready_crash_car, model_vehicle.id, model_vehicle.brand_id, model_vehicle.name_model, model_vehicle.register_number, model_vehicle.type_id, "
			+ "model_vehicle.count_of_passenger, brand_vehicle.brand_name, type_vehicle.type_name FROM trip "
			+ "INNER JOIN request ON request.id = trip.request_id "
			+ "INNER JOIN place ON place.id = request.place_id "
			+ "INNER JOIN vehicle ON vehicle.id = trip.vehicle_id "
			+ "INNER JOIN model_vehicle ON model_vehicle.id=vehicle.model_id "
			+ "INNER JOIN brand_vehicle ON brand_vehicle.id=model_vehicle.brand_id "
			+ "INNER JOIN type_vehicle  ON model_vehicle.type_id=type_vehicle.id "
			+ "JOIN users as DRIVER ON (DRIVER.id = vehicle.driver_id) "
			+ "JOIN users as CLIENT ON (CLIENT.id = request.client_id) "
			+ "JOIN users as DISPATCHER ON (DISPATCHER.id = request.dispatcher_id) "
			+ "WHERE DRIVER.id = ? LIMIT 1";
	final String INSERT_TRIP = "INSERT INTO trip (request_id, vehicle_id, end_trip) VALUES(?,?,?)";
	final String UPDATE_TRIP = "UPDATE trip SET request_id = ?, vehicle_id = ?, end_trip = ? where id = ?";

	private final static Logger LOGGER = LoggerFactory.getLogger(TripDaoImpl.class);

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	protected String getTableName() {
		return "trip";
	}

	@Override
	public Trip joinGetById(Integer id) {
		try {
			return jdbcTemplate.queryForObject(FIND_TRIP_BY_ID, new Object[] { id }, new TripMapper());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Exception thrown! ", e);
			return null;
		}
	}

	@Override
	public Trip insert(Trip trip) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_TRIP, new String[] { "id" });
				ps.setInt(1, trip.getRequest().getId());
				ps.setInt(2, trip.getVehicle().getId());
				ps.setBoolean(3, trip.isEndTrip());
				return ps;
			}
		}, keyHolder);

		Number key = keyHolder.getKey();
		trip.setId(key.intValue());
		return trip;
	}

	@Override
	public Trip update(Trip trip) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(UPDATE_TRIP);
				ps.setInt(1, trip.getRequest().getId());
				ps.setInt(2, trip.getVehicle().getId());
				ps.setBoolean(3, trip.isEndTrip());
				ps.setInt(4, trip.getId());
				return ps;
			}
		});
		return trip;
	}

	@Override
	public List<Trip> joinGetAll() {
		try {
			List<Trip> rs = jdbcTemplate.query(GET_ALL_TRIP, new TripMapper());
			return rs;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Exception thrown! ", e);
			return null;
		}
	}

	@Override
	public List<Trip> findByCriteria() throws UnsupportedOperationException {
		LOGGER.debug("Used UnsupportedOperationException");
		throw new UnsupportedOperationException();
	}

	@Override
	public Trip getByUser(Users user) {
		try {
			return jdbcTemplate.queryForObject(GET_BY_USER, new Object[] { user.getId() }, new TripMapper());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Exception thrown! ", e);
			return null;
		}
	}

}
