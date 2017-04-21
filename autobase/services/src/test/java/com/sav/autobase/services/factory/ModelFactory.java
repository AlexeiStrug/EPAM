package com.sav.autobase.services.factory;

import java.util.ArrayList;
import java.util.Random;

import com.sav.autobase.datamodel.BrandVehicle;
import com.sav.autobase.datamodel.ModelVehicle;
import com.sav.autobase.datamodel.TypeVehicle;

public class ModelFactory implements IFactory<ModelVehicle> {

	ModelVehicle model1 = new ModelVehicle();
	ModelVehicle model2 = new ModelVehicle();
	ModelVehicle model3 = new ModelVehicle();

	ArrayList<ModelVehicle> models = new ArrayList<>();

	public ModelFactory(BrandVehicle brand, TypeVehicle type) {
		
		model1.setBrand(brand);
		model1.setNameModel("bla-bla-bla");
		model1.setRegisterNumber("bla-bla-bla");
		model1.setType(type);
		model1.setCountOfPassenger(5);
		
		model2.setBrand(brand);
		model2.setNameModel("bla-bla-bla");
		model2.setRegisterNumber("bla-bla-bla");
		model2.setType(type);
		model2.setCountOfPassenger(6);
		
		model3.setBrand(brand);
		model3.setNameModel("bla-bla-bla");
		model3.setRegisterNumber("bla-bla-bla");
		model3.setType(type);
		model3.setCountOfPassenger(9);
		
		models.add(model1);
		models.add(model2);
		models.add(model3);
	}

	@Override
	public ModelVehicle create() {
		Random r = new Random();
		return models.get(r.nextInt(models.size()));
	}

}
