package com.sav.autobase.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sav.autobase.dao.api.IAdminUtilityProcedures;
import com.sav.autobase.dao.api.IGenericDao;
import com.sav.autobase.dao.api.IModelVehicleDao;
import com.sav.autobase.dao.api.IPlaceDao;
import com.sav.autobase.dao.api.IRequestDao;
import com.sav.autobase.dao.api.ITripDao;
import com.sav.autobase.dao.api.IUsersDao;
import com.sav.autobase.dao.api.IVehicleDao;
import com.sav.autobase.dao.api.filter.UserSearchCriteria;
import com.sav.autobase.dao.api.filter.VehicleSerachCriteria;
import com.sav.autobase.dao.db.cache.UserCache;
import com.sav.autobase.datamodel.BrandVehicle;
import com.sav.autobase.datamodel.ModelVehicle;
import com.sav.autobase.datamodel.Place;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.StatusRequest;
import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.TypeUsers;
import com.sav.autobase.datamodel.TypeVehicle;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;
import com.sav.autobase.services.IAdminService;
import com.sav.autobase.services.exception.ServiceException;

@Service
public class AdminService implements IAdminService {

	private final static Logger LOGGER = LoggerFactory.getLogger(AdminService.class);

	@Inject
	private IUsersDao userDao;

	@Inject
	private IVehicleDao vehicleDao;

	@Inject
	private IModelVehicleDao modelVehicleDao;

	@Inject
	private IPlaceDao placeDao;

	@Inject
	private ITripDao tripDao;

	@Inject
	private IRequestDao requestDao;

	@Inject
	private IAdminUtilityProcedures adminUntilityProcedures;

	@Inject
	private IGenericDao<TypeVehicle> typeDao;

	@Inject
	private IGenericDao<BrandVehicle> brandDao;

	@Override
	public void saveVehicle(Vehicle vehicle) throws ServiceException {
		if (vehicle == null) {
			LOGGER.error("Failed save new Vehicle");
			return;
		}
		try {
			if (vehicle.getId() == null) {
				vehicleDao.insert(vehicle);
				LOGGER.info("Saved new Vehicle");
			} else {
				vehicleDao.update(vehicle);
				LOGGER.info("Updated new Vehicle");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public void deleteVehicle(Integer id) throws ServiceException {
		if (id == null) {
			LOGGER.error("Failed delete vehicle");
			return;
		}
		try {
			vehicleDao.delete(id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
		LOGGER.info("Deleted vehicle");
	}

	@Override
	public void saveModel(ModelVehicle model) throws ServiceException {
		if (model == null) {
			LOGGER.error("Failed save new Model Vehicle");
			return;
		}
		try {
			if (model.getId() == null) {
				modelVehicleDao.insert(model);
				LOGGER.info("Saved new Model Vehicle");
			} else {
				modelVehicleDao.update(model);
				LOGGER.info("Updated new Model Vehicle");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public void deleteModel(Integer id) throws ServiceException {
		if (id == null) {
			LOGGER.error("Failed delete model vehicle");
			return;
		}
		try {
			modelVehicleDao.delete(id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
		LOGGER.info("Deleted model vehicle");
	}

	@Override
	public void addNewBrand(BrandVehicle brand) throws ServiceException {
		if (brand == null) {
			LOGGER.error("Failed save new brand");
			return;
		}
		try {
			if (brand.getId() == null) {
				brandDao.insert(brand);
				LOGGER.info("Saved new brand");
			} else {
				brandDao.update(brand);
				LOGGER.info("Updated new brand");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public void deleteBrand(Integer id) throws ServiceException {
		if (id == null) {
			LOGGER.error("Failed delete brand");
			return;
		}
		try {
			brandDao.delete(id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
		LOGGER.info("Deleted brand");
	}

	@Override
	public void addNewType(TypeVehicle type) throws ServiceException {
		if (type == null) {
			LOGGER.error("Failed save new type");
			return;
		}
		try {
			if (type.getId() == null) {
				typeDao.insert(type);
				LOGGER.info("Saved new type");
			} else {
				typeDao.update(type);
				LOGGER.info("Updated new type");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public void deleteType(Integer id) throws ServiceException {
		if (id == null) {
			LOGGER.error("Failed delete type");
			return;
		}
		try {
			typeDao.delete(id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
		LOGGER.info("Deleted type");
	}

	@Override
	public void saveUser(Users user) throws ServiceException {
		if (user == null) {
			LOGGER.error("Failed save new user");
			return;
		}
		try {
			if (user.getId() == null) {
				userDao.insert(user);
				LOGGER.info("Saved new user");
			} else {
				if (user.getType() == TypeUsers.administator) {
					LOGGER.error("ERROR Saved new administator user");
					return;
				}
				userDao.update(user);
				LOGGER.info("Updated new user");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public void deleteUser(Integer id) throws ServiceException {
		if (id == null) {
			LOGGER.error("Failed delete user");
			return;
		} else {
			if (userDao.getById(id).getType() == TypeUsers.administator) {
				LOGGER.error("ERROR DELETE administator user");
				return;
			}
			try {
				userDao.delete(id);
				LOGGER.info("Deleted user");
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new ServiceException();
			}
		}
	}

	@Override
	public void savePlace(Place place) throws ServiceException {
		if (place == null) {
			LOGGER.error("Failed save new place");
			return;
		} else {
			try {
				if (place.getId() == null) {
					placeDao.insert(place);
					LOGGER.info("Saved new place");
				} else {
					placeDao.update(place);
					LOGGER.info("Updated new place");
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new ServiceException();
			}
		}
	}

	@Override
	public void deletePlace(Integer id) throws ServiceException {
		if (id == null) {
			LOGGER.error("Failed delete place");
			return;
		} else {
			try {
				placeDao.delete(id);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new ServiceException();
			}
			LOGGER.info("Deleted Place");
		}
	}

	@Override
	public void deleteTrip(Integer id) throws ServiceException {
		if (id == null) {
			LOGGER.error("Failed delete trip");
			return;
		} else {
			try {
				tripDao.delete(id);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new ServiceException();
			}
			LOGGER.info("Deleted trip");
		}
	}

	@Override
	public void deleteRequest(Integer id) throws ServiceException {
		if (id == null) {
			LOGGER.error("Failed delete request");
			return;
		} else {
			try {
				requestDao.delete(id);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new ServiceException();
			}
			LOGGER.info("Deleted request");
		}
	}

	@Override
	public Vehicle getVehicle(Integer id) throws ServiceException {
		if (id == null) {
			return null;
		} else {
			try {
				return vehicleDao.joinGetById(id);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new ServiceException();
			}
		}
	}

	@Override
	public List<Vehicle> getAllVehicle() throws ServiceException {
		try {
			return vehicleDao.joinGetAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public List<Vehicle> findVehicleByCriteria(VehicleSerachCriteria criteria) throws ServiceException {
		if (criteria == null) {
			return vehicleDao.joinGetAllReadyCar(true);
		}
		try {
			return vehicleDao.getFiltered(criteria);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public ModelVehicle getModelVehicle(Integer id) throws ServiceException {
		if (id == null) {
			return null;
		}
		try {
			return modelVehicleDao.joinGetById(id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public List<ModelVehicle> getAllModelVehicle() throws ServiceException {
		try {
			return modelVehicleDao.joinGetAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public BrandVehicle getBrand(Integer id) throws ServiceException {
		if (id == null) {
			return null;
		}
		try {
			return brandDao.getById(id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public TypeVehicle getType(Integer id) throws ServiceException {
		if (id == null) {
			return null;
		}
		try {
			return typeDao.getById(id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public Users getUser(Integer id) throws ServiceException {
		if (id == null) {
			return null;
		}
		try {
			return userDao.getById(id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public List<Users> getAllUser() throws ServiceException {
		try {
			return userDao.getAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public List<Users> findUserByCriteria(UserSearchCriteria criteria) throws ServiceException {
		if (criteria == null) {
			return userDao.getAll();
		}
		try {
			return userDao.findByCriteria(criteria);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public Request getRequest(Integer id) throws ServiceException {
		if (id == null) {
			return null;
		}
		try {
			return requestDao.joinGetById(id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public List<Request> getAllRequest() throws ServiceException {
		try {
			return requestDao.joinGetAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public List<Request> getAllByStatusRequest(StatusRequest status) throws ServiceException {
		if (status == null) {
			return getAllRequest();
		}
		try {
			return requestDao.joinGetAllByStatus(status);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public List<Request> getAllRequestByUser(Users user) throws ServiceException {
		if (user == null) {
			return null;
		}
		try {
			return requestDao.joinGetAllbyUser(user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public Trip getTrip(Integer id) throws ServiceException {
		if (id == null) {
			return null;
		}
		try {
			return tripDao.joinGetById(id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public List<Trip> getAllTrip() throws ServiceException {
		try {
			return tripDao.joinGetAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public Place getPlace(Integer id) throws ServiceException {
		if (id == null) {
			return null;
		}
		try {
			return placeDao.getById(id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public List<Place> getAllPlace() throws ServiceException {
		try {
			return placeDao.getAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public void deleteAll() throws ServiceException {
		try {
			adminUntilityProcedures.delete();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public void saveRequest(Request request) throws ServiceException {
		if (request == null) {
			LOGGER.error("Failed save new request");
			return;
		}
		try {
			if (request.getId() == null) {
				requestDao.insert(request);
				LOGGER.info("Saved new request");
			} else {
				requestDao.update(request);
				LOGGER.info("Updated new request");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public void saveTrip(Trip trip) throws ServiceException {
		if (trip == null) {
			LOGGER.error("Failed save new trip");
			return;
		}
		try {
			if (trip.getId() == null) {
				tripDao.insert(trip);
				LOGGER.info("Saved new trip");
			} else {
				tripDao.update(trip);
				LOGGER.info("Updated new trip");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}
}
