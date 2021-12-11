package com.currencylayer.project.service;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/** Interfaccia pubblica che chiede di implementare i metodi necessari
 * a leggere l'API assegnata alle rotte necessarie.
 * ************************************************  */
/** @author Marco Di Vita */

public interface CurrencyLayerService {
	
	public JSONObject getLiveOrList(String word) throws IOException;
	//public abstract void getCurrencyList();
	public JSONObject getHistoricalQuotation(String word, String data);
	public JSONObject toJSON(Object obj);
	public String getCurrency(String acronym);
	public Double getCouple(String acronym);
	public String getStatistic(String acronym);
	
}
