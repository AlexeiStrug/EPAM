package com.sav.autobase.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sav.autobase.dao.impl.db.IModelVehicleDao;
import com.sav.autobase.datamodel.ModelVehicle;
import com.sav.autobase.services.IModelVehicleService;

@Service
public class ModelVehicleService implements IModelVehicleService {

	@Inject
	private IModelVehicleDao modelVehicleDao;

	@Override
	public List<ModelVehicle> getAll() {
		return modelVehicleDao.getAll();
	}

	@Override
	public void save(ModelVehicle model) {
		if (model.getId() == null) {
			System.out.print("Insert new Book");
			modelVehicleDao.insert(model);
		} else {
			System.out.print("Update new Book");
			modelVehicleDao.update(model);
		}

	}

	@Override
	public ModelVehicle getById(Integer id) {
		return modelVehicleDao.getById(id);
	}

}
