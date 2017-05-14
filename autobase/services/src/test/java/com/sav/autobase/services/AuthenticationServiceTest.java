package com.sav.autobase.services;

import java.text.ParseException;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.sav.autobase.datamodel.Users;
import com.sav.autobase.services.exception.ModifyException;
import com.sav.autobase.services.exception.ServiceException;

public class AuthenticationServiceTest extends AbstractTest {
	
	
	private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceTest.class);
	
	@Inject
	private IAuthenticationService authService;
	
	@Inject 
	private IAdminService adminService;
	
	private Users newUser;
	
	@Before
	public void runBeforeMethod() throws ServiceException, ParseException {

		LOGGER.debug("^^^CREATED necessary entities:");

		newUser = entityFactory.createUser();
		adminService.saveUser(newUser);
		LOGGER.debug("{}", newUser);

	}

	@After
	public void runAfterMethod() throws ServiceException {

		LOGGER.debug("^^^DELETED created entites");
		adminService.deleteAll();
	}
	
	@Test
	public void registerUserTest() throws ServiceException, ModifyException {
		
		LOGGER.debug("^^^REGISTER USER test");

		authService.register(newUser);
		Users userFromDb = adminService.getUser(newUser.getId());

		Assert.notNull(userFromDb, "Method must be get new user");
		
		
	}
	
	@Test
	public void authenticateTest() throws ServiceException {
		
		LOGGER.debug("^^^AUTHENTICATE USER test");
		
		Users userGiven = authService.authenticate(newUser.getLogin(), newUser.getPassword());
		
		Assert.notNull(userGiven, "Method must be true");
	}
}
