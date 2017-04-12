package com.sav.autobase.dao.impl.db;

import java.util.List;

import com.sav.autobase.datamodel.Request;

public interface IRequestDao extends IGenericDao<Request> {

	Request joinGetById(Integer id);
	
	Request joinFindByProcessed(String processed);

	List<Request> joinGetAll();

}
