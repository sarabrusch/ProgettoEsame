package com.currencylayer.project.service;

/*
 * Interfaccia per la gestione della scommessa
 * @author Marco Di Vita
 * @author Sara Bruschi
 */

public interface BetService { 
	
	public String doBet (String acronym1, String acronym2, String acronym3);
	public String betResult();
	
}
