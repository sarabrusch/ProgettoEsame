package com.currencylayer.project.service;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.currencylayer.project.model.Bet;
import com.currencylayer.project.model.CurrencyCouple;
import com.currencylayer.project.utilis.FileAnalysis;

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
	
	public String betResult() {
		 Double value1Tom; 
		 Double value2Tom;
		 String result;
		 boolean result1 = false;
		 boolean result2 = false;
		 obj = file.readFile(dateTomorrow,"quotes");
		 value1Tom= (Double) obj.get(bet1);
		 value2Tom = (Double) obj.get(bet2);
		 if(value1Tom>value1Today) {
			 result1 = true;
		 }
		 if(value2Tom>value2Today) {
			 result2 = true;
		 }
		 if(result1 && result2) {
			 result = "You won";
		 }
		 else if(result1 && !result2) {
			 result = "You won the first bet";
		 }
		 else if(!result1 && result2) {
			 result = "You won the second bet";
		 }
		 else {
			 result = "You lost";
		 }
		return result;
	}
}
