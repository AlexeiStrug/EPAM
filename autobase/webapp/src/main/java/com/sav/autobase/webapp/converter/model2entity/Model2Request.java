package com.sav.autobase.webapp.converter.model2entity;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.StatusRequest;
import com.sav.autobase.webapp.models.RequestModel;

public class Model2Request implements Converter<RequestModel, Request> {

	public Request convert(RequestModel requestModel) {
		return model2request(requestModel);
	}

	private Request model2request(RequestModel requestModel) {
		Request request = new Request();
		request.setId(requestModel.getId());
		request.setClient(new Model2Client().convert(requestModel.getClient()));
		request.setStartDate(requestModel.getStartDate());
		request.setEndDate(requestModel.getEndDate());
		request.setPlace(new Model2Place().convert(requestModel.getPlace()));
		request.setCountOfPassenger(requestModel.getCountOfPassenger());
		request.setDispatcher(new Model2Dispathcer().convert(requestModel.getDispatcher()));
		request.setComment(requestModel.getComment());
		request.setProcessed(StatusRequest.valueOf(requestModel.getProcessed()));
		return request;
	}

}
