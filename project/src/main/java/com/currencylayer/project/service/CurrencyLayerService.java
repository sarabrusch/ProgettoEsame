package com.currencylayer.project.service;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.currencylayer.project.exceptions.CurrencyNotFoundException;
import com.currencylayer.project.exceptions.InvalidFormatDateException;

/** Interfaccia pubblica che chiede di implementare i metodi necessari
 * a leggere l'API assegnata alle rotte necessarie.
 * @author Marco Di Vita 
 * @author Sara Bruschi
 * */

public interface CurrencyLayerService {
	
	public JSONObject getData(String word) throws IOException;
	public JSONObject getHistoricalQuotation(String data) throws InvalidFormatDateException;
	public String getCurrency(String acronym) throws CurrencyNotFoundException;
	public Double getCouple(String acronym) throws CurrencyNotFoundException;
}
