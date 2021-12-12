package com.currencylayer.project.statistics;

import java.util.ArrayList;

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
	
	public Double getAverage(String acronym) {
		//name = currencyService.names;
		//name = currencyService.createHashMapList(acronym).get(acronym);
		value = currencyService.createHashMapLive(acronym).get(source+acronym);
		//currency = new Currency(name,acronym,value);
		//JSONObject obj = new JSONObject();
		return value;
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
