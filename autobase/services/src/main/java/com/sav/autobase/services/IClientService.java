package com.sav.autobase.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sav.autobase.datamodel.Request;
import com.sav.autobase.services.exception.DAOexception;

public interface IClientService {

	Request getRequest(Integer id) throws DAOexception;

	List<Request> getAllRequest() throws DAOexception;
	
	@Transactional
	void saveAllRequest(List<Request> requests) throws DAOexception;

	@Transactional
	void createRequest(Request request) throws DAOexception;

	@Transactional
	void modifyRequest(Request request) throws DAOexception;

	@Transactional
	void deleteRequest(Integer id) throws DAOexception;

}
