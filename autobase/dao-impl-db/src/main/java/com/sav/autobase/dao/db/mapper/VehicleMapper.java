package com.sav.autobase.dao.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sav.autobase.datamodel.TypeUsers;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;

public class VehicleMapper implements RowMapper<Vehicle> {

	
	@Override
	public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		
		ModelMapper modelMapper = new ModelMapper();
		
		Users driver = new Users();
		driver.setId(rs.getInt("driver_id"));
		driver.setFirstName(rs.getString("first_name"));
		driver.setLastName(rs.getString("last_name"));
		driver.setType(TypeUsers.valueOf(rs.getString("type")));

		
		Integer id = rs.getInt("vehicle_id");
		Boolean readyCrashCar = rs.getBoolean("ready_crash_car");
		
		Vehicle vehicle = new Vehicle();
		vehicle.setId(id);
		vehicle.setDriver(driver);
		vehicle.setModel(modelMapper.mapRow(rs, rowNum));
		vehicle.setReadyCrashCar(readyCrashCar);

		return vehicle;
	}

}
