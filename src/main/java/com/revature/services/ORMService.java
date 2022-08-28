package com.revature.services;

import java.util.List;
import java.util.stream.Collectors;

import com.revature.utils.ORMUtil;

public class ORMService {
	private ORM orm = ORMUtil.getORM();

	public <T> List<T> getObjects(Class<T> clazz) {
		orm = ORMUtil.getORM();
		if(orm == null) {
			System.out.println("oops");
		}
		return orm.retriveObject(clazz);
	}
	
	public boolean deleteObject(Object object) {
		return orm.deleteObject(object);
	}

	public boolean updateObject(Object object) {
		return orm.updateObject(object);

	}

	public int storeObject(Object object) {
		return orm.storeObject(object);
	}
}
