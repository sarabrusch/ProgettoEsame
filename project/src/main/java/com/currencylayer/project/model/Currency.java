package com.currencylayer.project.model;

/**
 *  Classe che modella la valuta descrivendone le sue caratteristiche
 *  principali, quali il nome e l'acronimo che la condraddistingue.
 *  @author Sara Bruschi 
 *  @author Marco Di Vita
 *  **********************   */

public class Currency {

	private String name;
	private String acronym;

	//costruttore
	public Currency() {

	}

	public Currency(String name, String acronym) {
		this.name = name;
		this.acronym = acronym;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym() {
		this.acronym = acronym;
	}

	@Override
	public String toString() {
		return "Currency: "+name+" Acronym: "+acronym;
	}

}



