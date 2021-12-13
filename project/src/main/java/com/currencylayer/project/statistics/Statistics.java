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
	private String date ="2021-12-";
 	
	public Double getAverage(String acronym) {
		Double live = currencyService.getCouple(acronym);
		//Double valueYesterday = currencyService.getHistoricalCouple(acronym,date);
		//JSONObject data;
		//data =  (JSONObject) live.get("quotes");
		//JSONObject yesterday = currencyService.getHistoricalQuotation(acronym, "2021-12-12");
		//data =  (JSONObject) data.get("quotes");
		//Double valueYesterday = (Double) data.get(source+acronym);
		//name = currencyService.createHashMapList(acronym).get(acronym);
		//Double valueToday = currencyService.createHashMapLive(acronym).get(source+acronym);
		//value = currencyService.createHashMapLive(acronym).get(source+acronym);
		//average = (live+valueYesterday);
		return average;
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
