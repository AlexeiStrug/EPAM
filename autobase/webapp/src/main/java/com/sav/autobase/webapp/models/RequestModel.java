package com.sav.autobase.webapp.models;

import java.sql.Timestamp;

/**
 * This class RequestModel serves to store objects with properties request from
 * user dispatcher or administrator
 * 
 * @author AlexStrug
 * @version 1
 *
 */
public class RequestModel extends IdModel {

	/**
	 * client - the user client <br>
	 * startDate - start date trip <br>
	 * endDate - end date trip <br>
	 * place - the place trip <br>
	 * countOfPassenger - count of passenger in vehicle <br>
	 * dispatcher - the user dispatcher <br>
	 * comment - some information about request, vehicle <br>
	 * processed - status request
	 */
	private ClientUsersModel client;
	private Timestamp startDate;
	private Timestamp endDate;
	private PlaceModel place;
	private Integer countOfPassenger;
	private DispatcherUsersModel dispatcher;
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

	public DispatcherUsersModel getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(DispatcherUsersModel dispatcher) {
		this.dispatcher = dispatcher;
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
