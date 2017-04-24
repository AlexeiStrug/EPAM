package com.sav.autobase.dao.db.impl;

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

import com.sav.autobase.dao.api.IRequestDao;
import com.sav.autobase.dao.db.mapper.RequestMapper;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.StatusRequest;

@Repository
public class RequestDaoImpl extends GenericDaoImpl<Request> implements IRequestDao {

	final String FIND_REQUEST_BY_ID = "SELECT request.id as request_id , request.client_id, request.dispatcher_id, users.first_name, users.last_name, users.login, users.type, request.place_id, place.id, place.place_start, place.place_end, place.distance, request.start_date, request.end_date, request.count_of_passenger, request.processed FROM request "
			+ "INNER JOIN users ON users.id = request.client_id Or request.dispatcher_id is not null "
			+ "INNER JOIN place ON place.id = request.place_id WHERE request.id = ?";
	final String GET_ALL_REQUEST = "SELECT request.id as request_id , request.client_id, request.dispatcher_id, users.first_name, users.last_name, users.login, users.type, request.place_id, place.id, place.place_start, place.place_end, place.distance, request.start_date, request.end_date, request.count_of_passenger, request.processed FROM request "
			+ "INNER JOIN users ON users.id = request.client_id Or request.dispatcher_id is not null "
			+ "INNER JOIN place ON place.id = request.place_id ";
	final String FIND_BY_PROCESSED = "SELECT request.id as request_id, request.client_id, request.dispatcher_id, users.first_name, users.last_name, users.login, users.type, request.place_id, place.id, place.place_start, place.place_end, place.distance, request.start_date, request.end_date, request.count_of_passenger, request.processed FROM request "
			+ "INNER JOIN users ON users.id = request.client_id Or request.dispatcher_id is not null "
			+ "INNER JOIN place ON place.id = request.place_id WHERE request.processed = ? LIMIT 1";
	final String INSERT_REQUEST = "INSERT INTO request (client_id, start_date, end_date, place_id, count_of_passenger, processed) VALUES(?,?,?,?,?,?)";
	final String UPDATE_REQUEST = "UPDATE request SET client_id = ?, start_date = ?, end_date = ?, place_id = ?, count_of_passenger = ?, dispatcher_id = ?, processed = ? where id = ?;";
	final String UPDATE_CLIENT_REQUEST = "UPDATE request SET client_id = ?, start_date = ?, end_date = ?, place_id = ?, count_of_passenger = ?, processed = ? where id = ?;";

	private final static Logger LOGGER = LoggerFactory.getLogger(RequestDaoImpl.class);

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	protected String getTableName() {
		return "request";
	}
	
	@Override
	public Request joinGetById(Integer id) {
		try {
			return jdbcTemplate.queryForObject(FIND_REQUEST_BY_ID, new Object[] { id }, new RequestMapper());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
			return null;
		}
	}
	
	@Override
	public Request joinFindByProcessed(StatusRequest status) {
		try {
			return jdbcTemplate.queryForObject(FIND_BY_PROCESSED, new Object[] { status.name() }, new RequestMapper());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
			return null;
		}
	}

	@Override
	public Request insert(Request request) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_REQUEST, new String[] { "id" });
				ps.setInt(1, request.getClient().getId());
				ps.setTimestamp(2, request.getStartDate());
				ps.setTimestamp(3, request.getEndDate());
				ps.setInt(4, request.getPlace().getId());
				ps.setInt(5, request.getCountOfPassenger());
				ps.setString(6, request.getProcessed().name());
				return ps;
			}
		}, keyHolder);

		Number key = keyHolder.getKey();
		request.setId(key.intValue());
		return request;
	}
	
	@Override
	public Request updateClientRequest(Request request) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(UPDATE_CLIENT_REQUEST);
				ps.setInt(1, request.getClient().getId());
				ps.setTimestamp(2, request.getStartDate());
				ps.setTimestamp(3, request.getEndDate());
				ps.setInt(4, request.getPlace().getId());
				ps.setInt(5, request.getCountOfPassenger());
				ps.setString(6, request.getProcessed().name());
				ps.setInt(7, request.getId());
				return ps;
			}
		});
		return request;
	}

	@Override
	public Request update(Request request) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(UPDATE_REQUEST);
				ps.setInt(1, request.getClient().getId());
				ps.setTimestamp(2, request.getStartDate());
				ps.setTimestamp(3, request.getEndDate());
				ps.setInt(4, request.getPlace().getId());
				ps.setInt(5, request.getCountOfPassenger());
				ps.setInt(6, request.getDispatcher().getId());
				ps.setString(7, request.getProcessed().name());
				ps.setInt(8, request.getId());
				return ps;
			}
		});
		return request;
	}

	@Override
	public List<Request> joinGetAll() {
		try {
			List<Request> rs = jdbcTemplate.query(GET_ALL_REQUEST, new RequestMapper());
			return rs;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
			return null;
		}
	}

}