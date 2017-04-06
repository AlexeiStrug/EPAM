package com.sav.autobase.dao.impl.db;

import java.util.List;

import com.sav.autobase.datamodel.BrandVehicle;

public interface IBrandVehicleDao {
	
	BrandVehicle getById(Integer id);
	
	BrandVehicle insert(BrandVehicle brand);
	
	List<BrandVehicle> getAll();
	
	void delete(Integer id);

}
