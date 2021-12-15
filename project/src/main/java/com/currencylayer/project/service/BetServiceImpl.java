package com.currencylayer.project.service;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.currencylayer.project.model.Bet;
import com.currencylayer.project.model.CurrencyCouple;
import com.currencylayer.project.utilis.FileAnalysis;

/**
 * Classe che contiene metodi utili alla scommessa 
 * @author Sara bruschi
 * @author Marco Di Vita
 */

@Service
public class BetServiceImpl {
	
	Bet bet;
	FileAnalysis file = new FileAnalysis();
	CurrencyLayerServiceImpl currencyService = new CurrencyLayerServiceImpl();
	private final String source = "USD";
	private String bet1;
	private String bet2;
	private Double value1Today;
	private Double value2Today;
	private String dateToday = "2021-12-11";
	private String dateTomorrow = "2021-12-11";
	JSONObject obj;;
	
	/**
	 * metodo per piazzare la scommessa, restituisce la scommessa
	 * piazzata insieme ai valori di riferimento della stessa
	 * @param acronym1,acronym2
	 * @return basedBet
	 */
	
	public String doBet (String acronym1, String acronym2){
		String basedBet;
		bet1 = source+acronym1;
		bet2 = source+acronym2;
		obj = file.readFile(dateToday,"quotes");
		value1Today= (Double) obj.get(bet1);
		bet = new Bet(source,acronym1,value1Today); //TODO chiedere collegamento al model
		value2Today = (Double) obj.get(bet2);
		basedBet = "Bet based on: "+bet1+" with current ExchangeRate: "+value1Today;
		if(acronym2 != null) {
		basedBet += "\nBet besed on: "+bet2+" with current ExchangeRate: "+value2Today;
		}
		return basedBet;
	}
	
	/** 
	 * metodo che verifica il risultato della scommesa
	 * @return result
	 */
	
	public JSONObject betResult() {
		 Double value1Tom; 
		 Double value2Tom;
		 JSONObject betJSon1 = new JSONObject();
		 JSONObject betJSon2 = new JSONObject();
		 JSONObject json =new JSONObject();
		 JSONObject jjson = new JSONObject();
		  
		 String result;
		 String result1 = "You lost";
		 String result2 = "You lost";
		 obj = file.readFile(dateTomorrow,"quotes");
		 value1Tom= (Double) obj.get(bet1);
		 value2Tom = (Double) obj.get(bet2);
		 if(value1Tom>value1Today) {
			result1 = "You won";
		 }
		 if(value2Tom>value2Today) {
			result2 = "You won";
		 }
		betJSon1.put("first bet",json);
		betJSon1.put("second bet", jjson);
		json.put("bet on", bet1);
		json.put("yesterday rate", value1Today);
		json.put("today rate", value1Tom);
		json.put("result", result1);
		jjson.put("based on", bet2);
		jjson.put("yesterday rate", value2Today);
		jjson.put("today rate", value2Tom);
		jjson.put("result", result2);
		return betJSon1;
	}
}
