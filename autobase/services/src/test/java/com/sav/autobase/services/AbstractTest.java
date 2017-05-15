package com.sav.autobase.services;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sav.autobase.services.factory.EntityFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:services-context.xml")
public abstract class AbstractTest {
	

	EntityFactory entityFactory = new EntityFactory();

	
}
