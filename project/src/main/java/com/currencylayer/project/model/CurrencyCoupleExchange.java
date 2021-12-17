package com.currencylayer.project.model;

public class CurrencyCoupleExchange extends CurrencyCouple{
	
	private Double exchangeRate;
	
	public CurrencyCoupleExchange(String source, String acronym,Double exchangeRate) {
		super(source,acronym);
		this.exchangeRate = exchangeRate;
	}

	public CurrencyCoupleExchange() {
		// TODO Auto-generated constructor stub
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	@Override
	public String toString() {
		return "source: "+getSource()+" acronym: "+getAcronym()+" exchangeRate: "+exchangeRate;
	}

}
