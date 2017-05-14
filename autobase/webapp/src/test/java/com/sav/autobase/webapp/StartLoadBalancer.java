package com.sav.autobase.webapp;

import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

public class StartLoadBalancer {

	public static void main(String[] args) throws Exception {

		startInstance(8080);

	}

	private static void startInstance(int port) throws Exception {

		Server server = new Server();

		HttpConfiguration http_config = new HttpConfiguration();
		http_config.setOutputBufferSize(32768);

		ServerConnector http = new ServerConnector(server, new HttpConnectionFactory(http_config));
		http.setPort(port);
		http.setIdleTimeout(1000 * 60 * 60);

		server.addConnector(http);

		WebAppContext contex = new WebAppContext();
		contex.setServer(server);
		contex.setContextPath("/");
		contex.setWar("src/main/webapp");

		server.setHandler(contex);

		BalanceServlet balanceServlet = new BalanceServlet();
		contex.addServlet(new ServletHolder(balanceServlet), "/**");

		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(100);
		}
	}
}
