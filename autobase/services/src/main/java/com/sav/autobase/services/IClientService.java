package com.sav.autobase.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.services.exception.ServiceException;
import com.sav.autobase.services.exception.ModifyException;

public interface IClientService {

	Request getRequest(Integer id) throws ServiceException;

	List<Request> getAllRequest(Users user) throws ServiceException;
	
	@Transactional
	void createRequest(Request request) throws ServiceException, ModifyException;

	@Transactional
	void modifyRequest(Request request) throws ServiceException, ModifyException;

	@Transactional
	void deleteRequest(Integer id) throws ServiceException;


}
