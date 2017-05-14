package com.sav.autobase.webapp.converter.entity2model;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.ModelVehicle;
import com.sav.autobase.webapp.models.ModelVehicleModel;

public class ModelVehicle2Model implements Converter<ModelVehicle, ModelVehicleModel> {

	public ModelVehicleModel convert(ModelVehicle modelVehicle) {
		return modelVehicle2model(modelVehicle);
	}
	
	private ModelVehicleModel modelVehicle2model(ModelVehicle modelVehicle) {
		ModelVehicleModel modelVehicleModel = new ModelVehicleModel();
		modelVehicleModel.setId(modelVehicle.getId());
		modelVehicleModel.setNameModel(modelVehicle.getNameModel());
		modelVehicleModel.setRegisterNumber(modelVehicle.getRegisterNumber());
		modelVehicleModel.setCountOfPassenger(modelVehicle.getCountOfPassenger());
		modelVehicleModel.setBrand(new Brand2Model().convert(modelVehicle.getBrand()));
		modelVehicleModel.setType(new Type2Model().convert(modelVehicle.getType()));
		return modelVehicleModel;
	}


}
