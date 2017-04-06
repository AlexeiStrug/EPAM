package com.sav.autobase.dao.impl.db;

import java.util.List;

import com.sav.autobase.dao.impl.db.filter.vehicle.IFilterVehicle;
import com.sav.autobase.datamodel.Vehicle;

public interface IVehicleDao {

	Vehicle getById(Integer id);
	
	Vehicle insert(Vehicle vehicle);
	
	Vehicle update(Vehicle vehicle);
	
	void delete(Integer id);

	List<Vehicle> getFiltered(List<IFilterVehicle> filters);

	List<Vehicle> getAll();

}
