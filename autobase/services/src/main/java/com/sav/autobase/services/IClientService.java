package com.sav.autobase.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sav.autobase.datamodel.Request;
import com.sav.autobase.services.exception.DAOException;
import com.sav.autobase.services.exception.ModifyException;

public interface IClientService {

	Request getRequest(Integer id) throws DAOException;

	List<Request> getAllRequest() throws DAOException;
	
	@Transactional
	void saveAllRequest(List<Request> requests) throws DAOException;

	@Transactional
	void createRequest(Request request) throws DAOException;

	@Transactional
	void modifyRequest(Request request) throws DAOException, ModifyException;

	@Transactional
	void deleteRequest(Integer id) throws DAOException;

}
