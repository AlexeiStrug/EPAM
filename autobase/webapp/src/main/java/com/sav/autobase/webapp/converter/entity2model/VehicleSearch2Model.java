package com.sav.autobase.webapp.converter.entity2model;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.dao.api.filter.VehicleSerachCriteria;
import com.sav.autobase.webapp.models.VehicleSearchModel;

public class VehicleSearch2Model implements Converter<VehicleSerachCriteria, VehicleSearchModel> {

	public VehicleSearchModel convert(VehicleSerachCriteria criteria) {
		return vehicleSearch2model(criteria);
	}
	
	private VehicleSearchModel vehicleSearch2model(VehicleSerachCriteria criteria) {
		VehicleSearchModel criteriaModel = new VehicleSearchModel();
		criteriaModel.setBrand(criteria.getBrand());
		criteriaModel.setType(criteria.getType());
		criteriaModel.setNameModel(criteria.getNameModel());
		criteriaModel.setCountOfPassenger(criteria.getCountOfPassenger());
		criteriaModel.setRegisterNumber(criteria.getRegisterNumber());
		criteriaModel.setReadyCrashCar(criteria.getReadyCrashCar());
		return criteriaModel;
	}

}
