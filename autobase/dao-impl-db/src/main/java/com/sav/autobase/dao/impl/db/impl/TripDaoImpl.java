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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.sav.autobase.dao.impl.db.ITripDao;
import com.sav.autobase.dao.impl.db.mapper.TripMapper;
import com.sav.autobase.datamodel.Trip;

@Repository
public class TripDaoImpl extends AbstractModelDaoImpl<Trip> implements ITripDao {

	final String FIND_TRIP_BY_ID = "SELECT * FROM trip " + "INNER JOIN request ON request.id = trip.request_id "
			+ "INNER JOIN place ON place.id = request.place_id " + "INNER JOIN vehicle ON vehicle.id = trip.vehicle_id "
			+ "INNER JOIN model_vehicle ON model_vehicle.id=vehicle.model_id "
			+ "INNER JOIN brand_vehicle ON brand_vehicle.id=model_vehicle.brand_id "
			+ "INNER JOIN type_vehicle  ON model_vehicle.type_id=type_vehicle.id "
			+ "INNER JOIN users ON users.id = vehicle.driver_id & request.client_id & request.dispatcher_id "
			+ "WHERE trip.id = ?";
	final String GET_ALL_TRIP = "SELECT * FROM trip " + "INNER JOIN request ON request.id = trip.request_id "
			+ "INNER JOIN place ON place.id = request.place_id " + "INNER JOIN vehicle ON vehicle.id = trip.vehicle_id "
			+ "INNER JOIN model_vehicle ON model_vehicle.id=vehicle.model_id "
			+ "INNER JOIN brand_vehicle ON brand_vehicle.id=model_vehicle.brand_id "
			+ "INNER JOIN type_vehicle  ON model_vehicle.type_id=type_vehicle.id "
			+ "INNER JOIN users ON users.id = vehicle.driver_id & request.client_id & request.dispatcher_id ";
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
			LOGGER.debug("Exception thrown! ", e);
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
				ps.setBoolean(3, trip.getEndTrip());
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
				ps.setBoolean(3, trip.getEndTrip());
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
			LOGGER.debug("Exception thrown! ", e);
			return null;
		}
	}

	@Override
	public List<Trip> findByCriteria() throws UnsupportedOperationException {
		LOGGER.debug("Used UnsupportedOperationException");
		throw new UnsupportedOperationException();
	}

}
