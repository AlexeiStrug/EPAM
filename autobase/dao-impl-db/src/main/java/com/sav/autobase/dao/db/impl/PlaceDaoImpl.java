package com.sav.autobase.dao.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

import com.sav.autobase.dao.api.IPlaceDao;
import com.sav.autobase.datamodel.Place;

@Repository
public class PlaceDaoImpl extends GenericDaoImpl<Place> implements IPlaceDao {

	final String FIND_PLACE_BY_STARTNAME = "SELECT * FROM place WHERE place_start = ?";
	final String FIND_PLACE_BY_ENDNAME = "SELECT * FROM place WHERE place_end = ?";
	final String INSERT_PLACE = "INSERT INTO place (place_start, place_end, distance) VALUES(?,?,?)";
	

	private final static Logger LOGGER = LoggerFactory.getLogger(PlaceDaoImpl.class);

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	protected String getTableName() {
		return "place";
	}
	
	@Override
	public Place getByStartPlace(Place place) {
		try {
			return jdbcTemplate.queryForObject(FIND_PLACE_BY_STARTNAME, new Object[] { place },
					new BeanPropertyRowMapper<Place>(Place.class));
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Exception thrown! ", e);
			return null;
		}
	}

	@Override
	public Place getByEndtPlace(Place place) {
		try {
			return jdbcTemplate.queryForObject(FIND_PLACE_BY_ENDNAME, new Object[] { place },
					new BeanPropertyRowMapper<Place>(Place.class));
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Exception thrown! ", e);
			return null;
		}
	}

	@Override
	public Place insert(Place place) {
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
	public Place update(Place entity) throws UnsupportedOperationException {
		LOGGER.error("Used UnsupportedOperationException");
		throw new UnsupportedOperationException();
	}

}
