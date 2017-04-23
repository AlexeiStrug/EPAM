package com.sav.autobase.dao.api;

import java.util.List;

import com.sav.autobase.dao.api.filter.VehicleSerachCriteria;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;

public interface IVehicleDao extends IGenericDao<Vehicle> {

	List<Vehicle> getFiltered(VehicleSerachCriteria criteria);

	Vehicle joinGetById(Integer id);
	
	Vehicle getByUser(Users user);

	List<Vehicle> joinGetAll();
	
	List<Vehicle> joinGetAllReadyCar(Boolean ready);

}
