package com.sav.autobase.webapp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sav.autobase.dao.impl.db.IRequestDao;
import com.sav.autobase.dao.impl.db.filters.UserSearchCriteria;
import com.sav.autobase.datamodel.TypeUsers;
import com.sav.autobase.datamodel.Vehicle;
import com.sav.autobase.services.ITripService;
import com.sav.autobase.services.IUsersService;
import com.sav.autobase.services.IVehicleService;

public class ServicesTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("web-context.xml");

//		IModelVehicleService service = context.getBean(IModelVehicleService.class);
//		IVehicleService service = context.getBean(IVehicleService.class);
//		IRequestDao service = context.getBean(IRequestDao.class);
//		IBookService service = context.getBean(IBookService.class);
//		ModelVehicleDaoImpl model1 = new ModelVehicleDaoImpl();
//		model1.getAll();
//		ModelVehicle model1 = new ModelVehicle();
//		ModelVehicle model1 = new ModelVehicle();
		
//		Vehicle vehicle = new Vehicle();
//		System.out.println(service.getById(5));
		IUsersService service =context.getBean(IUsersService.class);
		UserSearchCriteria criteria = new UserSearchCriteria();
		criteria.setLastName("struh");
		System.out.println(service.findByCriteria(criteria));
		
//		System.out.println(service.getById(1));
		
		
//		model1.setBrand(new BrandVehicle());
//		model1.getBrand().setId(1);
//		model1.setNameModel("a5");
//		model1.setRegisterNumber("1111-AA");
//		model1.setType(new TypeVehicle());
//		model1.getType().setId(1);
//		model1.setCountOfPassenger(5);
//		model1.setId(11);
//	    service.save(model1);
		
//		UserSearchCriteria user = new UserSearchCriteria();
//		user.setFirstName("Alexx");
//		user.setLastName("asd");
//		user.setDateBirth(new Timestamp(84, 1, 15, 0, 0, 0, 0));
//		service.save(user);
//		System.out.println(service.getById(3));
//		user.setType(TypeUsers.client.name());
//		System.out.println(service.findByCriteria(user));
		
//		System.out.println(service.getById(11));

//		Book book = new Book();
//		book.setTitle("New book from java");
//		service.save(book);
//
//		System.out.println("Saved book: " + book);
//		
//		System.out.println(service.get(book.getId()));
//		service.delete(book.getId());
//		System.out.println(service.get(book.getId()));
	}

}
