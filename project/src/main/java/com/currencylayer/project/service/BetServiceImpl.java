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
	private String bet1, bet2, bet3;
	private Double value1Today, value2Today, value3Today;
	private String dateToday = "2021-12-11";
	private String dateTomorrow = "2021-12-11";
	private JSONObject obj;
	boolean control2 = false;
	boolean control3 = false;

	/**
	 * Metodo per piazzare la scommessa, restituisce la scommessa
	 * piazzata insieme ai valori di riferimento della stessa
	 * @param acronym1,acronym2
	 * @return basedBet
	 */
	public String doBet (String acronym1, String acronym2,String acronym3){
		String basedBet;
		bet1 = source+acronym1;
		bet2 = source+acronym2;
		bet3 = source+acronym3;
		obj = file.readFile(dateToday,"quotes");
		value1Today= (Double) obj.get(bet1);
		bet = new Bet(source,acronym1,value1Today); 
		value2Today = (Double) obj.get(bet2);
		value3Today = (Double) obj.get(bet3);
		basedBet = "Bet based on: "+bet1+" with current ExchangeRate: "+value1Today;
		if(acronym2 != null) {
		    basedBet += "\nBet based on: "+bet2+" with current ExchangeRate: "+value2Today;
		    control2 = true;
		}
		if(acronym3 != null) {
			basedBet += "\nBet based on: "+bet3+" with current ExchangeRate: "+value3Today;
			control3 = true;
		}
		return basedBet;
	}
	
	/** 
	 * metodo che verifica il risultato della scommesa
	 * @return result
	 */
	@SuppressWarnings("unchecked")
	public JSONObject betResult() {
		Double value1Tom = new Double(0); 
		Double value2Tom = new Double(0);
		Double value3Tom = new Double(0);
		JSONObject betJSon1 = new JSONObject();
		JSONObject betJSon2 = new JSONObject();
		JSONObject json =new JSONObject();
		JSONObject jjson = new JSONObject();
		JSONObject jjjson = new JSONObject();
		String result;
		String result1 = "You lost";
		String result2 = "You lost";
		String result3 = "You lost";
		obj = file.readFile(dateTomorrow,"quotes");
		value1Tom= (Double) obj.get(bet1);
		if(value1Tom>value1Today) {
			result1 = "You won";
		}
		if(control2) {
			value2Tom = (Double) obj.get(bet2);
			if(value2Tom>value2Today) {
				result2 = "You won";
			}
		}
		if(control3) {
			value3Tom = (Double) obj.get(bet3);	
			if(value3Tom>value3Today) {
				result3 = "You won";
			}
		}
		betJSon1.put("first bet",json);
		json.put("bet on", bet1);
		json.put("yesterday rate", value1Today);
		json.put("today rate", value1Tom);
		json.put("result", result1);
		if(control2) {
			betJSon1.put("second bet", jjson);
			jjson.put("based on", bet2);
			jjson.put("yesterday rate", value2Today);
			jjson.put("today rate", value2Tom);
			jjson.put("result", result2);
		}
		if(control3) {
			betJSon1.put("third bet", jjjson);
			jjjson.put("based on", bet3);
			jjjson.put("yesterday rate", value3Today);
			jjjson.put("today rate", value3Tom);
			jjjson.put("result", result3);
		}
		return betJSon1;
	}
}
