package com.sav.autobase.webapp.converter.model2entity;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.StatusRequest;
import com.sav.autobase.webapp.models.ClientRequestModel;

public class Model2ClientRequest implements Converter<ClientRequestModel, Request> {

	public Request convert(ClientRequestModel requestModel) {
		return model2clientRequest(requestModel);
	}

	private Request model2clientRequest(ClientRequestModel requestModel) {
		Request request = new Request();
		request.setId(requestModel.getId());
		request.setClient(new Model2Client().convert(requestModel.getClient()));
		request.setStartDate(requestModel.getStartDate());
		request.setEndDate(requestModel.getEndDate());
		request.setPlace(new Model2Place().convert(requestModel.getPlace()));
		request.setCountOfPassenger(requestModel.getCountOfPassenger());
		request.setComment(requestModel.getComment());
		request.setProcessed(StatusRequest.valueOf(requestModel.getProcessed()));
		return request;
	}

}
