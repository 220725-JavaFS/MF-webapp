package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.revature.models.Users;

public class ORMTests {

	private ORM orm = Mockito.mock(ORM.class);
	private Users test = new Users(1, "user", "pass", 1);
	private List<Users> testList = new ArrayList<Users>();
	private ORMService ormService;

	
	@BeforeEach
	public void setMockORM() {
		ormService = new ORMService(orm);
	}
	
	@Test
	public void testStoreObject() {
		Mockito.when(orm.storeObject(test)).thenReturn(1);
		int id = ormService.storeObject(test);
		assertEquals(1, id);
	}

	@Test
	public void testGetObjects() {

		Mockito.when(orm.retriveObject(Users.class)).thenReturn(testList);
		List<Users> list = ormService.getObjects(Users.class);
		assertEquals(list, testList);
	}

	@Test
	public void testUpdateObject() {
		Mockito.when(orm.updateObject(test)).thenReturn(true);
		Boolean updated = ormService.updateObject(test);
		assertTrue(updated);
	}

	@Test
	public void testDeleteObject() {
		Mockito.when(orm.deleteObject(test)).thenReturn(true);
		Boolean updated = ormService.deleteObject(test);
		assertTrue(updated);
	}
}
