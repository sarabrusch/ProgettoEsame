package com.currencylayer.project.statistics;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.currencylayer.project.model.Currency;
import com.currencylayer.project.service.CurrencyLayerServiceImpl;

@Service
public class Statistics {

	private CurrencyLayerServiceImpl currencyService = new CurrencyLayerServiceImpl();
	private final String source = "USD";
	private String name;
	private Double value;
	private String boh;
	private Currency currency;
	private Double average;
	private Double variance;
	private HashMap<String,Double> historicals = new HashMap<String,Double>();
 	
	public Double getAverage(String acronym) {
		JSONObject live = currencyService.getHistoricalQuotation(acronym,"2021-12-12");
		JSONObject data;
		data =  (JSONObject) live.get("quotes");
		historicals.put(source+acronym, (Double) data.get(source+acronym));
		
		//JSONObject yesterday = currencyService.getHistoricalQuotation(acronym, "2021-12-12");
		//data =  (JSONObject) data.get("quotes");
		Double valueYesterday = historicals.get(source+acronym);
		Double valueToday = currencyService.createHashMapLive(acronym).get(source+acronym);
		return (valueToday+valueYesterday)/2;
		//name = currencyService.names;
		//name = currencyService.createHashMapList(acronym).get(acronym);
		//value = currencyService.createHashMapLive(acronym).get(source+acronym);
		//currency = new Currency(name,acronym,value);
		//JSONObject obj = new JSONObject();
		//return value;
	}
	
	public String getName(String acronym) {
		//name = currencyService.names;
		name = currencyService.createHashMapList(acronym).get(acronym);
		//value = currencyService.createHashMapLive(acronym).get(source+acronym);
		//currency = new Currency(name,acronym,value);
		//JSONObject obj = new JSONObject();
		return name;
	}
}
