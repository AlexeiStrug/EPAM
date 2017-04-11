package com.sav.autobase.dao.impl.db;

import java.util.List;

import com.sav.autobase.datamodel.TypeVehicle;

public interface ITypeVehicleDao extends IAbstractModelDao<TypeVehicle> {

	List<TypeVehicle> getAll();

}
