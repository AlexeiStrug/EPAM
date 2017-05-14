package com.sav.autobase.webapp.converter.model2entity;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.BrandVehicle;
import com.sav.autobase.webapp.models.BrandVehicleModel;

public class Model2Brand implements Converter<BrandVehicleModel, BrandVehicle> {

	public BrandVehicle convert(BrandVehicleModel brandModel) {
		return model2brand(brandModel);
	}
	
	private BrandVehicle model2brand(BrandVehicleModel brandModel) {
		BrandVehicle brand = new BrandVehicle();
		brand.setBrandName(brandModel.getBrandName());
		return brand;
	}
}
