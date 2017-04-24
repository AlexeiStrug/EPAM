package com.sav.autobase.dao.api;

import java.util.List;

import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.StatusRequest;

public interface IRequestDao extends IGenericDao<Request> {

	Request joinGetById(Integer id);
	
	Request joinFindByProcessed(StatusRequest status);
	
	Request updateClientRequest(Request request);

	List<Request> joinGetAll();

}