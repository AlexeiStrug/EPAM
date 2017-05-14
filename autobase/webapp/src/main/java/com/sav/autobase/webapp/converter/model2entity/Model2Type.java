package com.sav.autobase.webapp.converter.model2entity;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.TypeVehicle;
import com.sav.autobase.webapp.models.TypeVehicleModel;

public class Model2Type implements Converter<TypeVehicleModel, TypeVehicle> {

	public TypeVehicle convert(TypeVehicleModel typeModel) {
		return model2type(typeModel);
	}
	
	private TypeVehicle model2type(TypeVehicleModel typeModel) {
		TypeVehicle type = new TypeVehicle();
		type.setTypeName(typeModel.getTypeName());
		return type;
	}

}
