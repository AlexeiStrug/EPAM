package com.sav.autobase.services.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sav.autobase.dao.impl.db.ITripDao;
import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.services.ITripService;

@Service
public class TripService implements ITripService{

	@Inject
	ITripDao tripDao;
	
	@Override
	public Trip getById(Integer id) {
		return tripDao.getById(id);
	}
	
	

}
