package com.currencylayer.project.service;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.currencylayer.project.exceptions.CurrencyNotFoundException;
import com.currencylayer.project.model.Bet;
import com.currencylayer.project.model.CurrencyCouple;
import com.currencylayer.project.model.CurrencyCoupleExchange;
import com.currencylayer.project.model.Source;
import com.currencylayer.project.utilis.FileAnalysis;

/**
 * Classe  implementazione di BetService che contiene l'implementazione dei
 * metodi per le scommesse
 * @author Sara bruschi
 * @author Marco Di Vita
 */

@Service
public class BetServiceImpl implements BetService {
	
	private Bet bet1,bet2,bet3;
	private CurrencyCouple currencyCouple1,currencyCouple2,currencyCouple3;
	private CurrencyCoupleExchange currencyCoupleExchange1,currencyCoupleExchange2,currencyCoupleExchange3;
	private Source source = new Source();
	private String src = source.getAcronym();
	private FileAnalysis file = new FileAnalysis();
	private Double value1Today, value2Today, value3Today;
    private String acronym1,acronym2,acronym3;
	private String dateToday = "2021-12-11";
	private String dateTomorrow = "2021-12-12";
	private JSONObject obj;
	private boolean control2,control3 = false;


	/**
	 * Metodo per piazzare la scommessa, restituisce la scommessa
	 * piazzata insieme ai valori di riferimento della stessa
	 * @param acronym1,acronym2,acronym3 acronimi delle valute sulle quali si vuole
	 * scommettere (ricordando che source = "USD" sempre).
	 * @return basedBet è una stringa che dichiara le caratteristiche della/e
	 * scommessa/e appena piazzata/e
	 */
	public String doBet (String acronym1, String acronym2,String acronym3) throws CurrencyNotFoundException {
		String basedBet;
		value1Today = new Double(0);
		value2Today = new Double(0);
		value3Today = new Double(0);
		this.acronym1 = acronym1;
		this.acronym2 = acronym2;
		this.acronym3 = acronym3;
		obj = file.readFile(dateToday,"quotes");
		currencyCouple1 = new CurrencyCouple(src,acronym1);
		if(obj.get(currencyCouple1.getCouple()) == null ) {
			throw new CurrencyNotFoundException("This currency: "+acronym1+" doesn't exist");
		}
		value1Today = (Double) obj.get(currencyCouple1.getCouple());
		currencyCoupleExchange1 = new CurrencyCoupleExchange(src,acronym1,value1Today);
		basedBet = "Bet based on: "+currencyCouple1.getCouple()+" with current ExchangeRate: "+value1Today;
		if(acronym2 != null) {
			currencyCouple2 = new CurrencyCouple(src,acronym2);
		if(obj.get(currencyCouple2.getCouple()) == null ) {
			throw new CurrencyNotFoundException("This currency: "+acronym2+" doesn't exist");
		}
		else {
			value2Today = (Double) obj.get(currencyCouple2.getCouple());
			currencyCoupleExchange2 = new CurrencyCoupleExchange(src,acronym2,value2Today);
			basedBet += "\nBet based on: "+currencyCouple2.getCouple()+" with current ExchangeRate: "+value2Today;
			control2 = true;
		}
		}
		if(acronym3 != null) {
			currencyCouple3 = new CurrencyCouple(src,acronym3);
		if(obj.get(currencyCouple3.getCouple()) == null ) {
			throw new CurrencyNotFoundException("This currency: "+acronym3+" doesn't exist");
		}
		else {
			value3Today = (Double) obj.get(currencyCouple3.getCouple());
			currencyCoupleExchange3 = new CurrencyCoupleExchange(src,acronym3,value3Today);
			basedBet += "\nBet based on: "+currencyCouple3.getCouple()+" with current ExchangeRate: "+value3Today;
			control3 = true;
		}
	    }
		return basedBet+"\nSee results at http://localhost:8080/betResult";
	}
	
	/** 
	 * Metodo che verifica e stampa il risultato della scommesa precedentemente
	 * piazzata.
	 * @return result JSONObject contenente tutte le informazioni riguardanti le
	 * scommesse, tra cui anche il risultato.
	 */
	public JSONObject betResult() {
		Double value1Tom = new Double(0); 
		Double value2Tom = new Double(0);
		Double value3Tom = new Double(0);
		JSONObject betJSon = new JSONObject();
		JSONObject json =new JSONObject();
		JSONObject jjson = new JSONObject();
		JSONObject jjjson = new JSONObject();
		String result1 = "You lost";
		String result2 = result1;
		String result3 = result1;
		obj = file.readFile(dateTomorrow,"quotes");
		value1Tom= (Double) obj.get(currencyCouple1.getCouple());
		currencyCoupleExchange1 = new CurrencyCoupleExchange(src,acronym1,value1Tom);
		bet1 = new Bet(src,currencyCouple1.getAcronym(),false);
		if(value1Tom>value1Today) {
			result1 = "You won";
			bet1.setIsWinning(true);
		}
		betJSon.put("first bet",json);
		json.put("bet on", currencyCouple1.getCouple());
		json.put("yesterday rate", value1Today);
		json.put("today rate", value1Tom);
		json.put("result", result1);
		if(control2) {
			value2Tom = (Double) obj.get(currencyCouple2.getCouple());
			currencyCoupleExchange2 = new CurrencyCoupleExchange(src,acronym2,value2Tom);
			bet2 = new Bet(src,currencyCouple2.getAcronym(),false);
			if(value2Tom>value2Today) {
				result2 = "You won";
				bet2.setIsWinning(true);
			}
			betJSon.put("second bet", jjson);
			jjson.put("based on", currencyCouple2.getCouple());
			jjson.put("yesterday rate", value2Today);
			jjson.put("today rate", value2Tom);
			jjson.put("result", result2);
		}
		if(control3) {
			value3Tom = (Double) obj.get(currencyCouple3.getCouple());	
			currencyCoupleExchange3 = new CurrencyCoupleExchange(src,acronym3,value3Tom);
			bet3 = new Bet(src,currencyCouple3.getAcronym(),false);
			if(value3Tom>value3Today) {
				result3 = "You won";
				bet3.setIsWinning(true);
			}
			betJSon.put("third bet", jjjson);
			jjjson.put("based on",currencyCouple3.getCouple());
			jjjson.put("yesterday rate", value3Today);
			jjjson.put("today rate", value3Tom);
			jjjson.put("result", result3);
		}
		return betJSon;
	}
}
