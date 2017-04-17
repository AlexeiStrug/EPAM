package com.sav.autobase.services;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import com.sav.autobase.datamodel.Place;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.TypeUsers;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.services.exception.DAOException;
import com.sav.autobase.services.exception.ModifyException;

public class ClientServiceTest extends AbstractTest {

	@Inject
	private IClientService clientService;

	Users newestUser = new Users();
	Place newPlace = new Place();
	Request request1 = new Request();
	Request request2 = new Request();
	Request request3 = new Request();

	@Before
	public void runBeforeMethodcreateTest() {

		newestUser.setId(1);
		newestUser.setFirstName("Sasha");
		newestUser.setLastName("SSS");
		newestUser.setLogin("dsfsdf");
		newestUser.setPassword("12345");
		newestUser.setEmail("sdfsdfsd@asda.ru");
		newestUser.setDateBirth(new Timestamp(new Date().getTime()));
		newestUser.setType(TypeUsers.client);

		newPlace.setId(1);
		newPlace.setPlaceStart("Minsk");
		newPlace.setPlaceEnd("Gomel");
		newPlace.setDistance(250);
	}

	@After
	public void runAfterMethodcreateTest() throws DAOException, ModifyException {
		clientService.deleteRequest(request1.getId());
	}

	@Test
	public void createTest() throws DAOException, ParseException, ModifyException {

		request1.setClient(newestUser);
		Date dateStart = new SimpleDateFormat("yyyy-MM-dd").parse("2017-04-14");
		request1.setStartDate(new Timestamp(dateStart.getTime()));
		Date dateEnd = new SimpleDateFormat("yyyy-MM-dd").parse("2017-04-17");
		request1.setEndDate(new Timestamp(dateEnd.getTime()));
		request1.setPlace(newPlace);
		request1.setCountOfPassenger(5);
		clientService.createRequest(request1);

		Integer savedRequestId = request1.getId();
		Request requestFromDb = clientService.getRequest(savedRequestId);

		Assert.notNull(requestFromDb, "request must be saved");

		Assert.notNull(requestFromDb.getProcessed().name(), "created column Processed must not be empty");
		Assert.isTrue(requestFromDb.getProcessed().name().equals(request1.getProcessed().name()),
				"Processed in DB must be equals created Processed in Java");

		Assert.notNull(requestFromDb.getClient(), "created column Client_id must not be empty");
		Assert.notNull(requestFromDb.getPlace(), "created column Client_id must not be empty");

		Assert.isTrue(requestFromDb.getStartDate().equals(request1.getStartDate()), "created must be eq...");
		Assert.isTrue(requestFromDb.getEndDate().equals(request1.getEndDate()), "created must be eq...");

	}

	@Before
	public void runBeforeMethodSaveAllRequest() {

		newestUser.setId(1);
		newestUser.setFirstName("Sasha");
		newestUser.setLastName("SSS");
		newestUser.setLogin("dsfsdf");
		newestUser.setPassword("12345");
		newestUser.setEmail("sdfsdfsd@asda.ru");
		newestUser.setDateBirth(new Timestamp(new Date().getTime()));
		newestUser.setType(TypeUsers.client);

		newPlace.setId(1);
		newPlace.setPlaceStart("Minsk");
		newPlace.setPlaceEnd("Gomel");
		newPlace.setDistance(250);
	}

	@Test
	public void SaveAllRequest() throws ParseException, DAOException, ModifyException {

		request1.setClient(newestUser);
		Date dateStart = new SimpleDateFormat("yyyy-MM-dd").parse("2017-04-14");
		request1.setStartDate(new Timestamp(dateStart.getTime()));
		Date dateEnd = new SimpleDateFormat("yyyy-MM-dd").parse("2017-04-17");
		request1.setEndDate(new Timestamp(dateEnd.getTime()));
		request1.setPlace(newPlace);
		request1.setCountOfPassenger(5);

		request2.setClient(newestUser);
		Date dateStart1 = new SimpleDateFormat("yyyy-MM-dd").parse("2017-04-14");
		request2.setStartDate(new Timestamp(dateStart1.getTime()));
		Date dateEnd1 = new SimpleDateFormat("yyyy-MM-dd").parse("2017-04-17");
		request2.setEndDate(new Timestamp(dateEnd1.getTime()));
		request2.setPlace(newPlace);
		request2.setCountOfPassenger(5);

		request3.setClient(newestUser);
		Date dateStart2 = new SimpleDateFormat("yyyy-MM-dd").parse("2017-04-14");
		request3.setStartDate(new Timestamp(dateStart2.getTime()));
		Date dateEnd2 = new SimpleDateFormat("yyyy-MM-dd").parse("2017-04-17");
		request3.setEndDate(new Timestamp(dateEnd2.getTime()));
		request3.setPlace(newPlace);
		request3.setCountOfPassenger(5);
		
		clientService.saveAllRequest(request1,request2, request3);
		
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
