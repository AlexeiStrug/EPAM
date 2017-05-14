package com.sav.autobase.webapp.converter.entity2model;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.Request;
import com.sav.autobase.webapp.models.ClientRequestModel;

public class ClientRequest2Model implements Converter<Request, ClientRequestModel> {

	public ClientRequestModel convert(Request request) {
		return clientRequest2model(request);
	}

	private ClientRequestModel clientRequest2model(Request request) {
		ClientRequestModel requestModel = new ClientRequestModel();
		requestModel.setId(request.getId());
		requestModel.setClient(new Client2Model().convert(request.getClient()));
		requestModel.setStartDate(request.getStartDate());
		requestModel.setEndDate(request.getEndDate());
		requestModel.setPlace(new Place2Model().convert(request.getPlace()));
		requestModel.setCountOfPassenger(request.getCountOfPassenger());
		requestModel.setComment(request.getComment());
		requestModel.setProcessed(request.getProcessed().name());
		return requestModel;
	}

}
