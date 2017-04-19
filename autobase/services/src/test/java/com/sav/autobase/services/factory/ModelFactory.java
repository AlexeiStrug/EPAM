package com.sav.autobase.services.factory;

import java.util.ArrayList;

import com.sav.autobase.datamodel.BrandVehicle;
import com.sav.autobase.datamodel.ModelVehicle;
import com.sav.autobase.datamodel.TypeVehicle;

public class ModelFactory implements IFactory<ModelVehicle> {

	ModelVehicle model = new ModelVehicle();

	ArrayList<ModelVehicle> users = new ArrayList<>();

	public ModelFactory(BrandVehicle brand, TypeVehicle type) {
		model.setBrand(brand);
		model.setNameModel("bla-bla-bla");
		model.setRegisterNumber("bla-bla-bla");
		model.setType(type);
		model.setCountOfPassenger(5);
	}

	@Override
	public ModelVehicle create() {
		return model;
	}

}
