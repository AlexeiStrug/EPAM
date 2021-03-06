package com.sav.autobase.services;

import java.text.ParseException;

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
import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.TypeVehicle;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;
import com.sav.autobase.services.exception.ServiceException;

public class DriverServiceTest extends AbstractTest {

	private final static Logger LOGGER = LoggerFactory.getLogger(DriverServiceTest.class);

	@Inject
	private IDriverService driverService;

	@Inject
	private IAdminService adminService;

	private BrandVehicle newBrand;
	private TypeVehicle newType;
	private ModelVehicle newModel;
	private Vehicle newVehicle;
	private Users newUser;
	private Request newRequest;
	private Place newPlace;
	private Trip newTrip;

	@Before
	public void runBeforeMethod() throws ServiceException, ParseException {

		LOGGER.debug("^^^CREATED necessary entities:");

		newUser = entityFactory.createUser();
		adminService.saveUser(newUser);
		LOGGER.debug("{}", newUser);

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

		newPlace = entityFactory.createPlace();
		adminService.savePlace(newPlace);
		LOGGER.debug("{}", newPlace);

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
	public void getTrip() throws ServiceException {
		LOGGER.debug("^^^GET TRIP test");

		Trip tripFromDb = driverService.getTrip(newUser);
		LOGGER.info("{}", tripFromDb);

		Assert.notNull(tripFromDb, "Method must get trip by User");
	}

	@Test
	public void getVehicle() throws ServiceException {
		LOGGER.debug("^^^GET VEHICLE test");

		Vehicle vehicleFromDb = driverService.getVehicle(newUser);
		LOGGER.info("{}", vehicleFromDb);

		Assert.notNull(vehicleFromDb, "Method must get vehicle by User");
	}

	@Test
	public void changeStatusVehicle() throws ServiceException {
		LOGGER.debug("^^^CHANGE STATUS VEHICLE test");

		Vehicle vehicleFromDb = driverService.getVehicle(newUser);
		LOGGER.info("{}", vehicleFromDb);

		driverService.changeStatusVehicle(newUser);

		boolean expected = vehicleFromDb.isReadyCrashCar();
		boolean given = driverService.getVehicle(newUser).isReadyCrashCar();

		Assert.isTrue(given == !expected, "Vehicle must be equal");
	}

	@Test
	public void changeStatusTrip() throws ServiceException {
		LOGGER.debug("^^^CHANGE STATUS TRIP test");

		Trip tripFromDb = driverService.getTrip(newUser);
		LOGGER.info("{}", tripFromDb);

		driverService.changeStatusTrip(newUser);
		boolean expected = tripFromDb.isEndTrip();
		boolean given = driverService.getTrip(newUser).isEndTrip();

		Assert.isTrue(given == !expected, "Trip must be equal");
	}

}
