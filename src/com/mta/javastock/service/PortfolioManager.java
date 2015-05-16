package com.mta.javastock.service;

import java.util.Calendar;
import java.util.Date;

import com.mta.javastock.model.Portfolio;
import com.mta.javastock.model.Stock;


public class PortfolioManager {

	public Portfolio getPortfolio(){
		Portfolio portfolio = new Portfolio("Exercise 7 portfolio");
		portfolio.updateBalance(10000);
		
		
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 10, 15);
		
		Date date1 = cal.getTime();
		Stock s1 = new Stock("PIH",10F,8.5F, date1);
		Date date2 = cal.getTime();
		Stock s2 = new Stock("AAL", 30F, 25.5F, date2);
		Date date3 = cal.getTime();
		Stock s3 = new Stock("CAAS", 20F, 15.5F, date3);
		
		portfolio.buyStock(s1, 20);
		portfolio.buyStock(s2, 30);
		portfolio.buyStock(s3, 40);
		
		portfolio.sellStock("AAL", -1);
		portfolio.removeStock("CAAS");
		
		return portfolio;	
	}	

}
