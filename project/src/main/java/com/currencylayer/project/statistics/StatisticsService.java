package com.currencylayer.project.statistics;

import org.json.simple.JSONObject;

import com.currencylayer.project.exceptions.CurrencyNotFoundException;

/**
 * Interfaccia per la gestione delle statistiche
 * @author Marco Di Vita
 * @author Sara Bruschi
 */

public interface StatisticsService {
	
	public Double getAverage();
	public Double getVariance();
	public Double getMax();
	public Double getMin();
	public JSONObject getStatistics(String acronym) throws CurrencyNotFoundException;
	
}


