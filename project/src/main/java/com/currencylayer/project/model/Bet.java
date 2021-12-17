package com.currencylayer.project.model;

/** Classe che modella la scommessa estendendo la classe CurrencyCouple e 
 * descrivendone le caratteristiche principali, 
 * quali la coppia di valute coinvolta e il risultato.
 *  @author Sara Bruschi 
 *  @author Marco Di Vita
 */

public class Bet extends CurrencyCouple {

	private boolean isWinning ;
	
	/** Costruttore Bet di default
	 */
    public Bet() {
		super();
	}
	
	/** Costruttore Bet
	 * @param source che è sempre "USD"
	 * @param acronym da inserire in input
	 * @param isWinning che definisce se la scommessa è vincente o meno
	 */
    public Bet(String source, String acronym,boolean isWinning) {
		super(source, acronym);
		this.isWinning = isWinning;
	}
	
	/** Getter parametro che descrive la situazione della scommessa
	 * @return isWinning è true se la scommessa è stata vinta, è false altrimenti.
	 */
    public boolean getIsWinning() {
		return isWinning;
	}

	/** Setter parametro che descrive la situazione della scommessa
	 * @param isWinning 
	 */
	public void setIsWinning(boolean isWinning) {
		this.isWinning = isWinning;
	}

	/** Metodo override di toString
	 * @return "Betting on: "+getSource()+" and: "+getAcronym()
	 */
	@Override
	public String toString() {
		return "Betting on: "+getSource()+" and: "+getAcronym()+" with bet result: "+getIsWinning();
	}

}
