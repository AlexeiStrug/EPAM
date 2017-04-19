package com.sav.autobase.datamodel;

import java.sql.Timestamp;

public class Request extends AbstractModel {

	private Users client;
	private Timestamp startDate;
	private Timestamp endDate;
	private Place place;
	private Integer countOfPassenger;
	private Users dispatcher;
	private StatusRequest processed;

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

	public StatusRequest getProcessed() {
		return processed;
	}

	public void setProcessed(StatusRequest processed) {
		this.processed = processed;
	}

	@Override
	public String toString() {
		return "Request id=" + getId() + " [client=" + client + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", place=" + place + ", countOfPassenger=" + countOfPassenger + ", dispatcher=" + dispatcher + ", processed =" + processed + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((countOfPassenger == null) ? 0 : countOfPassenger.hashCode());
		result = prime * result + ((dispatcher == null) ? 0 : dispatcher.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((place == null) ? 0 : place.hashCode());
		result = prime * result + ((processed == null) ? 0 : processed.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Request))
			return false;
		Request other = (Request) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (countOfPassenger == null) {
			if (other.countOfPassenger != null)
				return false;
		} else if (!countOfPassenger.equals(other.countOfPassenger))
			return false;
		if (dispatcher == null) {
			if (other.dispatcher != null)
				return false;
		} else if (!dispatcher.equals(other.dispatcher))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (place == null) {
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
			return false;
		if (processed != other.processed)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

}
