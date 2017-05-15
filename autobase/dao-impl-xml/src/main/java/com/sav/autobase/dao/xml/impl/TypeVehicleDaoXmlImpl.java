package com.sav.autobase.dao.xml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.sav.autobase.dao.api.IAdminUtilityProcedures;
import com.sav.autobase.dao.api.IGenericDao;
import com.sav.autobase.dao.api.ITripDao;
import com.sav.autobase.dao.api.IUsersDao;
import com.sav.autobase.dao.api.IVehicleDao;
import com.sav.autobase.dao.api.filter.UserSearchCriteria;
import com.sav.autobase.dao.api.filter.VehicleSerachCriteria;
import com.sav.autobase.dao.xml.impl.wrapper.XmlModelWrapper;
import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.TypeVehicle;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Repository
public class TypeVehicleDaoXmlImpl implements IGenericDao<TypeVehicle> {

	private final XStream xstream = new XStream(new DomDriver());

	@Value("${root.folder}")
	private String rootFolder;

	private File getFile() {
		File file = new File(rootFolder + "users.xml");
		return file;
	}

	@Override
	public TypeVehicle getById(Integer t) {
		throw new UnsupportedOperationException();
	}

	@Override
	public TypeVehicle insert(TypeVehicle entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public TypeVehicle update(TypeVehicle entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(Integer t) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public List<TypeVehicle> getAll() {
		throw new UnsupportedOperationException();
	}


}
