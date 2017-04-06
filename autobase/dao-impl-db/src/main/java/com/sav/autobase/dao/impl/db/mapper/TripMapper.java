package com.sav.autobase.dao.impl.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sav.autobase.datamodel.Trip;

public class TripMapper implements RowMapper<Trip> {

	@Override
	public Trip mapRow(ResultSet rs, int rowNum) throws SQLException {

		RequestMapper requestMapper = new RequestMapper();
		VehicleMapper vehicleMapper = new VehicleMapper();

		Integer id = rs.getInt("id");
		Boolean endTrip = rs.getBoolean("end_trip");

		Trip trip = new Trip();
		trip.setId(id);
		trip.setRequest(requestMapper.mapRow(rs, rowNum));
		trip.setVehicle(vehicleMapper.mapRow(rs, rowNum));
		trip.setEndTrip(endTrip);
		
		return trip;
	}

}
