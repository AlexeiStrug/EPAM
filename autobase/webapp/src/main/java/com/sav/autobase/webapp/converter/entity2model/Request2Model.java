package com.sav.autobase.webapp.converter.entity2model;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.Request;
import com.sav.autobase.webapp.models.RequestModel;

public class Request2Model implements Converter<Request, RequestModel> {

	public RequestModel convert(Request request) {
		return request2model(request);
	}

	private RequestModel request2model(Request request) {
		RequestModel requestModel = new RequestModel();
		requestModel.setId(request.getId());
		requestModel.setClient(new Client2Model().convert(request.getClient()));
		requestModel.setStartDate(request.getStartDate());
		requestModel.setEndDate(request.getEndDate());
		requestModel.setPlace(new Place2Model().convert(request.getPlace()));
		requestModel.setCountOfPassenger(request.getCountOfPassenger());
		requestModel.setDispatcher(new Dispatcher2Model().convert(request.getDispatcher()));
		requestModel.setComment(request.getComment());
		requestModel.setProcessed(request.getProcessed().name());
		return requestModel;
	}

}
