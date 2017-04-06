package com.sav.autobase.services.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sav.autobase.dao.impl.db.IVehicleDao;
import com.sav.autobase.datamodel.Vehicle;
import com.sav.autobase.services.IVehicleService;

@Service
public class VehicleService implements IVehicleService {

	@Inject
	private IVehicleDao vehicleDao;

	@Override
	public Vehicle getById(Integer id) {
		return vehicleDao.getById(id);
	}

}
