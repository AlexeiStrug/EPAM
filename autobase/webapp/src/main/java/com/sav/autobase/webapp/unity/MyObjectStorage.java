package com.sav.autobase.webapp.unity;

import com.sav.autobase.datamodel.Users;

public class MyObjectStorage {

	static private ThreadLocal<Users> threadLocal = new ThreadLocal<Users>();

	public static ThreadLocal<Users> getThreadLocal() {
		return threadLocal;
	}
}
