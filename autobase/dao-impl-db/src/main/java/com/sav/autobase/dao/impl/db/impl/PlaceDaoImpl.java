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

import com.sav.autobase.dao.impl.db.IPlaceDao;
import com.sav.autobase.dao.impl.db.exceptions.DaoException;
import com.sav.autobase.datamodel.Place;

public class PlaceDaoImpl implements IPlaceDao {

	final String FIND_PLACE_BY_ID = "SELECT * FROM place where id = ? ";
	final String FIND_PLACE_BY_STARTNAME = "SELECT * FROM place WHERE place_start = ?";
	final String FIND_PLACE_BY_ENDNAME = "SELECT * FROM place WHERE place_end = ?";
	final String GET_ALL_PLACE = "SELECT * FROM place ";
	final String INSERT_PLACE = "INSERT INTO place (start_place, end_place, distance) VALUES(?,?,?)";
	final String DELELTE_PLACE = "DELETE FROM place WHERE id = ?";

	private final static Logger LOGGER = LoggerFactory.getLogger(PlaceDaoImpl.class);

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public Place getById(Integer id) throws DaoException {
		try {
			return jdbcTemplate.queryForObject(FIND_PLACE_BY_ID, new Object[] { id },
					new BeanPropertyRowMapper<Place>(Place.class));
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
			return null;
		}
	}

	@Override
	public Place getByStartPlace(Place place) throws DaoException {
		try {
			return jdbcTemplate.queryForObject(FIND_PLACE_BY_STARTNAME, new Object[] { place },
					new BeanPropertyRowMapper<Place>(Place.class));
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
			return null;
		}
	}

	@Override
	public Place getByEndtPlace(Place place) throws DaoException {
		try {
			return jdbcTemplate.queryForObject(FIND_PLACE_BY_ENDNAME, new Object[] { place },
					new BeanPropertyRowMapper<Place>(Place.class));
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
			return null;
		}
	}

	@Override
	public Place insert(Place place) throws DaoException {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_PLACE, new String[] { "id" });
				ps.setString(1, place.getPlaceStart());
				ps.setString(2, place.getPlaceEnd());
				ps.setInt(3, place.getDistance());
				return ps;
			}
		}, keyHolder);

		Number key = keyHolder.getKey();
		place.setId(key.intValue());
		return place;
	}

	@Override
	public List<Place> getAll() throws DaoException {
		try {
			List<Place> rs = jdbcTemplate.query(GET_ALL_PLACE, new BeanPropertyRowMapper<Place>(Place.class));
			return rs;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
			return null;
		}
	}

	@Override
	public void delete(Integer id) throws DaoException {
		jdbcTemplate.update(DELELTE_PLACE + id);

	}

	@Override
	public Place update(Place entity) throws DaoException {
		 LOGGER.debug("Used UnsupportedOperationException");
		 throw new UnsupportedOperationException();
	}

}
