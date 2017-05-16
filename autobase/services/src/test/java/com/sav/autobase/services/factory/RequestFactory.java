package com.sav.autobase.services.factory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.sav.autobase.datamodel.Place;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.StatusRequest;
import com.sav.autobase.datamodel.Users;

public class RequestFactory implements IFactory<Request> {

	Request newRequest = new Request();

	ArrayList<Request> requests = new ArrayList<>();

	public RequestFactory(Users user, Place place) throws ParseException {
		newRequest.setClient(user);
		Date dateStart = new SimpleDateFormat("yyyy-MM-dd").parse("2017-04-14");
		newRequest.setStartDate(new Timestamp(dateStart.getTime()));
		Date dateEnd = new SimpleDateFormat("yyyy-MM-dd").parse("2017-04-17");
		newRequest.setEndDate(new Timestamp(dateEnd.getTime()));
		newRequest.setPlace(place);
		newRequest.setCountOfPassenger(5);
		newRequest.setComment("I want audi");
		newRequest.setDispatcher(user);
		newRequest.setProcessed(StatusRequest.notReady);

	}

	@Override
	public Request create() {
		return newRequest;
	}

}
