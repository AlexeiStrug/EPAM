package com.sav.autobase.webapp.converter.entity2model;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.BrandVehicle;
import com.sav.autobase.webapp.models.BrandVehicleModel;

public class Brand2Model implements Converter<BrandVehicle, BrandVehicleModel> {

	public BrandVehicleModel convert(BrandVehicle brand) {
		return brand2model(brand);
	}
	
	private BrandVehicleModel brand2model(BrandVehicle brand) {
		BrandVehicleModel brandModel = new BrandVehicleModel();
		brandModel.setId(brand.getId());
		brandModel.setBrandName(brand.getBrandName());
		return brandModel;
	}

}
