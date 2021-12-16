package com.currencylayer.project.model;

/** Classe che modella la coppia di valute descrivendone le caratteristiche
 *  principali, quali la coppia di acronimi e il tasso di cambio relativo.
 *  @author Sara Bruschi 
 *  @author Marco Di Vita
 */

public class CurrencyCouple {
	
	private String source;
	private String acronym;
	private double exchangeRate;
	
	/** Costruttore di default 
	 * */
	public CurrencyCouple() {
		
	}
	
	/** Costruttore CurrencyCouple
	 * @param source sempre "USD"
	 * @param acronym da prendere in input per formare la coppia
	 * @param exchangeRate tasso di cambio della coppia
	 */
	public CurrencyCouple(String source, String acronym, double exchangeRate) {
		this.source = source;
		this.acronym = acronym;
		this.exchangeRate = exchangeRate;
	}

	/** Getter di
	 * @return source acronimo della source
	 * */
	public String getSource() {
		return source;
	}

	/** Setter di
	 * @param source acronimo della source
	 * */
	public void setSource(String source) {
		this.source = source;
	}

	/** Getter di
	 * @return acronym acronimo della valuta scelta
	 * */
	public String getAcronym() {
		return acronym;
	}

	/** Setter di
	 * @param acronym acronimo della valuta scelta
	 * */
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	/** Getter di
	 * @return exchangeRate tasso di cambio della coppia di valute
	 * */
	public double getExchangeRate() {
		return exchangeRate;
	}

	/** Setter di
	 * @param exchangeRate tasso di cambio della coppia di valute
	 * */
	public void setExchangeRate(double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	
	/** Metodo override di toString
	 * @return "Base currency: "+source+".\nQuote currency: "+acronym
	 */
	@Override
	public String toString() {
		return "Base currency: "+source+".\nQuote currency: "+acronym;
	}

}
