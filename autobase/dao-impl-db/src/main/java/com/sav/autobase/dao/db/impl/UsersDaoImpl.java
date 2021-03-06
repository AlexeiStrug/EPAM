package com.sav.autobase.dao.db.impl;

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

import com.sav.autobase.dao.api.IUsersDao;
import com.sav.autobase.dao.api.filter.UserSearchCriteria;
import com.sav.autobase.dao.db.cache.UserCache;
import com.sav.autobase.datamodel.Users;

@Repository
public class UsersDaoImpl extends GenericDaoImpl<Users> implements IUsersDao {

	final String INSERT_USER = "INSERT INTO users (first_name, last_name, login, password, email, date_birth, type) VALUES(?,?,?,?,?,?,?)";
	final String UPDATE_USER = "UPDATE users SET first_name = ?, last_name = ?, login = ?, password = ?, email = ?, date_birth = ?, type = ? where id = ?";
	final String FIND_LOGGIN_AND_PASSWORD = "SELECT * FROM users WHERE login = ? AND password = ? LIMIT 1";
	String FIND_BY_CRITERIA = "SELECT * FROM users WHERE true";

	private final static Logger LOGGER = LoggerFactory.getLogger(UsersDaoImpl.class);

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Inject
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Inject
	private UserCache userCache;

	@Override
	protected String getTableName() {
		return "users";
	}

	@Override
	public Users insert(Users user) {

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
	public Users update(Users user) {
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
				ps.setInt(8, user.getId());
				return ps;
			}
		});
		return user;
	}

	@Override
	public List<Users> findByCriteria(UserSearchCriteria criteria) {
		
		String query = FIND_BY_CRITERIA;
		try {
			if (criteria.isEmpty()) {
				getAll();
			}
			if (criteria.getFirstName() != null && criteria.getFirstName().length() > 0) {
				FIND_BY_CRITERIA += " AND first_name=:firstName";
			}
			if (criteria.getLastName() != null && criteria.getLastName().length() > 0) {
				FIND_BY_CRITERIA += " AND last_name=:lastName";
			}
			if (criteria.getDateBirth() != null) {
				FIND_BY_CRITERIA += " AND date_birth=:dateBirth";
			}
			if (criteria.getLogin() != null && criteria.getLogin().length() > 0) {
				FIND_BY_CRITERIA += " AND login=:login";
			}
			if (criteria.getEmail() != null && criteria.getEmail().length() > 0) {
				FIND_BY_CRITERIA += " AND email=:email";
			}
			if (criteria.getType() != null && criteria.getType().length() > 0) {
				FIND_BY_CRITERIA += " AND type=:type";
			}
			BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(criteria);
			return namedParameterJdbcTemplate.query(query, namedParameters,
					new BeanPropertyRowMapper<Users>(Users.class));
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Exception thrown! ", e);
			return null;
		}
	}

	@Override
	public Users findByloginPassword(String login, String password) {

		Users userFromCache = userCache.get(login);

		if (userFromCache != null) {
			if (userFromCache.getPassword().equals(password)) {
				LOGGER.info("from cache User");
				return userFromCache;
			} else
				return null;
		} else
			try {
				Users user = jdbcTemplate.queryForObject(FIND_LOGGIN_AND_PASSWORD, new Object[] { login, password },
						new BeanPropertyRowMapper<Users>(Users.class));
				userCache.set(user.getLogin(), user);
				return user;
			} catch (EmptyResultDataAccessException e) {
				LOGGER.error("Exception thrown! ", e);
				return null;
			}
	}

}
