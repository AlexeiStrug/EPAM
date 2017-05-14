package com.sav.autobase.webapp.controllers;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;
import com.sav.autobase.services.IDriverService;
import com.sav.autobase.services.exception.ServiceException;
import com.sav.autobase.webapp.converter.entity2model.DriverTrip2Model;
import com.sav.autobase.webapp.converter.entity2model.Vehicle2Model;
import com.sav.autobase.webapp.filter.BasicAuthFilter;
import com.sav.autobase.webapp.models.DriverTripModel;
import com.sav.autobase.webapp.models.VehicleModel;

/**
 * This class DriverController includes a set of methods of action necessary for
 * the user driver
 * 
 * @author AlexStrug
 * @version 1
 *
 */
@RestController
@RequestMapping("/driver")
public class DriverController {

	@Inject
	private IDriverService driverService;

	/**
	 * The method returns the trip for the driver
	 * 
	 * @param httpServletRequest
	 *            - transferring the user(driver) from BasicAuthFilter
	 * @return HttpStatus.OK if there is the trip created by the dispatcher for
	 *         execution by a certain driver <br>
	 *         HttpStatus.NO_CONTENT if trip == null
	 */
	@RequestMapping(value = "/trip", method = RequestMethod.GET)
	public ResponseEntity<?> getTrip(HttpServletRequest httpServletRequest) {

		Users user = (Users) httpServletRequest.getAttribute(BasicAuthFilter.userAttribute);
		Trip trip;
		try {
			trip = driverService.getTrip(user);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (trip == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		DriverTripModel tripModel = new DriverTrip2Model().convert(trip);
		return new ResponseEntity<DriverTripModel>(tripModel, HttpStatus.OK);
	}

	/**
	 * The method returns the vehicle for the driver
	 * 
	 * @param httpServletRequest
	 *            - transferring the user(driver) from BasicAuthFilter
	 * @return HttpStatus.OK if there is the vehicle created by the dispatcher
	 *         for execution by a certain driver <br>
	 *         HttpStatus.NO_CONTENT if vehicle == null
	 */
	@RequestMapping(value = "/vehicle", method = RequestMethod.GET)
	public ResponseEntity<?> getVehicle(HttpServletRequest httpServletRequest) {

		Users user = (Users) httpServletRequest.getAttribute(BasicAuthFilter.userAttribute);
		Vehicle vehicle;
		try {
			vehicle = driverService.getVehicle(user);
			if (vehicle == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		VehicleModel vehicleModel = new Vehicle2Model().convert(vehicle);
		return new ResponseEntity<VehicleModel>(vehicleModel, HttpStatus.OK);
	}

	/**
	 * The method change status the trip for the driver
	 * 
	 * @param httpServletRequest
	 *            - transferring the user(driver) from BasicAuthFilter
	 * @return HttpStatus.OK if successfully managed to change the status trip
	 *         <br>
	 *         HttpStatus.NO_CONTENT if there's no such thing trip
	 */
	@RequestMapping(value = "/trip", method = RequestMethod.POST)
	public ResponseEntity<?> changeStatusTrip(HttpServletRequest httpServletRequest) {

		Users user = (Users) httpServletRequest.getAttribute(BasicAuthFilter.userAttribute);
		try {
			driverService.changeStatusTrip(user);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * The method change status the vehicle for the driver
	 * 
	 * @param httpServletRequest
	 *            - transferring the user(driver) from BasicAuthFilter
	 * @return HttpStatus.OK if successfully managed to change the status trip
	 *         <br>
	 *         HttpStatus.NO_CONTENT if there's no such thing vehicle
	 */
	@RequestMapping(value = "/vehicle", method = RequestMethod.POST)
	public ResponseEntity<?> changeStatusVehicle(HttpServletRequest httpServletRequest) {
		Users user = (Users) httpServletRequest.getAttribute(BasicAuthFilter.userAttribute);
		try {
			driverService.changeStatusVehicle(user);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
