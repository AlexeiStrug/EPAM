package com.sav.autobase.dao.xml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.sav.autobase.dao.api.IGenericDao;
import com.sav.autobase.dao.api.IUsersDao;
import com.sav.autobase.dao.api.filter.UserSearchCriteria;
import com.sav.autobase.dao.xml.impl.wrapper.XmlModelWrapper;
import com.sav.autobase.datamodel.Users;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class UsersDaoXmlImpl implements IGenericDao<Users>, IUsersDao {

	private final XStream xstream = new XStream(new DomDriver());

	@Value("${root.folder}")
	private String rootFolder;

	private File getFile() {
		File file = new File(rootFolder + "users.xml");
		return file;
	}

	@Override
	public Users findByloginPassword(String login, String password) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Users> findByCriteria(UserSearchCriteria criteria) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Users getById(Integer id) {

		File file = getFile();

		XmlModelWrapper<Integer, Users> wrapper = (XmlModelWrapper<Integer, Users>) xstream.fromXML(file);
		List<Users> users = wrapper.getRows();
		for (Users user : users) {
			if (user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public Users insert(Users user) {
		File file = getFile();

		XmlModelWrapper<Integer, Users> wrapper = (XmlModelWrapper<Integer, Users>) xstream.fromXML(file);
		List<Users> users = wrapper.getRows();
		Integer lastId = wrapper.getLastId();
		int newId = lastId + 1;

		user.setId(newId);
		users.add(user);

		wrapper.setLastId(newId);
		writeNewData(file, wrapper);
		return user;
	}

	@Override
	public Users update(Users user) {
		File file = getFile();

		XmlModelWrapper<Integer, Users> wrapper = (XmlModelWrapper<Integer, Users>) xstream.fromXML(file);
		List<Users> users = wrapper.getRows();
		for (Users userItem : users) {
			if (userItem.getId().equals(user.getId())) {
				// TODO copy all properties
				break;
			}
		}

		writeNewData(file, wrapper);
		return user;
	}

	@Override
	public void delete(Integer id) {
		File file = getFile();

		XmlModelWrapper<Integer, Users> wrapper = (XmlModelWrapper<Integer, Users>) xstream.fromXML(file);
		List<Users> users = wrapper.getRows();
		Users found = null;
		for (Users user : users) {
			if (user.getId().equals(id)) {
				found = user;
				break;
			}
		}
		if (found != null) {
			users.remove(found);
			writeNewData(file, wrapper);
		}
	}

	@Override
	public List<Users> getAll() {
		File file = getFile();

		XmlModelWrapper<Integer, Users> wrapper = (XmlModelWrapper<Integer, Users>) xstream.fromXML(file);
		return wrapper.getRows();
	}

	private void writeNewData(File file, XmlModelWrapper obj) {
		try {
			xstream.toXML(obj, new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
