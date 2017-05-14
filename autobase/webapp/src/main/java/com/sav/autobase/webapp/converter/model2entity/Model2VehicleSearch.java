package com.sav.autobase.webapp.converter.model2entity;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.dao.api.filter.VehicleSerachCriteria;
import com.sav.autobase.webapp.models.VehicleSearchModel;

public class Model2VehicleSearch implements Converter<VehicleSearchModel, VehicleSerachCriteria> {

	public VehicleSerachCriteria convert(VehicleSearchModel criteriaModel) {
		return model2vehicleSearch(criteriaModel);
	}
	
	private VehicleSerachCriteria model2vehicleSearch(VehicleSearchModel criteriaModel) {
		VehicleSerachCriteria criteria = new VehicleSerachCriteria();
		criteria.setBrand(criteriaModel.getBrand());
		criteria.setType(criteriaModel.getType());
		criteria.setNameModel(criteriaModel.getNameModel());
		criteria.setCountOfPassenger(criteriaModel.getCountOfPassenger());
		criteria.setRegisterNumber(criteriaModel.getRegisterNumber());
		criteria.setReadyCrashCar(criteriaModel.getReadyCrashCar());
		return criteria;
	}

}
