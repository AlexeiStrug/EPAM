package com.sav.autobase.dao.impl.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.sav.autobase.dao.impl.db.IVehicleDao;
import com.sav.autobase.dao.impl.db.filter.vehicle.IFilterVehicle;
import com.sav.autobase.dao.impl.db.mapper.VehicleMapper;
import com.sav.autobase.datamodel.Vehicle;

@Repository
public class VehicleDaoImpl implements IVehicleDao{
	
	final String FIND_VEHICLE_BY_ID = "SELECT * FROM vehicle "
			+ "INNER JOIN users ON users.id=vehicle.driver_id "
			+ "INNER JOIN model_vehicle ON model_vehicle.id=vehicle.model_id "
			+ "INNER JOIN brand_vehicle ON brand_vehicle.id=model_vehicle.brand_id  "
			+ "INNER JOIN type_vehicle  ON model_vehicle.type_id=type_vehicle.id WHERE vehicle.id = ?";
	final String GET_ALL_VEHICLE = "SELECT * FROM vehicle "
			+ "INNER JOIN users ON users.id=vehicle.driver_id "
			+ "INNER JOIN model_vehicle ON model_vehicle.id=vehicle.model_id "
			+ "INNER JOIN brand_vehicle ON brand_vehicle.id=model_vehicle.brand_id  "
			+ "INNER JOIN type_vehicle  ON model_vehicle.type_id=type_vehicle.id ";
	final String GET_FILTERED_VEHICLE = "SELECT * FROM vehicle "
			+ "INNER JOIN users ON users.id=vehicle.driver_id "
			+ "INNER JOIN model_vehicle ON model_vehicle.id=vehicle.model_id "
			+ "INNER JOIN brand_vehicle ON brand_vehicle.id=model_vehicle.brand_id  "
			+ "INNER JOIN type_vehicle  ON model_vehicle.type_id=type_vehicle.id WHERE ";
	final String UPDATE_VEHICLE = "UPDATE vehicle SET driver_id = ?, model_id = ?, ready_crash_car = ? WHERE vehicle.id = ? ";
	final String INSERT_VEHICLE = "INSERT INTO vehicle (driver_id, model_id, ready_crash_car) VALUES(?,?,?)";
	final String DELETE_VEHICLE = "DELETE FROM vehicle WHERE id = ? ";

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public Vehicle getById(Integer id) {
		try {
			return jdbcTemplate.queryForObject(FIND_VEHICLE_BY_ID, new Object[] { id },
					new VehicleMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	@Override
	public Vehicle update(Vehicle vehicle) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(UPDATE_VEHICLE);
				ps.setObject(1, vehicle.getDriver().getId());
				ps.setObject(2, vehicle.getModel().getId());
				ps.setBoolean(3, vehicle.getReadyCrashCar());
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
				ps.setObject(1, vehicle.getDriver().getId());
				ps.setObject(2, vehicle.getModel().getId());
				ps.setBoolean(3, vehicle.getReadyCrashCar());
				return ps;
			}
		}, keyHolder);

		Number key = keyHolder.getKey();
		vehicle.setId(key.intValue());
		return vehicle;
	}
	
	@Override
	public void delete(Integer id) {
		jdbcTemplate.update(DELETE_VEHICLE + id);

	}
	
	@Override
	public List<Vehicle> getAll() {
		try {
			List<Vehicle> rs = jdbcTemplate.query(GET_ALL_VEHICLE, new VehicleMapper());
			return rs;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Vehicle> getFiltered(List<IFilterVehicle> filters) {
		try {
			List<Vehicle> rs = jdbcTemplate.query(
					GET_FILTERED_VEHICLE
							+ filters.stream().map(f -> f.generateCondition()).collect(Collectors.joining(" AND ")),
					new VehicleMapper());
			return rs;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
