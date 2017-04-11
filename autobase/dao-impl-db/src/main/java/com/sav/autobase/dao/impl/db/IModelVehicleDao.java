package com.sav.autobase.dao.impl.db;

import java.util.List;

import com.sav.autobase.datamodel.ModelVehicle;

public interface IModelVehicleDao extends IGenericDao<ModelVehicle> {

	ModelVehicle joinGetById(Integer id);

	List<ModelVehicle> joinGetAll();

}
