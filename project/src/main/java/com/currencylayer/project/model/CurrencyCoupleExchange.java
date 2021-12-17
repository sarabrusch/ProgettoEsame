package com.currencylayer.project.model;

/**
 * sottoclasse di CurrencyCouple che aggiunge l'exchangeRate 
 * alla coppia di valute
 * @author Marco Di Vita
 * @author Sara Bruschi
 */
public class CurrencyCoupleExchange extends CurrencyCouple{
	
	private Double exchangeRate;
	
	/** Costruttore di CurrencyCoupleExchange
	 * @param source
	 * @param acronym
	 * @param exchangeRate
	 * */
	public CurrencyCoupleExchange(String source, String acronym,Double exchangeRate) {
		super(source,acronym);
		this.exchangeRate = exchangeRate;
	}

	/** Costruttore di default 
	 * */
	public CurrencyCoupleExchange() {
		// TODO Auto-generated constructor stub
	}

	/** Getter di
	 * @return exchangeRate
	 * */
	public Double getExchangeRate() {
		return exchangeRate;
	}

	/** Setter del exchangeRate 
	 * @param exchangeRate
	 * */
	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	/** Metodo override di toString
	 * @return "source: "+getSource()+" acronym: "+getAcronym()+" exchangeRate: "+exchangeRate
	 */
	@Override
	public String toString() {
		return "source: "+getSource()+" acronym: "+getAcronym()+" exchangeRate: "+exchangeRate;
	}

}
