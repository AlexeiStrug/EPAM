package com.sav.autobase.dao.impl.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.sav.autobase.dao.impl.db.IBrandVehicleDao;
import com.sav.autobase.datamodel.BrandVehicle;

@Repository
public class BrandVehicleDaoImpl implements IBrandVehicleDao {

	private final String FIND_BRAND_BY_ID = "SELECT * FROM brand_vehicle WHERE id = ? ";
	private final String INSERT_SQL = "INSERT INTO brand_vehicle (brand_name) VALUES(?)";
	private final String GET_ALL_BRAND = "SELECT * FROM brand_vehicle ";
	private final String DELETE_BRAND = "DELETE FROM brand_vehicle WHERE id= ?";

	private final static Logger LOGGER = LoggerFactory.getLogger(BrandVehicleDaoImpl.class);

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public BrandVehicle getById(Integer id) {
		try {
			return jdbcTemplate.queryForObject(FIND_BRAND_BY_ID, new Object[] { id },
					new BeanPropertyRowMapper<BrandVehicle>(BrandVehicle.class));
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
			return null;
		}
	}

	@Override
	public BrandVehicle insert(BrandVehicle brand) {

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, brand.getBrandName());
				return ps;
			}
		}, keyHolder);

		Number key = keyHolder.getKey();
		brand.setId(key.intValue());
		return brand;
	}

	@Override
	public List<BrandVehicle> getAll() {
		try {
			List<BrandVehicle> rs = jdbcTemplate.query(GET_ALL_BRAND,
					new BeanPropertyRowMapper<BrandVehicle>(BrandVehicle.class));
			return rs;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
			return null;
		}
	}

	@Override
	public void delete(Integer id) {
		try {
			jdbcTemplate.update(DELETE_BRAND + id);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
		}

	}

	@Override
	public BrandVehicle update(BrandVehicle entity) throws UnsupportedOperationException {
		LOGGER.debug("Used UnsupportedOperationException");
		throw new UnsupportedOperationException();
	}

}
