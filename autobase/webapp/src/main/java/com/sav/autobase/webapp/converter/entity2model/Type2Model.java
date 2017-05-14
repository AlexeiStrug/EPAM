package com.sav.autobase.webapp.converter.entity2model;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.TypeVehicle;
import com.sav.autobase.webapp.models.TypeVehicleModel;

public class Type2Model implements Converter<TypeVehicle, TypeVehicleModel> {

	public TypeVehicleModel convert(TypeVehicle type) {
		return type2model(type);
	}
	
	private TypeVehicleModel type2model(TypeVehicle type) {
		TypeVehicleModel typeModel = new TypeVehicleModel();
		typeModel.setTypeName(type.getTypeName());
		return typeModel;
	}

}
