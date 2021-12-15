package com.currencylayer.project.model;



public class CurrencyCouple {
	
	private String source;
	private String acronym;
	private double exchangeRate;
	
	public CurrencyCouple() {
		
	}
	
	public CurrencyCouple(String source, String acronym, double exchangeRate) {
		this.source = source;
		this.acronym = acronym;
		this.exchangeRate = exchangeRate;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	
	@Override
	public String toString() {
		return "Base currency: "+source+".\nQuote currency: "+acronym;
	}

}
