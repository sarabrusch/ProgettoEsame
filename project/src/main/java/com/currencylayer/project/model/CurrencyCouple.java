package com.currencylayer.project.model;



public class CurrencyCouple {
	
	private Currency base = new Currency();
	private Currency quote = new Currency();
	private double exchangeRate;
	
	public CurrencyCouple(Currency base, Currency quote) {
		this.base = base;
		this.quote = quote;
		this.exchangeRate = exchangeRate;
	}

	public Currency getBase() {
		return base;
	}

	public void setBase(Currency base) {
		this.base = base;
	}

	public Currency getQuote() {
		return quote;
	}

	public void setQuote(Currency quote) {
		this.quote = quote;
	}

	public double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	
	public String getCouple() {
		return base.getAcronym()+quote.getAcronym();
	}
	
	@Override
	public String toString() {
		return "Base currency: "+base+".\nQuote currency: "+quote;
	}

}
