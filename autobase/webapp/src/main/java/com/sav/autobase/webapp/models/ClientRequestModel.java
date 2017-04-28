package com.sav.autobase.webapp.models;

import java.sql.Timestamp;

public class ClientRequestModel extends IdModel{

	private ClientUsersModel client;
	private Timestamp startDate;
	private Timestamp endDate;
	private PlaceModel place;
	private Integer countOfPassenger;
	private String comment;
	private String processed;


	public ClientUsersModel getClient() {
		return client;
	}

	public void setClient(ClientUsersModel client) {
		this.client = client;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public PlaceModel getPlace() {
		return place;
	}

	public void setPlace(PlaceModel place) {
		this.place = place;
	}

	public Integer getCountOfPassenger() {
		return countOfPassenger;
	}

	public void setCountOfPassenger(Integer countOfPassenger) {
		this.countOfPassenger = countOfPassenger;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getProcessed() {
		return processed;
	}

	public void setProcessed(String processed) {
		this.processed = processed;
	}

}
