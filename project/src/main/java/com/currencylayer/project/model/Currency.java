package com.currencylayer.project.model;

/** Classe che modella la valuta descrivendone le sue caratteristiche
 *  principali, quali il nome e l'acronimo che la condraddistinguono.
 *  @author Sara Bruschi 
 *  @author Marco Di Vita
 */

public class Currency {

	private String name;
	private String acronym;

	/** Costruttore di default
	 * */
	public Currency() {

	}

	/** Costruttore di Currency 
	 * @param name
	 * @param acronym
	 * */
	public Currency(String name, String acronym) {
		this.name = name;
		this.acronym = acronym;
	}

	/** Getter di
	 * @return name nome della currency
	 * */
	public String getName() {
		return name;
	}

	/** Setter del nome della currency 
	 * @param name nome della currency
	 * */
	public void setName(String name) {
		this.name = name;
	}

	/** Getter di
	 * @return acronym acronimo della currency
	 * */
	public String getAcronym() {
		return acronym;
	}

	/** Setter dell'acronimo della currency 
	 * @param acronym acronimo della currency
	 * */
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	/** Metodo override di toString
	 * @return "Currency: "+name+" Acronym: "+acronym
	 */
	@Override
	public String toString() {
		return "Currency: "+name+" Acronym: "+acronym;
	}

}



