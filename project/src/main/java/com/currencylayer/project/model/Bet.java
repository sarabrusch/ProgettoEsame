package com.currencylayer.project.model;
/**
 *  Classe che modella la scommessa descrivendone le sue caratteristiche
 *  principali.
 *  @author Sara Bruschi 
 *  @author Marco Di Vita
 *  **********************   */

public class Bet extends CurrencyCouple {

	private boolean isWinning;
	
	public Bet() {
		super();
	}
	
	public Bet(String source, String acronym, double exchangeRate) {
		super(source, acronym, exchangeRate);
	}
	
	public boolean getIsWinning() {
		return isWinning;
	}
	
	public void setIsWinning(boolean isWinning) {
		this.isWinning = isWinning;
	}
	
	public String toString() {
		return "Betting on: "+getSource()+" and: "+getAcronym();
	}
	
}
