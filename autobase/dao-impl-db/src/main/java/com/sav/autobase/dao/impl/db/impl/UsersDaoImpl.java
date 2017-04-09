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
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.sav.autobase.dao.impl.db.IUsersDao;
import com.sav.autobase.dao.impl.db.exceptions.DaoException;
import com.sav.autobase.dao.impl.db.filters.UserSearchCriteria;
import com.sav.autobase.datamodel.Users;

@Repository
public class UsersDaoImpl implements IUsersDao {

	final String FIND_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
	final String GET_ALL_USERS = "SELECT * FROM users ";
	final String INSERT_USER = "INSERT INTO users (first_name, last_name, login, password, email, date_birth, type) VALUES(?,?,?,?,?,?,?)";
	final String UPDATE_USER = "UPDATE users SET first_name = ?, last_name = ?, login = ?, password = ?, email = ?, date_birth = ?, type = ? where id = ?";
	final String DELETE_USER = "DELETE FROM users WHERE id = ?";
	String FIND_BY_CRITERIA = "SELECT * FROM users WHERE true";

	private final static Logger LOGGER = LoggerFactory.getLogger(UsersDaoImpl.class);

	@Inject
	private JdbcTemplate jdbcTemplate;
	@Inject
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public Users getById(Integer id) throws DaoException {
		try {
			return jdbcTemplate.queryForObject(FIND_USER_BY_ID, new Object[] { id },
					new BeanPropertyRowMapper<Users>(Users.class));
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
			return null;
		}
	}

	@Override
	public Users insert(Users user) throws DaoException {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_USER, new String[] { "id" });
				ps.setString(1, user.getFirstName());
				ps.setString(2, user.getLastName());
				ps.setString(3, user.getLogin());
				ps.setString(4, user.getPassword());
				ps.setString(5, user.getEmail());
				ps.setTimestamp(6, user.getDateBirth());
				ps.setString(7, user.getType().name());
				return ps;
			}
		}, keyHolder);

		Number key = keyHolder.getKey();
		user.setId(key.intValue());
		return user;
	}

	@Override
	public Users update(Users user) throws DaoException {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(UPDATE_USER);
				ps.setString(1, user.getFirstName());
				ps.setString(2, user.getLastName());
				ps.setString(3, user.getLogin());
				ps.setString(4, user.getPassword());
				ps.setString(5, user.getEmail());
				ps.setTimestamp(6, user.getDateBirth());
				ps.setString(7, user.getType().name());
				return ps;
			}
		});
		return user;
	}

	@Override
	public void delete(Integer id) throws DaoException {
		jdbcTemplate.update(DELETE_USER + id);

	}

	@Override
	public List<Users> getAll() throws DaoException {
		try {
			List<Users> rs = jdbcTemplate.query(GET_ALL_USERS, new BeanPropertyRowMapper<Users>(Users.class));
			return rs;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
			return null;
		}
	}

	@Override
	public List<Users> findByCriteria(UserSearchCriteria criteria) throws DaoException {
		try {
			if (criteria.isEmpty()) {
				getAll();
			}
			if (criteria.getFirstName() != null) {
				FIND_BY_CRITERIA += " AND first_name=:firstName";
			}
			if (criteria.getLastName() != null) {
				FIND_BY_CRITERIA += " AND last_name=:lastName";
			}
			if (criteria.getDateBirth() != null) {
				FIND_BY_CRITERIA += " AND date_birth=:dateBirth";
			}
			if (criteria.getLogin() != null) {
				FIND_BY_CRITERIA += " AND login=:login";
			}
			if (criteria.getEmail() != null) {
				FIND_BY_CRITERIA += " AND email=:email";
			}
			if (criteria.getType() != null) {
				FIND_BY_CRITERIA += " AND type=:type";
			}
			BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(criteria);
			return namedParameterJdbcTemplate.query(FIND_BY_CRITERIA, namedParameters,
					new BeanPropertyRowMapper<Users>(Users.class));
		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Exception thrown! ", e);
			return null;
		}
	}

}
