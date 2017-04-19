package com.sav.autobase.services;

import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.sav.autobase.datamodel.BrandVehicle;
import com.sav.autobase.datamodel.ModelVehicle;
import com.sav.autobase.datamodel.Place;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.StatusRequest;
import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.TypeVehicle;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;
import com.sav.autobase.services.exception.DAOException;

public class DispatcherServiceTest extends AbstractTest {

	private final static Logger LOGGER = LoggerFactory.getLogger(DispatcherServiceTest.class);
	
	@Inject
	private IDispatcherService dispatcherService;
	
	@Inject
	private IAdminService adminService;
	
	private BrandVehicle newBrand;
	private TypeVehicle newType;
	private ModelVehicle newModel;
	private Vehicle newVehicle;
	private Users newUser;
	private Request newRequest;
	private Place newPlace;
	private Trip newTrip;;
	
	/* For Get all TEST */
	private Request newRequest1;
	private Request newRequest2;
	private Request newRequest3;
	
	private Vehicle newVehicle1;
	private Vehicle newVehicle2;
	private Vehicle newVehicle3;
	
	
	@Before
	public void runBeforeMethod() throws DAOException, ParseException {
		LOGGER.debug("^^^CREATED necessary entities:");
		newUser = entityFactory.createUser();
		adminService.saveUser(newUser);
		LOGGER.debug("{}", newUser);

		newPlace = entityFactory.createPlace();
		adminService.savePlace(newPlace);
		LOGGER.debug("{}", newPlace);
		
		newBrand = entityFactory.createBrand();
		adminService.addNewBrand(newBrand);
		LOGGER.debug("{}", newBrand);
		
		newType = entityFactory.createType();
		adminService.addNewType(newType);
		LOGGER.debug("{}", newType);
		
		newModel = entityFactory.createModel(newBrand, newType);
		adminService.saveModel(newModel);
		LOGGER.debug("{}", newModel);
		
		newVehicle = entityFactory.createVehicle(newModel, newUser);
		adminService.saveVehicle(newVehicle);
		LOGGER.debug("{}", newVehicle);

		newRequest = entityFactory.createRequest(newUser, newPlace);
		adminService.saveRequest(newRequest);
		LOGGER.debug("{}", newRequest);
		
		newTrip = entityFactory.createTrip(newVehicle, newRequest);
		adminService.saveTrip(newTrip);
		LOGGER.debug("{}", newTrip);
		
		/* For Get all TEST */
		newRequest1 = entityFactory.createRequest(newUser, newPlace);
		newRequest2 = entityFactory.createRequest(newUser, newPlace);
		newRequest3 = entityFactory.createRequest(newUser, newPlace);
		
		newVehicle1 = entityFactory.createVehicle(newModel, newUser);
		newVehicle2 = entityFactory.createVehicle(newModel, newUser);
		newVehicle3 = entityFactory.createVehicle(newModel, newUser);
		
	}
	@After
	public void runAfterMethod() throws DAOException {
		LOGGER.debug("^^^DELETED created entites");
		adminService.deleteAll();
	}
	
//	@Test
//	public void getRequestTest() throws DAOException {
//		LOGGER.debug("^^^GET REQUEST test");
//		
//		Request requestFromDb = dispatcherService.getRequest();
//		LOGGER.info("{}", requestFromDb);
//
//		Assert.notNull(requestFromDb, "request must get request by Status");
//	}
//	
//	@Test 
//	public void modifyRequestTest() throws DAOException {
//		LOGGER.debug("^^^UPDATED REQUEST test");
//		
//		Request requestFromDb = dispatcherService.getRequest();
//		requestFromDb.setDispatcher(newUser);
//		dispatcherService.modifyRequest(requestFromDb);
//		
//		Assert.notNull(requestFromDb, "request must be modify and saved");
//		
//		
//	}
//	
//	@Test
//	public void getRequestByStatusTest() throws DAOException {
//		LOGGER.debug("^^^GET BY STATUS test");
//		
//		Request requestFindFromDb = dispatcherService.findByStatus(StatusRequest.notReady);
//		LOGGER.info("{}", requestFindFromDb);
//		
//		Assert.notNull(requestFindFromDb, "request must be find");
//	}
//	
//	@Test
//	public void getTripTest() throws DAOException {
//		LOGGER.debug("^^^GET TRIP test");
//		
//		Trip tripFindFromDb = dispatcherService.getTrip(newTrip.getId());
//		LOGGER.info("{}", tripFindFromDb);
//		
//		Assert.notNull(tripFindFromDb, "trip must be get");
//		
//	}
//	@Test
//	public void getAllTripTest() throws DAOException {
//		LOGGER.debug("^^^GET ALL TRIP test");
//		
//		
//		
//	}
//	@Test
//	public void createTripTest() throws DAOException {
//		LOGGER.debug("^^^CREATE TRIP test");
//		
//		Request requestFromDb = dispatcherService.getRequest();
//		requestFromDb.setDispatcher(newUser);
//		dispatcherService.modifyRequest(requestFromDb);
//		
//		dispatcherService.createTrip(requestFromDb, newVehicle);
//		
//		Assert.notNull(tripFromDb, "Trip must be created");
//	}
//	@Test
//	public void deleteTripTest() throws DAOException {
//		LOGGER.debug("^^^DELETED TRIP test");
//		
//		Request requestFromDb = dispatcherService.getRequest();
//		requestFromDb.setDispatcher(newUser);
//		dispatcherService.modifyRequest(requestFromDb);
//		
//		Trip tripFromDb = dispatcherService.createTrip(requestFromDb, newVehicle);
//		dispatcherService.deleteTrip(tripFromDb.getId());
//		
//		Assert.isNull(requestFromDb, "request must not exist");
//		
//		
//	}
 	
	

}
