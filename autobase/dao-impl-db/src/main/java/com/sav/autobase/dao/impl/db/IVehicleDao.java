package com.sav.autobase.dao.impl.db;

import java.util.List;

import com.sav.autobase.dao.impl.db.filters.VehicleSerachCriteria;
import com.sav.autobase.datamodel.Vehicle;

public interface IVehicleDao extends IAbstractModelDao<Vehicle> {

	List<Vehicle> getFiltered(VehicleSerachCriteria criteria);

	List<Vehicle> getAll();

}
