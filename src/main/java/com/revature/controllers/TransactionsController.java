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
import com.revature.models.Account;
import com.revature.models.Transactions;
import com.revature.models.Users;
import com.revature.services.ORMService;

public class TransactionsController extends HttpServlet {
	private ObjectMapper objectMapper = new ObjectMapper();
	private static Logger log = LoggerFactory.getLogger(AccountController.class);
	private ORMService ormService = new ORMService();
	private List<Account> accounts = ormService.getObjects(Account.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String URI = request.getRequestURI();
		log.info(URI);
		// localhost:8080/MF-webapp/transactions/{accountid}

		String[] urlSections = URI.split("/");
		try {
			int accountid = Integer.valueOf(urlSections[3]);

			// checks if account with that id exists
			if (accounts.stream().filter(a -> a.getId() == accountid).collect(Collectors.toList()).size() != 1) {
				response.setStatus(404);
				return;
			}

			List<Transactions> transactions = ormService.getObjects(Transactions.class);

			transactions = transactions.stream().filter(t -> t.getFromAccount() == accountid|| t.getToAccount() == accountid).collect(Collectors.toList());

			PrintWriter printWriter = response.getWriter();

			String json = objectMapper.writeValueAsString(transactions);
			log.info(json);

			printWriter.print(json);

			response.setStatus(200);
			response.setContentType("application/json");

		} catch (NumberFormatException e) {
			response.setStatus(404);
			return;
		}

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

		Transactions transaction = objectMapper.readValue(json, Transactions.class);

		String URI = request.getRequestURI();
		String[] urlSections = URI.split("/");
		try {
			int accountid = Integer.valueOf(urlSections[3]);

			// checks if account with that id exists
			if (accounts.stream().filter(a -> a.getId() == accountid).collect(Collectors.toList()).size() != 1) {
				response.setStatus(404);
				return;
			}
			// this ensures the account created is bound to the userid in the uri
			transaction.setFromAccount(accountid);
			if(ormService.storeObject(transaction) != -1) {
				response.setStatus(201);
				return;
			}else {
				response.setStatus(400);
				return;
			}

		} catch (NumberFormatException e) {
			response.setStatus(404);
			return;
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

		Transactions transaction = objectMapper.readValue(json, Transactions.class);

		String URI = request.getRequestURI();
		String[] urlSections = URI.split("/");
		try {
			int accountid = Integer.valueOf(urlSections[3]);

			// checks if account with that id exists
			if (accounts.stream().filter(a -> a.getId() == accountid).collect(Collectors.toList()).size() != 1) {
				response.setStatus(404);
				return;
			}
			// this ensures the account created is bound to the userid in the uri
			transaction.setFromAccount(accountid);
			if (ormService.updateObject(transaction)) {
				response.setStatus(200);
				return;
			} else {
				response.setStatus(400);
				return;
			}

		} catch (NumberFormatException e) {
			response.setStatus(404);
			return;
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

		List<Transactions> transactions = ormService.getObjects(Transactions.class);
		Transactions transaction = transactions.stream().filter(t -> t.getId() == id).collect(Collectors.toList()).get(0);

		if (ormService.deleteObject(transaction)) {
			response.setStatus(200);

		} else {
			response.setStatus(400);

		}
	}

}
