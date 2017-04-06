package com.sav.autobase.dao.impl.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sav.autobase.datamodel.BrandVehicle;
import com.sav.autobase.datamodel.ModelVehicle;
import com.sav.autobase.datamodel.TypeVehicle;

public class ModelMapper implements RowMapper<ModelVehicle> {

	@Override
	public ModelVehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		BrandVehicle brand = new BrandVehicle();
		brand.setBrandName(rs.getString("brand_name"));
		
		TypeVehicle type = new TypeVehicle();
		type.setTypeName(rs.getString("type_name"));
		
		Integer id = rs.getInt("id");
		String nameModel = rs.getString("name_model");
		String registerNumber = rs.getString("register_number");
		Integer countOfPassenger = rs.getInt("count_of_passenger");
		
		ModelVehicle model = new ModelVehicle();
		model.setId(id);
		model.setBrand(brand);
		model.setNameModel(nameModel);
		model.setRegisterNumber(registerNumber);
		model.setType(type);
		model.setCountOfPassenger(countOfPassenger);
		
		return model;
	}
	

}
