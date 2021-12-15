package com.currencylayer.project.filters;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.currencylayer.project.service.CurrencyLayerServiceImpl;
import com.currencylayer.project.utilis.FileAnalysis;

@Service
public class Filters implements FiltersService {
	
	private final String source = "USD";
	private CurrencyLayerServiceImpl currencyService = new CurrencyLayerServiceImpl();
	private FileAnalysis file = new FileAnalysis();
	private String nameSource = "";
	private String nameQuote = "";
	private Double value = null;
	
	@Override
	public JSONObject currencyFilter(String acronym) {
		JSONObject obj = new JSONObject();
		JSONObject filter = new JSONObject();
		JSONObject list = file.readFile("List.txt", "currencies");
		nameSource = (String) list.get(source);
		nameQuote = (String) list.get(acronym);
		value = currencyService.getCouple(acronym);
		filter.put("filter",obj);
		obj.put(source, nameSource);
		obj.put(acronym, nameQuote);
		obj.put(source+acronym, value);
		return filter;
	}
	
	@Override
	public JSONObject historicalFilter(String date, String acronym1, String acronym2) {
		JSONObject obj = new JSONObject();
		JSONObject filter = new JSONObject();
		JSONObject list = currencyService.getHistoricalQuotation(date);
		list = (JSONObject) list.get("quotes");
	    Double quote1 = (Double) list.get(source+acronym1);
	    Double quote2 = (Double) list.get(source+acronym2);
		filter.put("historical",obj);
		obj.put("date", date);
		obj.put(source+acronym1, quote1);
		obj.put(source+acronym2, quote2);
		return filter;
	}

}
