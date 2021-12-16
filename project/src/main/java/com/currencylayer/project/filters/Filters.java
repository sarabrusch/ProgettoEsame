package com.currencylayer.project.filters;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.currencylayer.project.service.CurrencyLayerServiceImpl;
import com.currencylayer.project.utilis.FileAnalysis;

/** 
 * Classe implementazione di FiltersService contentente i filtri per periodo e per valuta.
 * @author Sara Bruschi
 * @author Marco Di Vita
 */

@Service
public class Filters implements FiltersService {
	
	private final String source = "USD";
	private CurrencyLayerServiceImpl currencyService = new CurrencyLayerServiceImpl();
	private FileAnalysis file = new FileAnalysis();
	private String nameSource = "";
	private String nameQuote = "";
	private Double value = null;
	
	/**
	 * Metodo che filtra la lista contentente i nomi delle currency restituendo tutte
	 * le informazioni relative alla currency richiesta in ingresso.
	 * @param acronym Stringa contenente l'acronimo della currency che si vuole
	 * filtrare.
	 * @return filter JSONObject contenente tutte le informazioni relative all'acronym
	 * in ingresso.
	 */
	@SuppressWarnings("unchecked")
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
	
	/**
	 * Metodo che filtra la lista contentente le historicalQuotes restituendo 
	 * l'exchange rate relativo alla coppia source+acronym che si aveva alla data
	 * richiesta in ingresso.
	 * @param date data di cui si richiede di conoscere l'exchange rate
	 * @param acronym1 prima currency che si vuole filtrare per periodo
	 * @param acronym2 seconda currency che si vuole filtrare per periodo
	 * @return filter informazioni relative alle richieste effettuate.
	 */
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
		if(acronym2 != null) {
			obj.put(source+acronym2, quote2);
		}
		return filter;
	}

}
