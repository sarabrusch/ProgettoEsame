package com.currencylayer.project.filters;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.currencylayer.project.exceptions.CurrencyNotFoundException;
import com.currencylayer.project.exceptions.InvalidFormatDateException;
import com.currencylayer.project.model.Currency;
import com.currencylayer.project.model.CurrencyCouple;
import com.currencylayer.project.model.OurDate;
import com.currencylayer.project.model.Source;
import com.currencylayer.project.service.CurrencyLayerServiceImpl;
import com.currencylayer.project.utilis.FileAnalysis;

/** Classe implementazione di FiltersService contentente i filtri 
 * per periodo e per valuta.
 * @author Sara Bruschi
 * @author Marco Di Vita
 */

@Service
public class Filters implements FiltersService {
	
	private Source source = new Source();
	private String src = source.getAcronym();
	private OurDate d;
	private Currency currency1, currency2;
	private CurrencyLayerServiceImpl currencyService = new CurrencyLayerServiceImpl();
	private FileAnalysis file = new FileAnalysis();
	private Double quote1, quote2;
	
	/** Metodo che filtra la lista contentente i nomi delle currency restituendo 
	 * tutte le informazioni relative alla currency richiesta in ingresso.
	 * @param acronym Stringa contenente l'acronimo della currency che si vuole
	 * filtrare.
	 * @return filter JSONObject contenente tutte le informazioni relative all'acronym
	 * in ingresso.
	 * @throws CurrencyNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject currencyFilter(String acronym1, String acronym2) throws CurrencyNotFoundException {
		JSONObject obj = new JSONObject();
		JSONObject filter = new JSONObject();
		boolean control = false;
		String nameQuote;
		CurrencyCouple currencyCouple2 = new CurrencyCouple();
		quote1 = new Double(0);
		quote2 = new Double(0);
		JSONObject list = file.readFile("List.txt", "currencies");
		JSONObject live = file.readFile("Live.txt", "quotes");
		nameQuote = (String) list.get(acronym1);
		if(nameQuote == null ) {
			throw new CurrencyNotFoundException("This currency: "+acronym1+" doesn't exist");
		}
		CurrencyCouple currencyCouple1 = new CurrencyCouple(src,acronym1);
		currency1 = new Currency(nameQuote,acronym1);
		quote1 = (Double) live.get(src+acronym1);
		if(acronym2 != null) {
			nameQuote = (String) list.get(acronym2);
			currencyCouple2 = new CurrencyCouple(src,acronym2);
			quote2 = (Double) live.get(src+acronym2);
		if(nameQuote == null ) {
			throw new CurrencyNotFoundException("This currency: "+acronym2+" doesn't exist");
		}
		else {
			control = true;
		}
		}
		filter.put("filter",obj);
		obj.put(src, source.getName());
		obj.put(acronym1, currency1.getName());
		obj.put(currencyCouple1.getCouple(), quote1);
		if(control) {
			currency2 = new Currency(nameQuote,acronym2);
			obj.put(acronym2, currency2.getName());
			obj.put(currencyCouple2.getCouple(), quote2);
		}
		return filter;
	}
	
	/** Metodo che filtra la lista contentente le historicalQuotes restituendo 
	 * l'exchange rate relativo alla coppia source+acronym che si aveva alla data
	 * richiesta in ingresso.
	 * @param date data di cui si richiede di conoscere l'exchange rate
	 * @param acronym1 prima currency che si vuole filtrare per periodo
	 * @param acronym2 seconda currency che si vuole filtrare per periodo
	 * @return filter informazioni relative alle richieste effettuate.
	 * @throws CurrencyNotFoundException 
	 * @throws InvalidFormatDateException 
	 */
	@Override
	public JSONObject historicalFilter(String date, String acronym1, String acronym2) throws CurrencyNotFoundException, InvalidFormatDateException {
		quote1 = new Double(0);
		quote2 = new Double(0);
		CurrencyCouple currencyCouple1 = new CurrencyCouple();
		CurrencyCouple currencyCouple2 = new CurrencyCouple();
		currencyCouple1 = new CurrencyCouple(src,acronym1);
		JSONObject obj = new JSONObject();
		JSONObject filter = new JSONObject();
		d = new OurDate(date);
		date = d.toString();
		if(!d.isRight()) {
			throw new InvalidFormatDateException("Invalid format date");
		}
		JSONObject list = currencyService.getHistoricalQuotation(date);
		list = (JSONObject) list.get("quotes");
	    quote1 = (Double) list.get(currencyCouple1.getCouple());
	    if(quote1 == null ) {
	    	throw new CurrencyNotFoundException("This currency: "+acronym1+" doesn't exist");
	    }
	    if(acronym2 != null) { 
	    	currencyCouple2 = new CurrencyCouple(src,acronym2);
	    	quote2 = (Double) list.get(currencyCouple2.getCouple());
	    	if(quote2 == null) {
	    		throw new CurrencyNotFoundException("This currency: "+acronym2+" doesn't exist");
	    	}
	    	else {
	    		obj.put(currencyCouple2.getCouple(), quote2);
	    	}
	    }
	    filter.put("historical",obj);
	    obj.put("date", date);
	    obj.put(currencyCouple1.getCouple(), quote1);
	    return filter;
	}

}
