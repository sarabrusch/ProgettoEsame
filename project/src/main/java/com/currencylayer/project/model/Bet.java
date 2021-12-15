package com.currencylayer.project.model;

/**
 *  Classe che modella la scommessa descrivendone le sue caratteristiche
 *  principali.
 *  @author Sara Bruschi 
 *  @author Marco Di Vita
 *  **********************   */

public class Bet extends CurrencyCouple {

	private boolean isWinning;
	
	/*
	 * Costruttore bet
	 */

	public Bet() {
		super();
	}
	
	/*
	 * Costruttore bet
	 * @param source,acronym,exchangeRate
	 */

	public Bet(String source, String acronym, double exchangeRate) {
		super(source, acronym, exchangeRate);
	}
	
	/*
	 * getter parametro che descrive la situazione della scommessa
	 * @return isWinning
	 */

	public boolean getIsWinning() {
		return isWinning;
	}

	/*
	 * setter parametro che descrive la situazione della scommessa
	 * @param isWinning
	 */
	
	public void setIsWinning(boolean isWinning) {
		this.isWinning = isWinning;
	}

	/*
	 * Metodo toString
	 * @return "Betting on: "+getSource()+" and: "+getAcronym()
	 */
	
	public String toString() {
		return "Betting on: "+getSource()+" and: "+getAcronym();
	}

}
