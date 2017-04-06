package com.sav.autobase.dao.impl.db;

import java.util.List;

import com.sav.autobase.datamodel.ModelVehicle;

public interface IModelVehicleDao {
	
	ModelVehicle getById(Integer id);
	
	ModelVehicle insert(ModelVehicle model);
	
	ModelVehicle update(ModelVehicle model);
	
	void delete(Integer id);
		
	List<ModelVehicle> getAll();
	
}
