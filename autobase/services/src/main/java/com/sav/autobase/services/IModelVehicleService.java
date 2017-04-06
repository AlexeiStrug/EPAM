package com.sav.autobase.services;

import java.util.List;

import com.sav.autobase.datamodel.ModelVehicle;

public interface IModelVehicleService {
	
	ModelVehicle getById(Integer id);
	
	List<ModelVehicle> getAll();
	
	void save(ModelVehicle model);
	

}
