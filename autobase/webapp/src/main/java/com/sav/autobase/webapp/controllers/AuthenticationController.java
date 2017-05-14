package com.sav.autobase.webapp.controllers;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sav.autobase.datamodel.Users;
import com.sav.autobase.services.IAuthenticationService;
import com.sav.autobase.services.exception.ModifyException;
import com.sav.autobase.services.exception.ServiceException;
import com.sav.autobase.webapp.converter.model2entity.Model2User;
import com.sav.autobase.webapp.models.IdModel;
import com.sav.autobase.webapp.models.UsersModel;

/**
 * This class AuthenticationController includes a set of methods of action
 * necessary for register and login the users
 * 
 * @author AlexStrug
 * @version 1
 *
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Inject
	private IAuthenticationService authService;

	/**
	 * The method returns the users who successfully passed authenticate
	 * 
	 * @param login
	 *            - transferring the user login for authenticate
	 * @param password
	 *            - transferring the user password for authenticate
	 * @return HttpStatus.OK if user successfully passed authenticate and all
	 *         information about authenticate user <br>
	 *         HttpStatus.NOT_FOUND if error authenticate
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<?> authenticate(@RequestParam(value = "login", required = true) String login,
			@RequestParam(value = "pass", required = true) String password) {

		Users authUser = null;
		try {
			authUser = authService.authenticate(login, password);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (authUser == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Users>(authUser, HttpStatus.OK);
	}

	/**
	 * The method returns the users who successfully passed registration
	 * 
	 * @param userModel
	 *            - transferring the new user for registration
	 * @return HttpStatus.CREATED if user successfully passed register and "ID"
	 *         the users <br>
	 *         HttpStatus.BAD_REQUEST if error created new user or created user
	 *         from type "Administrator"
	 */
	@RequestMapping(value = "/register", method = RequestMethod.PUT)
	public ResponseEntity<?> registerUser(@RequestBody UsersModel userModel) {

		if (userModel == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Users RegisterUser = new Model2User().convert(userModel);
		try {
			authService.register(RegisterUser);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (ModifyException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<IdModel>(new IdModel(RegisterUser.getId()), HttpStatus.CREATED);
	}

}
