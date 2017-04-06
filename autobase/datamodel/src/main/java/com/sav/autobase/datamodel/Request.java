package com.sav.autobase.datamodel;

import java.sql.Timestamp;

public class Request extends AbstractModel{

	private Users client;
	private Timestamp startDate;
	private Timestamp endDate;
	private Place place;
	private Integer countOfPassenger;
	private Users dispatcher;

	public Users getClient() {
		return client;
	}

	public void setClient(Users client) {
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

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public Integer getCountOfPassenger() {
		return countOfPassenger;
	}

	public void setCountOfPassenger(Integer countOfPassenger) {
		this.countOfPassenger = countOfPassenger;
	}

	public Users getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(Users dispatcher) {
		this.dispatcher = dispatcher;
	}

	@Override
	public String toString() {
		return "Request id=" + getId() + " [client=" + client + ", startDate=" + startDate + ", endDate=" + endDate + ", place=" + place
				+ ", countOfPassenger=" + countOfPassenger + ", dispatcher=" + dispatcher + "]";
	}


	
	

}
