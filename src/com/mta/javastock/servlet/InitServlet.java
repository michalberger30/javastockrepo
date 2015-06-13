package com.mta.javastock.servlet;
import java.io.IOException;

import javax.servlet.ServletException;

import org.algo.service.ServiceManager;

import com.mta.javastock.service.PortfolioManager;
@SuppressWarnings({ "serial", "unused" })
public class InitServlet extends javax.servlet.http.HttpServlet {

	public void init() throws ServletException {
		PortfolioManager pm = new PortfolioManager();
		ServiceManager.setPortfolioManager(pm);
	}
}
