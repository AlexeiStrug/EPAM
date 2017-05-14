package com.sav.autobase.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.StatusRequest;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.services.IClientService;
import com.sav.autobase.services.exception.ModifyException;
import com.sav.autobase.services.exception.ServiceException;
import com.sav.autobase.webapp.converter.entity2model.ClientRequest2Model;
import com.sav.autobase.webapp.converter.model2entity.Model2Client;
import com.sav.autobase.webapp.converter.model2entity.Model2ClientRequest;
import com.sav.autobase.webapp.converter.model2entity.Model2Place;
import com.sav.autobase.webapp.filter.BasicAuthFilter;
import com.sav.autobase.webapp.models.ClientRequestModel;
import com.sav.autobase.webapp.models.IdModel;

/**
 * This class ClientController includes a set of methods of action necessary for
 * the user client
 * 
 * @author AlexStrug
 * @version 1
 *
 */
@RestController
@RequestMapping("/client")
public class ClientController {

	@Inject
	private IClientService clientService;

	/**
	 * The method returns all request by authorization user(client)
	 * 
	 * @param httpServletRequest
	 *            - transferring the user(client) from BasicAuthFilter
	 * @return HttpStatus.OK if successfully get all and get all the requests
	 *         <br>
	 *         HttpStatus.NO_CONTENT if error with get all requests <br>
	 */
	@RequestMapping(value = "/allRequest", method = RequestMethod.GET)
	public ResponseEntity<List<ClientRequestModel>> getAllRequest(HttpServletRequest httpServletRequest) {

		Users user = (Users) httpServletRequest.getAttribute(BasicAuthFilter.userAttribute);

		List<Request> allRequest;
		try {
			allRequest = clientService.getAllRequest(user);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (allRequest == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		List<ClientRequestModel> convertRequest = new ArrayList<>();
		for (Request request : allRequest) {
			convertRequest.add(new ClientRequest2Model().convert(request));
		}

		return new ResponseEntity<List<ClientRequestModel>>(convertRequest, HttpStatus.OK);
	}

	/**
	 * The method returns by "ID" request
	 * 
	 * @param requestIdParam
	 *            - transferring by "ID" for get by "ID" request
	 * @return HttpStatus.OK if successfully get by "ID" and get the request
	 *         <br>
	 *         HttpStatus.NO_CONTENT if error with get request by "ID" <br>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdRequest(@PathVariable(value = "id") Integer requestIdParam) {

		if (requestIdParam == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		Request request;
		try {
			request = clientService.getRequest(requestIdParam);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		ClientRequestModel requestModel = new ClientRequest2Model().convert(request);

		return new ResponseEntity<ClientRequestModel>(requestModel, HttpStatus.OK);
	}

	/**
	 * The method returns the request "ID" who successfully passed created
	 * 
	 * @param requestModel
	 *            - transferring the new request for createRequest
	 * @return HttpStatus.CREATED if request successfully created and "ID" the
	 *         request <br>
	 *         HttpStatus.BAD_REQUEST if error created new request
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> createRequest(@RequestBody ClientRequestModel requestModel) {

		if (requestModel == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Request request = new Model2ClientRequest().convert(requestModel);
		try {
			clientService.createRequest(request);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (ModifyException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<IdModel>(new IdModel(request.getId()), HttpStatus.CREATED);
	}

	/**
	 * The method returns HttpStatus if the request successfully passed updated
	 * 
	 * @param requestModel
	 *            - transferring the new request for update request
	 * @param requestIdParam
	 *            - transferring the request by "ID" who need updating
	 * @return HttpStatus.ACCEPTED if request successfully updated and "ID" the
	 *         request <br>
	 *         HttpStatus.NO_CONTENT if error updated new request
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> updateRequest(@RequestBody ClientRequestModel requestModel,
			@PathVariable(value = "id") Integer requestIdParam) {

		if (requestIdParam == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		Request request;
		try {
			request = clientService.getRequest(requestIdParam);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		requestApplyChanges(request, requestModel);

		try {
			clientService.modifyRequest(request);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (ModifyException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<IdModel>(HttpStatus.ACCEPTED);
	}

	/**
	 * The method is used in updateRequest method and checks what parameters
	 * came to update
	 * 
	 * @param requestFromDb
	 *            - transferring the request for update request from Database
	 * @param requestModel
	 *            - transferring the request for update request http request
	 */
	public void requestApplyChanges(Request requestFromDb, ClientRequestModel requestModel) {

		if (requestModel.getClient() != null) {
			requestFromDb.setClient(new Model2Client().convert(requestModel.getClient()));
		}
		if (requestModel.getStartDate() != null) {
			requestFromDb.setStartDate(requestModel.getStartDate());
		}
		if (requestModel.getEndDate() != null) {
			requestFromDb.setEndDate(requestModel.getEndDate());
		}
		if (requestModel.getPlace() != null) {
			requestFromDb.setPlace(new Model2Place().convert(requestModel.getPlace()));
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

	/**
	 * The method returns HttpStatus if request successfully deleted
	 * 
	 * @param requestIdParam
	 *            - transferring by "ID" for delete by "ID" request
	 * @return HttpStatus.OK if request successfully deleted <br>
	 *         HttpStatus.NO_CONTENT if error deleted request
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRequest(@PathVariable(value = "id") Integer requestIdParam) {

		if (requestIdParam == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		try {
			clientService.deleteRequest(requestIdParam);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (ModifyException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

}
