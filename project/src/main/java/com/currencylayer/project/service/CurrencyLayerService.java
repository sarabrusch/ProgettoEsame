package com.currencylayer.project.service;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/** Interfaccia pubblica che chiede di implementare i metodi necessari
 * a leggere l'API assegnata alle rotte necessarie.
 * ************************************************  */
/** @author Marco Di Vita */

public interface CurrencyLayerService {
	
	public JSONObject getLiveOrList(String word);
	//public abstract void getCurrencyList();
	public JSONObject getHistoricalQuotation(String word, String data);
	public JSONObject toJSON(Object obj);
	
}
