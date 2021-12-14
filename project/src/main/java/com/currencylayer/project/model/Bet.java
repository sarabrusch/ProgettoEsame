package com.currencylayer.project.model;
/**
 *  Classe che modella la scommessa descrivendone le sue caratteristiche
 *  principali.
 *  @author Sara Bruschi 
 *  @author Marco Di Vita
 *  **********************   */

public class Bet extends CurrencyCouple {
	
	private boolean isWinning;
	
	public Bet(Currency base, Currency quote) {
		super(base, quote);
	}
	
	public boolean getIsWinning() {
		return isWinning;
	}
	
	public void setIsWinning(boolean isWinning) {
		this.isWinning = isWinning;
	}
	
	public String toString() {
		return "Betting on: "+getBase()+" against: "+getQuote();
	}
	
	/*public void doBet(CurrencyCouple couple) {
		if(base.getExchangeRate() > quote.getExchangeRate()) {
			System.out.println("Winning Bet");
			isWinning = true;
		}
		else {
			System.out.println("Loosing Bet");
			isWinning = false;
		}
     }*/
}
