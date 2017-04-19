package com.sav.autobase.services.factory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.sav.autobase.datamodel.TypeUsers;
import com.sav.autobase.datamodel.Users;

public class UserFactory implements IFactory<Users> {

	Users newestUser1 = new Users();
	Users newestUser2 = new Users();
	ArrayList<Users> users = new ArrayList<>();

	public UserFactory() {
		newestUser1.setFirstName("Sasha");
		newestUser1.setLastName("SSS");
		newestUser1.setLogin("dsfsdf");
		newestUser1.setPassword("12345");
		newestUser1.setEmail("sdfsdfsd@asda.ru");
		newestUser1.setDateBirth(new Timestamp(new Date().getTime()));
		newestUser1.setType(TypeUsers.client);

		newestUser2.setFirstName("Pasha");
		newestUser2.setLastName("AAA");
		newestUser2.setLogin("wwwww");
		newestUser2.setPassword("54321");
		newestUser2.setEmail("qwert@asda.ru");
		newestUser2.setDateBirth(new Timestamp(new Date().getTime()));
		newestUser2.setType(TypeUsers.client);

		users.add(newestUser1);
		users.add(newestUser2);
	}

	public Users create() {
		Random r = new Random();
		return users.get(r.nextInt(users.size()));
	}

}
