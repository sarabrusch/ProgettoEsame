package com.currencylayer.project.statistics;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.currencylayer.project.model.Currency;
import com.currencylayer.project.service.CurrencyLayerServiceImpl;

public class Statistics {

	private CurrencyLayerServiceImpl currencyService = new CurrencyLayerServiceImpl();
	private final String source = "USD";
	private ArrayList name;
	private ArrayList value;
	private Currency currency;
	
	public JSONObject getAverage() {
		name = currencyService.names;
		value = currencyService.values;
		//currency = new Currency(name,acronym,value);
		JSONObject obj = new JSONObject();
		return (JSONObject) value.get(5);
	}
	
}
