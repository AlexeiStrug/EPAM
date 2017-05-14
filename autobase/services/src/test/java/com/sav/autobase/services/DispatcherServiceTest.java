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

import com.sav.autobase.dao.api.filter.VehicleSerachCriteria;
import com.sav.autobase.datamodel.BrandVehicle;
import com.sav.autobase.datamodel.ModelVehicle;
import com.sav.autobase.datamodel.Place;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.TypeVehicle;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;
import com.sav.autobase.services.exception.ServiceException;

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

	private Trip newTrip1;
	private Trip newTrip2;
	private Trip newTrip3;

	@Before
	public void runBeforeMethod() throws ServiceException, ParseException {

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

	}

	@After
	public void runAfterMethod() throws ServiceException {

		LOGGER.debug("^^^DELETED created entites");
		adminService.deleteAll();
	}

	@Test
	public void getRequest() throws ServiceException {

		LOGGER.debug("^^^GET REQUEST BY ID TEST");

		Request requestFromDb = dispatcherService.getRequest(newRequest.getId());

		Assert.notNull(requestFromDb, "Method must get request by ID");
	}

	@Test
	public void getRequestByStatusTest() throws ServiceException {

		LOGGER.debug("^^^GET NOT READY REQUEST FOR DISPATCHER test");

		Request requestFromDb = dispatcherService.getRequestByStatus(newUser);
		LOGGER.info("{}", requestFromDb);

		Assert.notNull(requestFromDb,
				"Method must get request by Status(notReady) and change status on inProcess, and add Dispatcher");
	}

	@Test
	public void modifyRequestTest() throws ServiceException {

		LOGGER.debug("^^^UPDATED REQUEST test");

		Request requestNotReadyFromDb = dispatcherService.getRequestByStatus(newUser);
		requestNotReadyFromDb.setCountOfPassenger(10);
		dispatcherService.modifyRequest(requestNotReadyFromDb);

		Request updatedRequest = dispatcherService.getRequest(requestNotReadyFromDb.getId());

		Assert.notNull(updatedRequest, "Method must be modify and saved request");
	}

	@Test
	public void getTripTest() throws ServiceException {

		LOGGER.debug("^^^GET TRIP test");

		Trip tripFindFromDb = dispatcherService.getTrip(newTrip.getId());
		LOGGER.info("{}", tripFindFromDb);

		Assert.notNull(tripFindFromDb, "Method must be get trip");
	}

	@Test
	public void getAllTripTest() throws ServiceException, ParseException {

		LOGGER.debug("^^^GET ALL TRIP test");

		newRequest1 = entityFactory.createRequest(newUser, newPlace);
		newRequest2 = entityFactory.createRequest(newUser, newPlace);
		newRequest3 = entityFactory.createRequest(newUser, newPlace);

		newVehicle1 = entityFactory.createVehicle(newModel, newUser);
		newVehicle2 = entityFactory.createVehicle(newModel, newUser);
		newVehicle3 = entityFactory.createVehicle(newModel, newUser);

		newTrip1 = entityFactory.createTrip(newVehicle1, newRequest1);
		newTrip2 = entityFactory.createTrip(newVehicle2, newRequest2);
		newTrip3 = entityFactory.createTrip(newVehicle3, newRequest3);

		List<Trip> tripFromDb = dispatcherService.getAllTrip();

		Assert.notNull(tripFromDb, "Method must be created all trip");

	}

	@Test
	public void createTripTest() throws ServiceException {

		LOGGER.debug("^^^CREATE TRIP test");

		Request requestFromDb = dispatcherService.getRequestByStatus(newUser);

		Trip trip = dispatcherService.createTrip(requestFromDb, newVehicle);
		Trip tripFromDb = dispatcherService.getTrip(trip.getId());

		Assert.notNull(tripFromDb, "Method must be created trip");
	}

	@Test
	public void modifyTripTest() throws ServiceException, ParseException {

		LOGGER.debug("^^^UPDATE TRIP TEST");

		Request requestFromDb = dispatcherService.getRequestByStatus(newUser);

		Trip trip = dispatcherService.createTrip(requestFromDb, newVehicle);
		Trip tripFromDb = dispatcherService.getTrip(trip.getId());
		tripFromDb.setEndTrip(true);
		dispatcherService.modifyTrip(tripFromDb);

		Trip updatedTrip = dispatcherService.getTrip(tripFromDb.getId());

		Assert.notNull(updatedTrip, "Method must be saved request");
		Assert.isTrue(tripFromDb.equals(updatedTrip), "Request must be equal");
	}

	@Test
	public void findByCriteria() throws ServiceException {

		LOGGER.debug("^^^FIND VEHICLE BY CRITERIA test");

		newVehicle1 = entityFactory.createVehicle(newModel, newUser);
		newVehicle2 = entityFactory.createVehicle(newModel, newUser);
		newVehicle3 = entityFactory.createVehicle(newModel, newUser);
		LOGGER.info("{},{},{}", newVehicle1,newVehicle2,newVehicle3);

		VehicleSerachCriteria criteria = new VehicleSerachCriteria();
		criteria.setCountOfPassenger(5);
		criteria.setReadyCrashCar(true);

		List<Vehicle> vehicleCriteria = dispatcherService.findVehicleByCriteria(criteria);
		LOGGER.info("{}", vehicleCriteria);

		Assert.notNull(vehicleCriteria, "Method must get vehicle by CRITERIA");
	}

	@Test
	public void deleteTripTest() throws ServiceException {

		LOGGER.debug("^^^DELETED TRIP test");

		Request requestFromDb = dispatcherService.getRequestByStatus(newUser);

		Trip trip = dispatcherService.createTrip(requestFromDb, newVehicle);
		dispatcherService.deleteTrip(trip.getId());
		Trip tripFromDb = dispatcherService.getTrip(trip.getId());

		Assert.isNull(tripFromDb, "Method must not exist");
	}

}
