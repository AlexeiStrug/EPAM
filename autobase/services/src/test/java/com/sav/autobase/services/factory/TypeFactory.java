package com.sav.autobase.services.factory;

import java.util.ArrayList;
import java.util.Random;

import com.sav.autobase.datamodel.TypeVehicle;

public class TypeFactory implements IFactory<TypeVehicle>{
	
	TypeVehicle newType1 = new TypeVehicle();
	TypeVehicle newType2 = new TypeVehicle();
	TypeVehicle newType3 = new TypeVehicle();
	ArrayList<TypeVehicle> types = new ArrayList<>();
	
	public TypeFactory() {
		newType1.setTypeName("cabrio");
		
		newType1.setTypeName("universal");
		
		newType1.setTypeName("pickup");
		
		types.add(newType1);
		types.add(newType1);
		types.add(newType1);

	}

	@Override
	public TypeVehicle create() {
		Random r = new Random();
		return types.get(r.nextInt(types.size()));
	}
	

}
