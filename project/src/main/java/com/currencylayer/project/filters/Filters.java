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
	private OurDate d;
	private Currency currency;
	private String src = source.getAcronym();
	private CurrencyCouple currencyCouple1, currencyCouple2;
	private CurrencyLayerServiceImpl currencyService = new CurrencyLayerServiceImpl();
	private FileAnalysis file = new FileAnalysis();
	private String nameQuote;
	private Double value;
	
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
	public JSONObject currencyFilter(String acronym) throws CurrencyNotFoundException {
		JSONObject obj = new JSONObject();
		JSONObject filter = new JSONObject();
		JSONObject list = file.readFile("List.txt", "currencies");
		nameQuote = (String) list.get(acronym);
		currency = new Currency(nameQuote,acronym);
		if(nameQuote == null ) {
			throw new CurrencyNotFoundException("This currency: "+acronym+" doesn't exist");
		}
		value = currencyService.getCouple(acronym);
		filter.put("filter",obj);
		obj.put(src, source.getName());
		obj.put(acronym, currency.getName());
		obj.put(src+acronym, value);
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
		boolean control = true;
		Double quote2 = new Double(0);
		currencyCouple1 = new CurrencyCouple()
		JSONObject obj = new JSONObject();
		JSONObject filter = new JSONObject();
		d = new OurDate(date);
		date = d.toString();
		if(!d.isRight()) {
			throw new InvalidFormatDateException("Invalid format date");
		}
		JSONObject list = currencyService.getHistoricalQuotation(date);
		list = (JSONObject) list.get("quotes");
	    Double quote1 = (Double) list.get(src+acronym1);
	    if(quote1 == null ) {
	    	throw new CurrencyNotFoundException("This currency: "+acronym1+" doesn't exist");
	    }
	    if(acronym2 != null) { 
	    	quote2 = (Double) list.get(src+acronym2); 
	    	if(quote2 == null) {
	    		throw new CurrencyNotFoundException("This currency: "+acronym2+" doesn't exist");
	    	}
	    	obj.put(src+acronym2, quote2);
	    }
	    filter.put("historical",obj);
	    obj.put("date", date);
	    obj.put(src+acronym1, quote1);
	    return filter;
	}

}
