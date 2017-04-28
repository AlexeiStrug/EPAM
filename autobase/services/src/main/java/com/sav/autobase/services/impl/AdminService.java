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
import com.sav.autobase.datamodel.BrandVehicle;
import com.sav.autobase.datamodel.ModelVehicle;
import com.sav.autobase.datamodel.Place;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.TypeUsers;
import com.sav.autobase.datamodel.TypeVehicle;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;
import com.sav.autobase.services.IAdminService;
import com.sav.autobase.services.exception.DAOException;

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
	public void saveVehicle(Vehicle vehicle) throws DAOException {
		if (vehicle != null) {
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
				throw new DAOException();
			}
		} else
			LOGGER.info("Failed save new Vehicle");

	}

	@Override
	public void deleteVehicle(Integer id) throws DAOException {
		if (id != null) {
			try {
				vehicleDao.delete(id);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
			}
			LOGGER.info("Deleted vehicle");
		} else
			LOGGER.info("Failed delete vehicle");
	}

	@Override
	public void saveModel(ModelVehicle model) throws DAOException {
		if (model != null) {
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
				throw new DAOException();
			}
		} else
			LOGGER.info("Failed save new Model Vehicle");
	}

	@Override
	public void deleteModel(Integer id) throws DAOException {
		if (id != null) {
			try {
				modelVehicleDao.delete(id);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
			}
			LOGGER.info("Deleted model vehicle");
		} else
			LOGGER.info("Failed delete model vehicle");
	}

	@Override
	public void addNewBrand(BrandVehicle brand) throws DAOException {
		if (brand != null) {
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
				throw new DAOException();
			}
		} else
			LOGGER.info("Failed save new brand");
	}

	@Override
	public void deleteBrand(Integer id) throws DAOException {
		if (id != null) {
			try {
				brandDao.delete(id);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
			}
			LOGGER.info("Deleted brand");
		} else
			LOGGER.info("Failed delete brand");
	}

	@Override
	public void addNewType(TypeVehicle type) throws DAOException {
		if (type != null) {
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
				throw new DAOException();
			}
		} else
			LOGGER.info("Failed save new type");
	}

	@Override
	public void deleteType(Integer id) throws DAOException {
		if (id != null) {
			try {
				typeDao.delete(id);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
			}
			LOGGER.info("Deleted type");
		} else
			LOGGER.info("Failed delete type");
	}

	@Override
	public void saveUser(Users user) throws DAOException {
		if (user != null) {
			try {
				if (user.getId() == null) {
					userDao.insert(user);
					LOGGER.info("Saved new user");
					if (user.getType() == TypeUsers.administator) {
						LOGGER.error("ERROR Saved new administator user");
					}
				} else {
					userDao.update(user);
					LOGGER.info("Updated new user");
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
			}
		} else
			LOGGER.info("Failed save new user");
	}

	@Override
	public void deleteUser(Integer id) throws DAOException {
		if (id != null) {
			if (userDao.getById(id).getType() == TypeUsers.administator) {
				LOGGER.error("ERROR DELETE administator user");
			}
			try {
				userDao.delete(id);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
			}
			LOGGER.info("Deleted user");
		} else
			LOGGER.info("Failed delete user");
	}

	@Override
	public void savePlace(Place place) throws DAOException {
		if (place != null) {
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
				throw new DAOException();
			}
		} else
			LOGGER.info("Failed save new place");
	}

	@Override
	public void deletePlace(Integer id) throws DAOException {
		if (id != null) {
			try {
				placeDao.delete(id);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
			}
			LOGGER.info("Deleted Place");
		} else
			LOGGER.info("Failed delete place");
	}

	@Override
	public void deleteTrip(Integer id) throws DAOException {
		if (id != null) {
			try {
				tripDao.delete(id);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
			}
			LOGGER.info("Deleted trip");
		} else
			LOGGER.info("Failed delete trip");
	}

	@Override
	public void deleteRequest(Integer id) throws DAOException {
		if (id != null) {
			try {
				requestDao.delete(id);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
			}
			LOGGER.info("Deleted request");
		} else
			LOGGER.info("Failed delete request");
	}

	@Override
	public Vehicle getVehicle(Integer id) throws DAOException {
		if (id != null) {
			try {
				return vehicleDao.joinGetById(id);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
			}
		} else
			return null;
	}

	@Override
	public List<Vehicle> getAllVehicle() throws DAOException {
		try {
			return vehicleDao.joinGetAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException();
		}
	}

	@Override
	public ModelVehicle getModelVehicle(Integer id) throws DAOException {
		if (id != null) {
			try {
				return modelVehicleDao.joinGetById(id);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
			}
		} else
			return null;
	}

	@Override
	public List<ModelVehicle> getAllModelVehicle() throws DAOException {
		try {
			return modelVehicleDao.joinGetAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException();
		}
	}

	@Override
	public BrandVehicle getBrand(Integer id) throws DAOException {
		if (id != null) {
			try {
				return brandDao.getById(id);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
			}
		} else
			return null;
	}

	@Override
	public TypeVehicle getType(Integer id) throws DAOException {
		if (id != null) {
			try {
				return typeDao.getById(id);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
			}
		} else
			return null;
	}

	@Override
	public Users getUser(Integer id) throws DAOException {
		if (id != null) {
			try {
				return userDao.getById(id);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
			}
		} else
			return null;
	}

	@Override
	public List<Users> getAllUser() throws DAOException {
		try {
			return userDao.getAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException();
		}
	}

	@Override
	public Request getRequest(Integer id) throws DAOException {
		if (id != null) {
			try {
				return requestDao.joinGetById(id);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
			}
		} else
			return null;
	}

	@Override
	public List<Request> getAllRequest() throws DAOException {
		try {
			return requestDao.joinGetAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException();
		}
	}

	@Override
	public List<Request> getAllRequestByUser(Users user) throws DAOException {
		try {
			return requestDao.joinGetAllbyUser(user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException();
		}
	}

	@Override
	public Trip getTrip(Integer id) throws DAOException {
		if (id != null) {
			try {
				return tripDao.joinGetById(id);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
			}
		} else
			return null;
	}

	@Override
	public List<Trip> getAllTrip() throws DAOException {
		try {
			return tripDao.joinGetAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException();
		}
	}

	@Override
	public Place getPlace(Integer id) throws DAOException {
		if (id != null) {
			try {
				return placeDao.getById(id);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
			}
		} else
			return null;
	}

	@Override
	public List<Place> getAllPlace() throws DAOException {
		try {
			return placeDao.getAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException();
		}
	}

	@Override
	public void deleteAll() throws DAOException {
		try {
			adminUntilityProcedures.delete();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException();
		}

	}

	@Override
	public void saveRequest(Request request) throws DAOException {
		if (request != null) {
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
				throw new DAOException();
			}
		} else
			LOGGER.info("Failed save new request");
	}

	@Override
	public void saveTrip(Trip trip) throws DAOException {
		if (trip != null) {
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
				throw new DAOException();
			}
		} else
			LOGGER.info("Failed save new trip");
	}

}
