package com.sav.autobase.dao.impl.db;

import java.util.List;

import com.sav.autobase.dao.impl.db.exceptions.DaoException;
import com.sav.autobase.datamodel.Request;

public interface IRequestDao extends IAbstractModelDao<Request> {

	List<Request> getAll() throws DaoException;

}
