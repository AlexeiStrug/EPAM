package com.sav.autobase.services.factory;

import java.util.ArrayList;
import java.util.Random;

import com.sav.autobase.datamodel.BrandVehicle;

public class BrandFactory implements IFactory<BrandVehicle>{
	
	BrandVehicle newBrand1 = new BrandVehicle();
	BrandVehicle newBrand2 = new BrandVehicle();
	BrandVehicle newBrand3 = new BrandVehicle();
	ArrayList<BrandVehicle> brands = new ArrayList<>();
	
	public BrandFactory() {
		newBrand1.setBrandName("audi");
		
		newBrand2.setBrandName("bmw");
		
		newBrand3.setBrandName("vw");
		
		brands.add(newBrand1);
		brands.add(newBrand2);
		brands.add(newBrand3);

	}

	@Override
	public BrandVehicle create() {
		Random r = new Random();
		return brands.get(r.nextInt(brands.size()));
	}
	

}
