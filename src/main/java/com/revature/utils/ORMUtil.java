package com.revature.utils;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.services.ORM;

public class ORMUtil {

	// singleton means that only one object can exist at a time
	private static ORM orm;
	private static Logger log = LoggerFactory.getLogger(ORMUtil.class);

	public static ORM getORM() {
		if (orm != null) {
			return orm;
		} else {
			try {
				orm = new ORM(ConnectionUtil.getConnection());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getLocalizedMessage(), e);
			}
			return orm;
		}

	}

}
