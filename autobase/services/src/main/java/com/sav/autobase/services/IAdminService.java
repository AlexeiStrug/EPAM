package com.sav.autobase.services;



import org.springframework.transaction.annotation.Transactional;

import com.sav.autobase.datamodel.BrandVehicle;
import com.sav.autobase.datamodel.ModelVehicle;
import com.sav.autobase.datamodel.Place;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.TypeVehicle;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;

public interface IAdminService {
	
	@Transactional
	Vehicle addNewVehicle(Vehicle vehicle);
	
	@Transactional
	Vehicle modifyVehicle(Vehicle vehicle);
	
	@Transactional
	Vehicle deleteVehicle(Vehicle vehicle);
	
	@Transactional
	ModelVehicle addNewModel(ModelVehicle model);
	
	@Transactional
	ModelVehicle modifyModel(ModelVehicle model);
	
	@Transactional
	ModelVehicle deleteModel(ModelVehicle model);
	
	@Transactional
	BrandVehicle addNewBrand(BrandVehicle brand);
	
	@Transactional
	BrandVehicle deleteBrand(BrandVehicle brand);
	
	@Transactional
	TypeVehicle addNewType(TypeVehicle type);
	
	@Transactional
	TypeVehicle deleteType(TypeVehicle type);

	@Transactional
	Users addNewUser(Users user);
	
	@Transactional
	Users modifyUser(Users user);
	
	@Transactional
	Users deleteUser(Users user);
	
	@Transactional
	Place addNewPlace(Place place);
	
	@Transactional 
	Place modifyPlace(Place place);
	
	@Transactional 
	Place deletePlace(Place place);
	
	@Transactional
	Trip deleteReadyTrip(Trip trip);
	
	@Transactional
	Request deleteReadyRequest(Request request);
	

}
