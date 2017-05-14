package com.sav.autobase.webapp.converter.model2entity;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.ModelVehicle;
import com.sav.autobase.webapp.models.ModelVehicleModel;

public class Model2ModelVehicle implements Converter<ModelVehicleModel, ModelVehicle> {

	public ModelVehicle convert(ModelVehicleModel modelVehicleModel) {
		return model2modelVehicle(modelVehicleModel);
	}
	
	private ModelVehicle model2modelVehicle(ModelVehicleModel modelVehicleModel) {
		ModelVehicle modelVehicle = new ModelVehicle();
		modelVehicle.setId(modelVehicleModel.getId());
		modelVehicle.setNameModel(modelVehicleModel.getNameModel());
		modelVehicle.setRegisterNumber(modelVehicleModel.getRegisterNumber());
		modelVehicle.setCountOfPassenger(modelVehicleModel.getCountOfPassenger());
		modelVehicle.setBrand(new Model2Brand().convert(modelVehicleModel.getBrand()));
		modelVehicle.setType(new Model2Type().convert(modelVehicleModel.getType()));
		return modelVehicle;
	}
}
