package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Users;
import com.revature.services.ORMService;

public class UserController extends HttpServlet {

	private ObjectMapper objectMapper = new ObjectMapper();
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	private ORMService ormService = new ORMService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String URI = request.getRequestURI();
		log.info(URI);
		// localhost:8080/MF-webapp/users/{userid}

		String[] urlSections = URI.split("/");

		List<Users> users = ormService.getObjects(Users.class);
		// this shows all of the users in the database

		String json = objectMapper.writeValueAsString(users);
		log.info(json);

		PrintWriter printWriter = response.getWriter();

		printWriter.print(json);

		response.setStatus(200);

		response.setContentType("application/json");

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();

		BufferedReader reader = request.getReader();

		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String json = new String(sb);
		log.info(json);

		Users user = objectMapper.readValue(json, Users.class);

		if(ormService.storeObject(user) != -1) {
			response.setStatus(201);
			return;
		}else {
			response.setStatus(400);

		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();

		BufferedReader reader = request.getReader();

		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String json = new String(sb);
		log.info(json);

		Users user = objectMapper.readValue(json, Users.class);

		if (ormService.updateObject(user)) {
			response.setStatus(200);

		} else {
			response.setStatus(400);

		}

	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BufferedReader reader = request.getReader();

		String line = reader.readLine();

		int id;

		try {
			id = Integer.valueOf(line);

		} catch (NumberFormatException e) {
			log.error(e.getLocalizedMessage(), e);
			response.setStatus(400);
			return;
		}

		List<Users> users = ormService.getObjects(Users.class);
		Users user = users.stream().filter(u -> u.getId() == id).collect(Collectors.toList()).get(0);

		if (ormService.deleteObject(user)) {
			response.setStatus(200);

		} else {
			response.setStatus(400);

		}
	}
}
