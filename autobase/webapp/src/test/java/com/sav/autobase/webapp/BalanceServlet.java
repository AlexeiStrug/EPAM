package com.sav.autobase.webapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.http.HttpMethod;
import org.springframework.stereotype.Controller;

@Controller
public class BalanceServlet extends HttpServlet {

	private Random rnd = new Random();

	HttpClient client = new HttpClient();

	List<String> list = new ArrayList<String>();

	public void init() {
		list.add("8081");
		list.add("8082");
		list.add("8083");
		list.add("8084");

		try {
			client.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getRandomList(List<String> list) {

		int index = rnd.nextInt(list.size());
		return list.get(index);

	}

	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ContentResponse response = client
					.newRequest("http://localhost:" + getRandomList(list) + req.getServletPath())
					.method(HttpMethod.HEAD).send();
			resp.setStatus(200);
			resp.getOutputStream().write(response.getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ContentResponse response = client
					.newRequest("http://localhost:" + getRandomList(list) + req.getServletPath()).method(HttpMethod.GET)
					.header("Authorization", req.getHeader("Authorization")).send();
			resp.setStatus(200);
			resp.getOutputStream().write(response.getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ContentResponse response = client
					.newRequest("http://localhost:" + getRandomList(list) + req.getServletPath())
					.method(HttpMethod.POST).header("Authorization", req.getHeader("Authorization")).send();
			resp.setStatus(200);
			resp.getOutputStream().write(response.getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ContentResponse response = client
					.newRequest("http://localhost:" + getRandomList(list) + req.getServletPath()).method(HttpMethod.PUT)
					.header("Authorization", req.getHeader("Authorization")).send();
			resp.setStatus(200);
			resp.getOutputStream().write(response.getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ContentResponse response = client
					.newRequest("http://localhost:" + getRandomList(list) + req.getServletPath())
					.method(HttpMethod.DELETE).header("Authorization", req.getHeader("Authorization")).send();
			resp.setStatus(200);
			resp.getOutputStream().write(response.getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
