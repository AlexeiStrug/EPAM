package com.sav.autobase.dao.db.cache;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.sav.autobase.datamodel.Vehicle;

@Component
public class SearchVehicleCache extends Cache<List<Vehicle>>{

	@Override
	protected String getCacheName() {
		return "vehicleSearch:";
	}

	@Override
	protected Integer getLifeTime() {
		return 60;
	}

}
