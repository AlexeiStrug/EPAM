package com.sav.autobase.dao.impl.db;

import java.util.List;

import com.sav.autobase.datamodel.TypeVehicle;

public interface ITypeVehicleDao {
	
	TypeVehicle getById(Integer id);
	
	TypeVehicle insert(TypeVehicle type);
	
	List<TypeVehicle> getAll();
	
	void delete(Integer id);
	

}
