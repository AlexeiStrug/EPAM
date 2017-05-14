package com.sav.autobase.services;

import java.text.ParseException;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.sav.autobase.datamodel.Users;
import com.sav.autobase.services.exception.ServiceException;

public class AdminXMLServiceTest extends AbstractTest{

	private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceTest.class);

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
	
	
	@Test
	public void registerUserTest() throws ServiceException {
		
		LOGGER.debug("^^^REGISTER USER test");

		adminService.saveUser(newUser);
		Users userFromDb = adminService.getUser(newUser.getId());

		Assert.notNull(userFromDb, "Method must be get new user");
		
		
	}
	
	
}
