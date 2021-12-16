package com.currencylayer.project.service;

import org.json.simple.JSONObject;

import com.currencylayer.project.exceptions.CurrencyNotFoundException;


/**
 * Interfaccia per la gestione delle scommesse
 * @author Marco Di Vita
 * @author Sara Bruschi
 */

public interface BetService { 
	
	public String doBet(String acronym1, String acronym2, String acronym3) throws CurrencyNotFoundException;
	public JSONObject betResult();
	
}
