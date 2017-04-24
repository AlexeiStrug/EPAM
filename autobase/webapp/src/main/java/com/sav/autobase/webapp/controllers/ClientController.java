package com.sav.autobase.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sav.autobase.datamodel.Place;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.StatusRequest;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.services.exception.DAOException;
import com.sav.autobase.services.exception.ModifyException;
import com.sav.autobase.services.impl.ClientService;
import com.sav.autobase.webapp.models.ClientRequestModel;
import com.sav.autobase.webapp.models.ClientUsersModel;
import com.sav.autobase.webapp.models.PlaceModel;
import com.sav.autobase.webapp.models.IdModel;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Inject
	private ClientService clientService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllRequest() throws DAOException {

		List<Request> allRequest;
		allRequest = clientService.getAllRequest();

		List<ClientRequestModel> convertRequest = new ArrayList<>();
		for (Request request : allRequest) {
			convertRequest.add(request2model(request));
		}

		return new ResponseEntity<List<ClientRequestModel>>(convertRequest, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdRequest(@PathVariable(value = "id") Integer requestIdParam) throws DAOException {

		Request request = clientService.getRequest(requestIdParam);
		ClientRequestModel requestModel = request2model(request);
		return new ResponseEntity<ClientRequestModel>(requestModel, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createRequest(@RequestBody ClientRequestModel requestModel)
			throws DAOException, ModifyException {

		Request request = model2request(requestModel);
		clientService.createRequest(request);
		return new ResponseEntity<IdModel>(new IdModel(request.getId()), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateRequest(@RequestBody ClientRequestModel requestModel,
			@PathVariable(value = "id") Integer requestIdParam) throws DAOException, ModifyException {

		Request request = clientService.getRequest(requestIdParam);
		request.setClient(model2user(requestModel.getClient()));
		request.setStartDate(requestModel.getStartDate());
		request.setEndDate(requestModel.getEndDate());
		request.setPlace(model2place(requestModel.getPlace()));
		request.setCountOfPassenger(requestModel.getCountOfPassenger());
		request.setProcessed(StatusRequest.valueOf(requestModel.getProcessed()));

		clientService.modifyRequest(request);
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRequest(@PathVariable(value = "id") Integer requestIdParam) throws DAOException {
		clientService.deleteRequest(requestIdParam);
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	/* ---Convert model to entity--- */
	private ClientRequestModel request2model(Request request) {
		ClientRequestModel requestModel = new ClientRequestModel();
		requestModel.setClient(user2model(request.getClient()));
		requestModel.setStartDate(request.getStartDate());
		requestModel.setEndDate(request.getEndDate());
		requestModel.setPlace(place2model(request.getPlace()));
		requestModel.setCountOfPassenger(request.getCountOfPassenger());
		requestModel.setProcessed(request.getProcessed().name());
		return requestModel;
	}

	private Request model2request(ClientRequestModel requestModel) {
		Request request = new Request();
		request.setClient(model2user(requestModel.getClient()));
		request.setStartDate(requestModel.getStartDate());
		request.setEndDate(requestModel.getEndDate());
		request.setPlace(model2place(requestModel.getPlace()));
		request.setCountOfPassenger(requestModel.getCountOfPassenger());
		request.setProcessed(StatusRequest.valueOf(requestModel.getProcessed()));

		return request;
	}

	private ClientUsersModel user2model(Users user) {
		ClientUsersModel userModel = new ClientUsersModel();
		userModel.setFirstName(userModel.getFirstName());
		userModel.setLastName(userModel.getLastName());
		userModel.setDateBirth(userModel.getDateBirth());
		userModel.setLogin(userModel.getLogin());
		userModel.setEmail(userModel.getEmail());
		return userModel;

	}

	private Users model2user(ClientUsersModel userModel) {
		Users user = new Users();
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setDateBirth(userModel.getDateBirth());
		user.setLogin(userModel.getLogin());
		user.setEmail(userModel.getEmail());
		return user;

	}

	private PlaceModel place2model(Place place) {
		PlaceModel placeModel = new PlaceModel();
		placeModel.setPlaceStart(place.getPlaceStart());
		placeModel.setPlaceEnd(place.getPlaceEnd());
		placeModel.setDistance(place.getDistance());
		return placeModel;
	}

	private Place model2place(PlaceModel placeModel) {
		Place place = new Place();
		place.setPlaceStart(placeModel.getPlaceStart());
		place.setPlaceEnd(placeModel.getPlaceEnd());
		place.setDistance(placeModel.getDistance());
		return place;
	}

}