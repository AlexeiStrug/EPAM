package com.sav.autobase.dao.impl.db;

import java.util.List;

import com.sav.autobase.datamodel.ModelVehicle;

public interface IModelVehicleDao extends IAbstractModelDao<ModelVehicle> {

	List<ModelVehicle> getAll();

}
