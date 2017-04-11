package com.sav.autobase.dao.impl.db;

import java.util.List;

import com.sav.autobase.datamodel.BrandVehicle;

public interface IBrandVehicleDao extends IAbstractModelDao<BrandVehicle> {

	List<BrandVehicle> getAll();

}
