package com.sav.autobase.services;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.sav.autobase.datamodel.Place;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.services.exception.DAOException;
import com.sav.autobase.services.exception.ModifyException;

public class ClientServiceTest extends AbstractTest {

	private final static Logger LOGGER = LoggerFactory.getLogger(ClientServiceTest.class);

	@Inject
	private IClientService clientService;

	@Inject
	private IAdminService adminService;

	private Users newUser;
	private Place newPlace;
	private Request request;
	
	/* For Get all and Save all TEST */
	private Request request1;
	private Request request2;
	private Request request3;

	@Before
	public void runBeforeMethod() throws DAOException, ParseException, ModifyException {

		LOGGER.debug("^^^CREATED necessary entities:");
		newUser = entityFactory.createUser();
		adminService.saveUser(newUser);
		LOGGER.debug("{}", newUser);

		newPlace = entityFactory.createPlace();
		adminService.savePlace(newPlace);
		LOGGER.debug("{}", newPlace);

		request = entityFactory.createRequest(newUser, newPlace);
		
		/* For Get all and Save all TEST */
		request1 = entityFactory.createRequest(newUser, newPlace);
		request2 = entityFactory.createRequest(newUser, newPlace);
		request3 = entityFactory.createRequest(newUser, newPlace);
	}

	@After
	public void runAfterMethod() throws DAOException {

		LOGGER.debug("^^^DELETED created entites");
		adminService.deleteAll();
	}

//	@Test
//	public void createTest() throws DAOException, ModifyException {
//
//		LOGGER.debug("^^^CREATED test");
//
//		clientService.createRequest(request);
//		Request requestFromDb = clientService.getRequest(request.getId());
//
//		Assert.notNull(requestFromDb, "request must be saved");
//
//	}
//
//	@Test
//	public void updateTest() throws DAOException, ModifyException, ParseException {
//
//		LOGGER.debug("^^^UPDATED test");
//
//		clientService.createRequest(request1);
//
//		Request requestFromDb = clientService.getRequest(request1.getId());
//		requestFromDb.setCountOfPassenger(10);
//		Date dateStart = new SimpleDateFormat("yyyy-MM-dd").parse("2017-04-20");
//		requestFromDb.setStartDate(new Timestamp(dateStart.getTime()));
//
//		clientService.createRequest(requestFromDb);
//		Request updatedRequest = clientService.getRequest(requestFromDb.getId());
//
//		Assert.notNull(updatedRequest, "request must be saved");
////		Assert.isTrue(requestFromDb.equals(updatedRequest), "Request must be equal");
//
//	}
//
//	@Test
//	public void deleteTest() throws DAOException, ModifyException {
//		LOGGER.debug("^^^DELETED test");
//
//		clientService.createRequest(request);
//
//		clientService.deleteRequest(request.getId());
//		Request requestFromDb = clientService.getRequest(request.getId());
//		Assert.isNull(requestFromDb, "request must not exist");
//
//	}
//
//	@Test
//	public void getTest() throws DAOException, ModifyException {
//
//		LOGGER.debug("^^^GET test");
//
//		clientService.createRequest(request);
//		Request requestFromDb = clientService.getRequest(request.getId());
//		LOGGER.info("{}", requestFromDb);
//
//		Assert.notNull(requestFromDb, "request must get request by ID");
//	}
//
//	@Test
//	public void getAllTest() throws DAOException, ModifyException {
//
//		LOGGER.debug("^^^GET ALL test");
//
//		clientService.createRequest(request1);
//		clientService.createRequest(request2);
//		clientService.createRequest(request3);
//
//		List<Request> requestFromDb = clientService.getAllRequest();
//		Assert.notNull(requestFromDb, "request must getAll requests");
//	}

	@Test
	public void SaveAllRequest() throws ParseException, DAOException, ModifyException {


		clientService.saveAllRequest(request1, request2, request3);

		Integer savedRequestId1 = request1.getId();
		Request requestFromDb1 = clientService.getRequest(savedRequestId1);

		Integer savedRequestId2 = request2.getId();
		Request requestFromDb2 = clientService.getRequest(savedRequestId2);

		Integer savedRequestId3 = request3.getId();
		Request requestFromDb3 = clientService.getRequest(savedRequestId3);

		Assert.notNull(requestFromDb1, "request must be saved");
		Assert.notNull(requestFromDb2, "request must be saved");
		Assert.notNull(requestFromDb3, "request must be saved");

	}

}
