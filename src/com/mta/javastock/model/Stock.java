package com.mta.javastock.model;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.mta.javastock.model.Portfolio.ALGO_RECOMMENDATION;

public class Stock {
	
	private String symbol;
	private float ask;
	private float bid;
	private Date date;
	private ALGO_RECOMMENDATION recommendation;
	private int stockQuantity;
	
	
	DateFormat dateFt = new SimpleDateFormat("MM/dd/yyyy");
	
	public Stock(String symbol, float ask, float bid, Date date) {
		this.symbol = symbol;
		this.ask = ask;
		this.bid = bid;
		this.date = date;
		this.stockQuantity = 0;
		this.recommendation = ALGO_RECOMMENDATION.HOLD;
		
	}
	
	public Stock (Stock stock){
		this (stock.getSymbol(), stock.getAsk(), stock.getBid(), new Date(stock.getDate().getTime()));
		this.recommendation = stock.getRecommendation();
		this.stockQuantity = stock.getStockQuantity();
		}

	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public float getAsk() {
		return ask;
	}
	public void setAsk(float ask) {
		this.ask = ask;
	}
	public float getBid() {
		return bid;
	}
	public void setBid(float bid) {
		this.bid = bid;
	}
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	/**
	 * Get  get Html description with all portfolio details
	 */
	public String getHtmlDescription(){
		String dateStr = dateFt.format(date);
		String result = new String("<b> Stock symbol is: </b>" + getSymbol()+"<b> ask: </b>"+getAsk() +"<b> Bid: </b>"+getBid()+"<b> Date: </b>"+ dateStr);
		return result;
	}

	public ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
		this.recommendation = recommendation;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

}
	
