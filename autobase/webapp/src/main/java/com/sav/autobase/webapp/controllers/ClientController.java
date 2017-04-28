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
import com.sav.autobase.services.IClientService;
import com.sav.autobase.services.exception.DAOException;
import com.sav.autobase.services.exception.ModifyException;
import com.sav.autobase.webapp.models.ClientRequestModel;
import com.sav.autobase.webapp.models.ClientUsersModel;
import com.sav.autobase.webapp.models.IdModel;
import com.sav.autobase.webapp.models.PlaceModel;
import com.sav.autobase.webapp.models.RequestModel;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Inject
	private IClientService clientService;

	@RequestMapping(value = "/{users}", method = RequestMethod.GET)
	public ResponseEntity<List<ClientRequestModel>> getAllRequest(@RequestBody ClientUsersModel userModel) throws DAOException {

		Users user = model2client(userModel);
		List<Request> allRequest = clientService.getAllRequest(user);

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

		requestApplyChanges(request, requestModel);

		clientService.modifyRequest(request);
		
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	public void requestApplyChanges(Request requestFromDb, ClientRequestModel requestModel)
			throws DAOException, ModifyException {

		if (requestModel.getClient() != null) {
			requestFromDb.setClient(model2client(requestModel.getClient()));
		}
		if (requestModel.getStartDate() != null) {
			requestFromDb.setStartDate(requestModel.getStartDate());
		}
		if (requestModel.getEndDate() != null) {
			requestFromDb.setEndDate(requestModel.getEndDate());
		}
		if (requestModel.getPlace() != null) {
			requestFromDb.setPlace(model2place(requestModel.getPlace()));
		}
		if (requestModel.getCountOfPassenger() != null) {
			requestFromDb.setCountOfPassenger(requestModel.getCountOfPassenger());
		}
		if (requestModel.getComment() != null) {
			requestFromDb.setComment(requestModel.getComment());
		}
		if (requestModel.getProcessed() != null) {
			requestFromDb.setProcessed(StatusRequest.valueOf(requestModel.getProcessed()));
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRequest(@PathVariable(value = "id") Integer requestIdParam)
			throws DAOException, ModifyException {
		
		clientService.deleteRequest(requestIdParam);
		
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	/* ---Convert model to entity and entity to model--- */
	private ClientRequestModel request2model(Request request) {
		ClientRequestModel requestModel = new ClientRequestModel();
		requestModel.setId(request.getId());
		requestModel.setClient(client2model(request.getClient()));
		requestModel.setStartDate(request.getStartDate());
		requestModel.setEndDate(request.getEndDate());
		requestModel.setPlace(place2model(request.getPlace()));
		requestModel.setCountOfPassenger(request.getCountOfPassenger());
		requestModel.setComment(request.getComment());
		requestModel.setProcessed(request.getProcessed().name());
		return requestModel;
	}

	private Request model2request(ClientRequestModel requestModel) {
		Request request = new Request();
		request.setId(requestModel.getId());
		request.setClient(model2client(requestModel.getClient()));
		request.setStartDate(requestModel.getStartDate());
		request.setEndDate(requestModel.getEndDate());
		request.setPlace(model2place(requestModel.getPlace()));
		request.setCountOfPassenger(requestModel.getCountOfPassenger());
		request.setComment(requestModel.getComment());
		request.setProcessed(StatusRequest.valueOf(requestModel.getProcessed()));

		return request;
	}

	private ClientUsersModel client2model(Users user) {
		ClientUsersModel userModel = new ClientUsersModel();
		userModel.setId(user.getId());
		userModel.setFirstName(user.getFirstName());
		userModel.setLastName(user.getLastName());
		userModel.setDateBirth(user.getDateBirth());
		userModel.setLogin(user.getLogin());
		userModel.setEmail(user.getEmail());
		return userModel;

	}

	private Users model2client(ClientUsersModel userModel) {
		Users user = new Users();
		user.setId(userModel.getId());
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setDateBirth(userModel.getDateBirth());
		user.setLogin(userModel.getLogin());
		user.setEmail(userModel.getEmail());
		return user;

	}

	private PlaceModel place2model(Place place) {
		PlaceModel placeModel = new PlaceModel();
		placeModel.setId(place.getId());
		placeModel.setPlaceStart(place.getPlaceStart());
		placeModel.setPlaceEnd(place.getPlaceEnd());
		placeModel.setDistance(place.getDistance());
		return placeModel;
	}

	private Place model2place(PlaceModel placeModel) {
		Place place = new Place();
		place.setId(placeModel.getId());
		place.setPlaceStart(placeModel.getPlaceStart());
		place.setPlaceEnd(placeModel.getPlaceEnd());
		place.setDistance(placeModel.getDistance());
		return place;
	}

}
