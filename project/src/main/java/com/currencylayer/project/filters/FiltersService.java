package com.currencylayer.project.filters;

import org.json.simple.JSONObject;

/**
 * Interfaccia relativa ai filti
 * @author Marco Di Vita
 * @author Sara Bruschi
 */
public interface FiltersService {
	
	public JSONObject currencyFilter(String acronym);
	public JSONObject historicalFilter(String date, String acronym1, String acronym2);

}
