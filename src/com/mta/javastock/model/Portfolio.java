package com.mta.javastock.model;

/**
 * This class represents a Portfolio of Stocks.
 * where the maximum of stocks in the Portfolio is 5.
 * 
 * @author MichalBerger
 * @since 26/4/2015
 */

public class Portfolio {
	
	public enum ALGO_RECOMMENDATION {
		BUY, SELL, REMOVE , HOLD 
	}
	
	private String title;
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private Stock[] stocks;
	private int portfolioSize;
	private float balance ;
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
		this.balance = 0 ;
	}
	
	public Portfolio(Portfolio portfolio ) {
		this(portfolio.getTitle());
		this.portfolioSize = portfolio.getPortfolioSize();
		this.updateBalance(portfolio.getBalance()); 
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

		if(portfolioSize == MAX_PORTFOLIO_SIZE){
			System.out.println("Can�t add new stock, portfolio can have only "+MAX_PORTFOLIO_SIZE+" stocks");
			return;
		}else if (stock == null){
			System.out.println("error!");
			return;
		}else {
			int i = this.findStock (stock.getSymbol());
			if(i != -1){
				System.out.println("Stock already exists in portfolio.");
				return;
			}
		}
		
		stocks[this.portfolioSize] = stock;
		stocks[this.portfolioSize].setStockQuantity(0); 
		this.portfolioSize++;
		return;
	}
	/**
	 * Find a stock in stocks array by symbol
	 * @param stockToFind
	 * @return index of the stock in the stocks array
	 * or return -1 if stock not found
	 */
	private int findStock (String stockToFind){
		for(int i = 0; i< this.portfolioSize; i++){
			if(stockToFind.equals(this.stocks[i].getSymbol())){
				return i;
			}
		}
		return -1;
	}
	/**
	 * Removes received stocked by symbol
	 */
public boolean removeStock(String stockName){
		
		if (stockName == null){
			System.out.println("The stock received is invalid!");
			return false;
		}
	
		int i = this.findStock (stockName);
			
		if(i>-1){
			if (portfolioSize > 1){
				this.sellStock(stocks[i].getSymbol(), -1);
				stocks[i] = stocks[this.portfolioSize-1];
				stocks[this.portfolioSize-1]=null;
				
			}else  if (this.portfolioSize == 1){
				this.sellStock(stocks[i].getSymbol(), -1);
				stocks[i]=null;
			}
			portfolioSize--;
			System.out.println("Stock "+stockName+" was deleted as per request");
			return true;
		}
	
	System.out.println("Stock was not found in this Portfolio");
	return false;
	}


/**
 * Method update stock quantity Depending sale
 * "-1 " means all specific stocks will be sold
 * @param symbol
 * @param quantity
 * @return
 */
	
	public boolean sellStock (String symbol, int quantity){
		if (symbol == null || quantity < -1 || quantity == 0 )
		{
			System.out.println("There is an error at stock symbol or stock quntity");
			return false;
		}
		
		int i = this.findStock (symbol);
		
		if(i>-1){
				if(stocks[i].getStockQuantity() < quantity )
				{
					System.out.println("Not enough stocks to sell");
					return false;
				}
				else if (quantity == -1)
				{
					this.updateBalance(this.stocks[i].getStockQuantity()*this.stocks[i].getBid());
					this.stocks[i].setStockQuantity(0);
					System.out.println("Entire stocks kind of ("+symbol+") - was sold succefully");
					return true;
				}
				else { 
					this.updateBalance(quantity*this.stocks[i].getBid());
					int currQuntity = this.stocks[i].getStockQuantity();
					this.stocks[i].setStockQuantity(currQuntity - quantity);
					System.out.println(quantity +" stocks kind of ("+symbol+") - was sold succefully");
					return true;
				}
			}
		
		System.out.println("Stock was not found in this Portfolio");
		return false; 
	}
	
	/**
	 * Method update the stock quantity depending Buy
	 * " -1" means all specific stocks will be used
	 * @param stock
	 * @param quantity
	 * @return
	 */
	
	public boolean buyStock (Stock stock , int quantity){
		
		if (stock == null || quantity < -1 )
		{
			System.out.println("There is an error at stock , or stock quntity");
			return false;
		}
		if (quantity * stock.getAsk() > this.balance)
		{
			System.out.println("Not enough balance to complete purchase.");
			return false;
		}
		int i = this.findStock (stock.getSymbol());
		
		if(i>-1){
				
				if (quantity == -1){
					int quantityToBuy = (int)this.balance / (int)this.stocks[i].getAsk();
					this.updateBalance(-quantityToBuy*this.stocks[i].getAsk());
					this.stocks[i].setStockQuantity(this.stocks[i].getStockQuantity()+quantityToBuy);
					System.out.println("Entire stock ("+stock.getSymbol()+") holdings that could be bought "
							+ "was bought succefully.");
					return true;
				}
				else {
					this.updateBalance(-quantity*this.stocks[i].getAsk());
					this.stocks[i].setStockQuantity(stocks[i].getStockQuantity()+quantity);
					System.out.println("An amount of "+quantity+" of stock ("+stock.getSymbol()+") was bought succefully");
					return true;
				}
			}
		if(i == MAX_PORTFOLIO_SIZE){
			System.out.println("Please note that the portfolio has reached it's maximum stock capacity.");
			return false;
		}
		
		if (quantity == -1){ // when we buy a new stock we also need to add it to array
			this.addStock(stock);
			int quantityToBuy = (int)this.balance/(int)this.stocks[i].getAsk();
			this.updateBalance(-(quantityToBuy*this.stocks[this.portfolioSize-1].getAsk()));
			this.stocks[i].setStockQuantity(this.stocks[this.portfolioSize-1].getStockQuantity()+quantityToBuy);
			System.out.println("Entire stock ("+stock.getSymbol()+") holdings that could be bought "
					+ "was bought succefully.");
			return true;
		} else {
			this.addStock(stock); //add the stock to portfolioSize-1 in the stocks array.
			this.updateBalance(-quantity*this.stocks[portfolioSize -1].getAsk());
			this.stocks[this.portfolioSize -1].setStockQuantity(quantity);
			System.out.println("Stock "+stock.getSymbol()+" was added successfuly to the portfolio. With quantity of "
					+ quantity+" stocks.");
			return true;

		}
	}
	
	/**
	 * Method calculates the portfolio's total stocks value by using another methods 
	 * @return
	 */
	public float getStocksValue(){  
		float totalValue =0;
		for(int i = 0; i<this.portfolioSize ;i++){
			totalValue += this.stocks[i].getStockQuantity()*this.stocks[i].getBid();
		}
		return totalValue;		
	}

	
	public String getHtmlString(){
		
		String res = new String();
		res = res+"<h1>"+getTitle()+"</h1> <br>";
		
		for(int i=0; i<portfolioSize;i++)
		{
			if(stocks[i] != null)
			{
			res = res + stocks[i].getHtmlDescription()+"<br>";
			}
		}
		res += "Total Portfolio Value :"+this.getTotalValue()+ "$.<br>"+
		"Total Stocks Value :"+this.getStocksValue()+"$. <br>"+"Balance :"+this.balance+"$.";
		return res;
	}
	
	/**
	 * Method receives amount and adds it to current balance
	 * @param amount
	 */
	
	public void updateBalance(float amount){
		float currBalance = this.balance + amount;
		if(currBalance < 0)
		{
			System.out.println("You can not change balance to negative amount");
		}
		else{
			this.balance = currBalance ;
		}
	}
	/**
	 * Method calculates the portfolio's total value
	 * @return
	 */
	public float getTotalValue(){
		
		return this.getStocksValue()+this.balance;		
	}
	public Stock[] getStocks(){
		return stocks;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}
	public float getBalance() {
		return balance;
	}


}