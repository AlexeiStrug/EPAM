package com.sav.autobase.dao.impl.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.sav.autobase.dao.impl.db.IModelVehicleDao;
import com.sav.autobase.dao.impl.db.exceptions.DaoException;
import com.sav.autobase.dao.impl.db.mapper.ModelMapper;
import com.sav.autobase.datamodel.ModelVehicle;

@Repository
public class ModelVehicleDaoImpl implements IModelVehicleDao {

	final String INSERT_MODEL = "INSERT INTO model_vehicle (brand_id, name_model, register_number, type_id, count_of_passenger) VALUES(?,?,?,?,?)";
	final String DELETE_MODEL = "DELETE FROM model_vehicle WHERE id= ?";
	final String FIND_MODEL_BY_ID = "SELECT * FROM model_vehicle "
			+ "INNER JOIN brand_vehicle ON brand_vehicle.id=model_vehicle.brand_id "
			+ "INNER JOIN type_vehicle ON model_vehicle.type_id=type_vehicle.id WHERE model_vehicle.id = ? ";
	final String GET_ALL_MODEL = "SELECT * FROM model_vehicle "
			+ "INNER JOIN brand_vehicle ON brand_vehicle.id=model_vehicle.brand_id "
			+ "INNER JOIN type_vehicle ON model_vehicle.type_id=type_vehicle.id";
	final String UPDATE_MODEL = "UPDATE model_vehicle SET brand_id = ?, name_model = ?, register_number = ?, type_id = ?, count_of_passenger = ? WHERE model_vehicle.id = ? ";

	private final static Logger LOGGER = LoggerFactory.getLogger(ModelVehicleDaoImpl.class);

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public ModelVehicle getById(Integer id) throws DaoException {
		try {
			return jdbcTemplate.queryForObject(FIND_MODEL_BY_ID, new Object[] { id }, new ModelMapper());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
			return null;
		}
	}

	@Override
	public ModelVehicle insert(ModelVehicle model) throws DaoException {

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_MODEL, new String[] { "id" });
				ps.setObject(1, model.getBrand().getId());
				ps.setString(2, model.getNameModel());
				ps.setString(3, model.getRegisterNumber());
				ps.setObject(4, model.getType().getId());
				ps.setInt(5, model.getCountOfPassenger());
				return ps;
			}
		}, keyHolder);

		Number key = keyHolder.getKey();
		model.setId(key.intValue());
		return model;
	}

	@Override
	public ModelVehicle update(ModelVehicle model) throws DaoException {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(UPDATE_MODEL);
				ps.setObject(1, model.getBrand().getId());
				ps.setString(2, model.getNameModel());
				ps.setString(3, model.getRegisterNumber());
				ps.setObject(4, model.getType().getId());
				ps.setInt(5, model.getCountOfPassenger());
				ps.setInt(6, model.getId());
				return ps;
			}
		});
		return model;
	}

	@Override
	public void delete(Integer id) throws DaoException {
		jdbcTemplate.update(DELETE_MODEL + id);

	}

	@Override
	public List<ModelVehicle> getAll() throws DaoException {
		try {
			List<ModelVehicle> rs = jdbcTemplate.query(GET_ALL_MODEL, new ModelMapper());
			return rs;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
			return null;
		}
	}

}
