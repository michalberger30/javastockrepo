package com.mta.javastock.model;

/**
 * This class represents a Portfolio of Stocks.
 * where the maximum of stocks in the Portfolio is 5.
 * 
 * @author MichalBerger
 * @since 26/4/2015
 */

public class Portfolio {
	
	private String title;
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private Stock[] stocks;
	private int portfolioSize;
	
	/**
	 * Constractor of Portfolio.
	 * Receives the title of the portfolio.
	 * Creates an instance of an array of Stocks
	 * Set the Portfolio Size to start as 0.
	 * @param title
	 * 		  the title of the Portfolio
	 */
	
	
	public Portfolio(String title) {
		this.title = title;
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = 0;
		
	}
	
	public Portfolio(Portfolio portfolio ) {
		this(portfolio.getTitle());
		this.portfolioSize = portfolio.getPortfolioSize();
		for (int i=0;i<portfolioSize; i++)
		{
			this.stocks[i] = new Stock(portfolio.getStocks()[i]);
		}
	}
	/**
	 * Add Stock to the portfolio's array of stocks.
	 * @author Michal Berger
	 */
	
	
	public void addStock(Stock stock){ 
		
		if(portfolioSize<MAX_PORTFOLIO_SIZE && stock != null)
		{
			stocks[this.portfolioSize] = stock;
			portfolioSize++;
		}
		else{
			System.out.println("Sorry, portfolio is full, or stock is null");
		}
	}
	/**
	 * Removes received stocked by symbol
	 */
	public void removeStock(String symbol)
	{
		for(int i = 0; i < portfolioSize; i++){
			if(this.stocks[i].getSymbol().equals(symbol)){
				if(i != portfolioSize-1 && portfolioSize > 1)
					for(int j = i; j < portfolioSize-1; j++){
						this.stocks[j] = new Stock(this.stocks[j+1]);
					}
			}
		}
		portfolioSize--;
	}
	
	public String getHtmlString(){
		
		String res = new String();
		res = res+"<h1>"+getTitle()+"</h1> <br>";
		
		for(int i=0; i<portfolioSize;i++)
		{
			res = res + stocks[i].getHtmlDescription()+"<br>";
		}
		
		return res;	
	}
	
	public Stock[] getStock()
	{
		return stocks;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Stock[] getStocks() {
		return stocks;
	}
	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}
	public int getPortfolioSize() {
		return portfolioSize;
	}
	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}
	public static int getMaxPortfolioSize() {
		return MAX_PORTFOLIO_SIZE;
	}


}