package com.currencylayer.project.service;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/** Interfaccia pubblica che chiede di implementare i metodi necessari
 * a leggere l'API assegnata alle rotte necessarie.
 * ************************************************  */
/** @author Marco Di Vita */

public interface CurrencyLayerService {
	
	public JSONObject getLive() throws IOException;
	public JSONObject getList() throws IOException;
	public JSONObject getHistoricalQuotation(String data);
	public JSONObject toJSON(Object obj);
	public JSONObject getCurrency(String acronym);
	public Double getCouple(String acronym);
	public HashMap<String,String> createHashMapList(String acronym);
	public HashMap<String, Double> createHashMapLive (String acronym);
	//public HashMap<String, Double> createHashMapHistorical (String acronym,String date);
	//public Double getHistoricalCouple(String acronym,String date);
	
}
