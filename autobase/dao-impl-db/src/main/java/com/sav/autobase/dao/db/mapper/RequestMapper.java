package com.sav.autobase.dao.db.mapper;

import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sav.autobase.datamodel.Place;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.StatusRequest;
import com.sav.autobase.datamodel.TypeUsers;
import com.sav.autobase.datamodel.Users;

public class RequestMapper implements RowMapper<Request> {

	@Override
	public Request mapRow(ResultSet rs, int rowNum) throws SQLException {

		
		Users dispatcher = new Users();
		dispatcher.setId(rs.getInt("dispatcher_id"));
		if (dispatcher.getId() == 0)
		{
			dispatcher = null;
		} else {
		dispatcher.setFirstName(rs.getString("first_name"));
		dispatcher.setLastName(rs.getString("last_name"));
		dispatcher.setLogin(rs.getString("login"));
		dispatcher.setType(TypeUsers.valueOf(rs.getString("type")));
		}
		
		
		Users client = new Users();
		client.setId(rs.getInt("client_id"));
		client.setFirstName(rs.getString("first_name"));
		client.setLastName(rs.getString("last_name"));
		client.setLogin(rs.getString("login"));
		client.setType(TypeUsers.valueOf(rs.getString("type")));

		Place place = new Place();
		place.setId(rs.getInt("place_id"));
		place.setPlaceStart(rs.getString("place_start"));
		place.setPlaceEnd(rs.getString("place_end"));
		place.setDistance(rs.getInt("distance"));

		Integer id = rs.getInt("request_id");
		Timestamp startDate = rs.getTimestamp("start_date");
		Timestamp endDate = rs.getTimestamp("end_date");
		Integer countOfPassenger = rs.getInt("count_of_passenger");
		String processed = rs.getString("processed");

		Request request = new Request();
		request.setId(id);
		request.setClient(client);
		request.setStartDate(startDate);
		request.setEndDate(endDate);
		request.setPlace(place);
		request.setCountOfPassenger(countOfPassenger);
		request.setDispatcher(dispatcher);
		request.setProcessed(StatusRequest.valueOf(processed));

		return request;
	}

}