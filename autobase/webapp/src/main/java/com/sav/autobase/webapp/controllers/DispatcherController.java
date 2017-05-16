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

import com.sav.autobase.dao.api.filter.VehicleSerachCriteria;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.StatusRequest;
import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;
import com.sav.autobase.services.IDispatcherService;
import com.sav.autobase.services.exception.ServiceException;
import com.sav.autobase.webapp.converter.entity2model.Request2Model;
import com.sav.autobase.webapp.converter.entity2model.Trip2Model;
import com.sav.autobase.webapp.converter.entity2model.Vehicle2Model;
import com.sav.autobase.webapp.converter.model2entity.Model2Client;
import com.sav.autobase.webapp.converter.model2entity.Model2Place;
import com.sav.autobase.webapp.converter.model2entity.Model2Request;
import com.sav.autobase.webapp.converter.model2entity.Model2Vehicle;
import com.sav.autobase.webapp.converter.model2entity.Model2VehicleSearch;
import com.sav.autobase.webapp.filter.BasicAuthFilter;
import com.sav.autobase.webapp.models.CreateTripModel;
import com.sav.autobase.webapp.models.IdModel;
import com.sav.autobase.webapp.models.RequestModel;
import com.sav.autobase.webapp.models.TripModel;
import com.sav.autobase.webapp.models.VehicleModel;
import com.sav.autobase.webapp.models.VehicleSearchModel;

/**
 * This class DispatcherController includes a set of methods of action necessary
 * for the user dispatcher
 * 
 * @author AlexStrug
 * @version 1
 *
 */
@RestController
@RequestMapping("/dispatcher")
public class DispatcherController {

	@Inject
	private IDispatcherService dispatcherService;

	/**
	 * The method return by "ID" request
	 * 
	 * @param requestIdParam
	 *            - transferring by "ID" for get by "ID" request
	 * @return HttpStatus.OK if successfully get by "ID" and get the request
	 *         <br>
	 *         HttpStatus.NO_CONTENT if error with get request by "ID" <br>
	 */
	@RequestMapping(value = "/requestGet/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdRequest(@PathVariable(value = "id") Integer requestIdParam) {

		if (requestIdParam == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		Request request;
		try {
			request = dispatcherService.getRequest(requestIdParam);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (request == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		RequestModel requestModel = new Request2Model().convert(request);

		return new ResponseEntity<RequestModel>(requestModel, HttpStatus.OK);
	}

	/**
	 * The method return by "status notReady" request
	 * 
	 * @param httpServletRequest
	 *            - transferring the user(dispatcher) from BasicAuthFilter
	 * @return HttpStatus.OK if successfully get by "status notReady" and get
	 *         the request <br>
	 *         HttpStatus.NO_CONTENT if error with get by "status notReady"
	 *         requests <br>
	 */
	@RequestMapping(value = "/request", method = RequestMethod.GET)
	public ResponseEntity<?> getRequestByStatus(HttpServletRequest httpServletRequest) {

		Users user = (Users) httpServletRequest.getAttribute(BasicAuthFilter.userAttribute);

		Request request;
		try {
			request = dispatcherService.getRequestByStatus(user);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (request == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		RequestModel requestModel = new Request2Model().convert(request);

		return new ResponseEntity<RequestModel>(requestModel, HttpStatus.OK);
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
	@RequestMapping(value = "/request/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> updateRequest(@RequestBody RequestModel requestModel,
			@PathVariable(value = "id") Integer requestIdParam) {

		if (requestIdParam == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		Request request;
		try {
			request = dispatcherService.getRequest(requestIdParam);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		requestApplyChanges(request, requestModel);

		try {
			dispatcherService.modifyRequest(request);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	/**
	 * The method is used in updateRequest method and checks what parameters
	 * came to update
	 * 
	 * @param requestFromDb
	 *            - transferring the Request for update request from Database
	 * @param requestModel
	 *            transferring the RequestModel for update request from http
	 *            request
	 */
	public void requestApplyChanges(Request requestFromDb, RequestModel requestModel) {

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
	 * The method return by "ID" trip
	 * 
	 * @param tripIdParam
	 *            - transferring by "ID" for get by "ID" trip
	 * @return HttpStatus.OK if successfully get by "ID" and get the trip <br>
	 *         HttpStatus.NO_CONTENT if error with get trip by "ID" <br>
	 */
	@RequestMapping(value = "/trip/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdTrip(@PathVariable(value = "id") Integer tripIdParam) {

		if (tripIdParam == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		Trip trip;
		try {
			trip = dispatcherService.getTrip(tripIdParam);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (trip == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		TripModel tripModel = new Trip2Model().convert(trip);

		return new ResponseEntity<TripModel>(tripModel, HttpStatus.OK);
	}

	/**
	 * The method returns all trips
	 * 
	 * @return HttpStatus.OK if successfully get all and get all the trips <br>
	 *         HttpStatus.NO_CONTENT if error with get all trips <br>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TripModel>> getAllTrip() {

		List<Trip> getAllTrip;
		try {
			getAllTrip = dispatcherService.getAllTrip();
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (getAllTrip == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		List<TripModel> tripModel = new ArrayList<>();
		for (Trip trip : getAllTrip) {
			tripModel.add(new Trip2Model().convert(trip));
		}

		return new ResponseEntity<List<TripModel>>(tripModel, HttpStatus.OK);
	}

	/**
	 * The method returns the trip "ID" who successfully passed created
	 * 
	 * @param requestModel
	 *            - transferring the new request for createTrip
	 * @param vehicleModel
	 *            - transferring the new vehicle for createTrip
	 * @return HttpStatus.CREATED if trip successfully created and "ID" the trip
	 *         <br>
	 *         HttpStatus.BAD_REQUEST if error created new trip
	 */
	@RequestMapping(value = "/trip", method = RequestMethod.PUT)
	public ResponseEntity<?> createTrip(@RequestBody CreateTripModel tripModel) {

		
		if (tripModel == null ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Request request = new Model2Request().convert(tripModel.getRequest());
		Vehicle vehicle = new Model2Vehicle().convert(tripModel.getVehicle());

		Trip trip;
		try {
			trip = dispatcherService.createTrip(request, vehicle);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<IdModel>(new IdModel(trip.getId()), HttpStatus.CREATED);
	}

	/**
	 * The method returns HttpStatus if the trip successfully passed updated
	 * 
	 * @param tripModel
	 *            - transferring the new trip for update trip
	 * @param tripIdParam
	 *            - transferring the trip by "ID" who need updating
	 * @return HttpStatus.ACCEPTED if trip successfully updated and "ID" the
	 *         trip <br>
	 *         HttpStatus.NO_CONTENT if error updated new trip
	 */
	@RequestMapping(value = "/trip/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> updateRequest(@RequestBody TripModel tripModel,
			@PathVariable(value = "id") Integer tripIdParam) {

		if (tripIdParam == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		Trip trip;
		try {
			trip = dispatcherService.getTrip(tripIdParam);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		tripApplyChanges(trip, tripModel);

		try {
			dispatcherService.modifyTrip(trip);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	/**
	 * The method is used in updateRequest method and checks what parameters
	 * came to update
	 * 
	 * @param tripFromDb
	 *            - transferring the Trip for update trip from Database
	 * @param tripModel
	 *            - transferring the TripModel for update trip from http request
	 */
	public void tripApplyChanges(Trip tripFromDb, TripModel tripModel) {

		if (tripModel.getRequest() != null) {
			tripFromDb.setRequest(new Model2Request().convert(tripModel.getRequest()));
		}
		if (tripModel.getVehicle() != null) {
			tripFromDb.setVehicle(new Model2Vehicle().convert(tripModel.getVehicle()));
		}
		if (tripModel.isEndTrip() != null) {
			tripFromDb.setEndTrip(tripModel.isEndTrip());
		}
	}

	/**
	 * The method return vehicle find by criteria
	 * 
	 * @param criteriaModel
	 *            - transferring the criteria for search vehicle from Database
	 * @return HttpStatus.OK if vehicle successfully find by criteria <br>
	 *         HttpStatus.NO_CONTENT if error find vehicle by criteria
	 */
	@RequestMapping(value = "/vehicle/criteria", method = RequestMethod.POST)
	public ResponseEntity<List<VehicleModel>> findVehicleByCriteria(@RequestBody VehicleSearchModel criteriaModel) {

		if (criteriaModel == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		VehicleSerachCriteria criteria = new Model2VehicleSearch().convert(criteriaModel);
		
		criteariaApplyChanges(criteria, criteriaModel);
		
		List<Vehicle> allCriteria;
		try {
			allCriteria = dispatcherService.findVehicleByCriteria(criteria);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		List<VehicleModel> convertCriteria = new ArrayList<>();
		for (Vehicle vehicle : allCriteria) {
			convertCriteria.add(new Vehicle2Model().convert(vehicle));
		}

		return new ResponseEntity<List<VehicleModel>>(convertCriteria, HttpStatus.OK);
	}

	/**
	 * The method is used in findVehicleByCriteria method and checks what parameters
	 * came to update
	 * 
	 * @param criteriaFromDb
	 *            - transferring the VehicleSerachCriteria for update trip from Database
	 * @param criteriaModel
	 *            - transferring the VehicleSearchModel for update trip from http request
	 */
	public void criteariaApplyChanges(VehicleSerachCriteria criteriaFromDb, VehicleSearchModel criteriaModel) {

		if (criteriaModel.getBrand() != null) {
			criteriaFromDb.setBrand(criteriaModel.getBrand());
		}
		if (criteriaModel.getType() != null) {
			criteriaFromDb.setType(criteriaModel.getType());
		}
		if (criteriaModel.getNameModel() != null) {
			criteriaFromDb.setNameModel(criteriaModel.getNameModel());
		}
		if (criteriaModel.getRegisterNumber() != null) {
			criteriaFromDb.setRegisterNumber(criteriaModel.getRegisterNumber());
		}
		if (criteriaModel.getCountOfPassenger() != null) {
			criteriaFromDb.setCountOfPassenger(criteriaModel.getCountOfPassenger());
		}
		if (criteriaModel.getReadyCrashCar() != null) {
			criteriaFromDb.setReadyCrashCar(criteriaModel.getReadyCrashCar());
		}
	}

	/**
	 * The method returns HttpStatus if trip successfully deleted
	 * 
	 * @param tripIdParam
	 *            - transferring by "ID" for delete by "ID" trip
	 * @return HttpStatus.OK if trip successfully deleted <br>
	 *         HttpStatus.NO_CONTENT if error deleted trip
	 */
	@RequestMapping(value = "/trip/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTrip(@PathVariable(value = "id") Integer tripIdParam) throws ServiceException {

		if (tripIdParam == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		try {
			dispatcherService.deleteTrip(tripIdParam);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}
}
