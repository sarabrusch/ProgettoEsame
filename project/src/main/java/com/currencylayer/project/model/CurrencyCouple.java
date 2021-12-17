package com.currencylayer.project.model;

/** Classe che modella la coppia di valute.
 *  @author Sara Bruschi 
 *  @author Marco Di Vita
 */

public class CurrencyCouple {
	
	private String source;
	private String acronym;
	private String couple;
	
	/** Costruttore di default 
	 * */
	public CurrencyCouple() {
		
	}
	
	/** Costruttore CurrencyCouple
	 * @param source sempre "USD"
	 * @param acronym da prendere in input per formare la coppia
	 */
	public CurrencyCouple(String source, String acronym) {
		this.source = source;
		this.acronym = acronym;
		couple = source+acronym;
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
	 * @return couple source+acronimo
	 * */
	public String getCouple() {
		return couple;
	}
	
	/** Setter di
	 * @param couple source+acronimo
	 * */
	public void setCouple(String couple) {
		this.couple = couple;
	}
	
	/** Metodo override di toString
	 * @return "Base currency: "+source+".\nQuote currency: "+acronym
	 */
	@Override
	public String toString() {
		return "Base currency: "+source+".\nQuote currency: "+acronym;
	}

}
