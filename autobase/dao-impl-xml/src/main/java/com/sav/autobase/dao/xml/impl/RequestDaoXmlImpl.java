package com.sav.autobase.dao.xml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.sav.autobase.dao.api.IGenericDao;
import com.sav.autobase.dao.api.IRequestDao;
import com.sav.autobase.dao.xml.impl.wrapper.XmlModelWrapper;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.StatusRequest;
import com.sav.autobase.datamodel.Users;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Repository
public class RequestDaoXmlImpl implements IGenericDao<Request>, IRequestDao {

	private final XStream xstream = new XStream(new DomDriver());

	@Value("${root.folder}")
	private String rootFolder;

	private File getFile() {
		File file = new File(rootFolder + "request.xml");
		return file;
	}

	private void writeNewData(File file, XmlModelWrapper obj) {
		try {
			xstream.toXML(obj, new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Request joinGetById(Integer id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Request joinFindByProcessed(StatusRequest status) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Request updateClientRequest(Request request) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Request> joinGetAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Request> joinGetAllbyUser(Users user) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Request getById(Integer id) {
		File file = getFile();

		XmlModelWrapper<Integer, Request> wrapper = (XmlModelWrapper<Integer, Request>) xstream.fromXML(file);
		List<Request> requests = wrapper.getRows();
		for (Request request : requests) {
			if (request.getId().equals(id)) {
				return request;
			}
		}
		return null;
	}

	@Override
	public Request insert(Request request) {
		File file = getFile();

		XmlModelWrapper<Integer, Request> wrapper = (XmlModelWrapper<Integer, Request>) xstream.fromXML(file);
		List<Request> requests = wrapper.getRows();
		Integer lastId = wrapper.getLastId();
		int newId = lastId + 1;

		request.setId(newId);
		requests.add(request);

		wrapper.setLastId(newId);
		writeNewData(file, wrapper);
		return request;
	}

	@Override
	public Request update(Request request) {
		File file = getFile();

		XmlModelWrapper<Integer, Request> wrapper = (XmlModelWrapper<Integer, Request>) xstream.fromXML(file);
		List<Request> requests = wrapper.getRows();
		for (Request requestItem : requests) {
			if (requestItem.getId().equals(request.getId())) {
				// TODO copy all properties
				break;
			}
		}

		writeNewData(file, wrapper);
		return request;
	}

	@Override
	public void delete(Integer id) {
		File file = getFile();

		XmlModelWrapper<Integer, Request> wrapper = (XmlModelWrapper<Integer, Request>) xstream.fromXML(file);
		List<Request> requests = wrapper.getRows();
		Request found = null;
		for (Request request : requests) {
			if (request.getId().equals(id)) {
				found = request;
				break;
			}
		}
		if (found != null) {
			requests.remove(found);
			writeNewData(file, wrapper);
		}

	}

	@Override
	public List<Request> getAll() {
		File file = getFile();

		XmlModelWrapper<Integer, Request> wrapper = (XmlModelWrapper<Integer, Request>) xstream.fromXML(file);
		return wrapper.getRows();
	}

	@Override
	public List<Request> joinGetAllByStatus(StatusRequest status) {
		throw new UnsupportedOperationException();
	}

}
