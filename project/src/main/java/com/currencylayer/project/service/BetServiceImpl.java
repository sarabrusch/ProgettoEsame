package com.currencylayer.project.service;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.currencylayer.project.model.Bet;
import com.currencylayer.project.model.CurrencyCouple;
import com.currencylayer.project.utilis.FileAnalysis;

@Service
public class BetServiceImpl {
	
	Bet bet = new Bet();
	FileAnalysis file = new FileAnalysis();
	CurrencyLayerServiceImpl currencyService = new CurrencyLayerServiceImpl();
	private final String source = "USD";
	private String bet1;
	private String bet2;
	private Double value1Today;
	private Double value2Today;
	private String dateToday = "2021-12-11";
	private String dateTomorrow = "2021-12-11";
	JSONObject qualsiasi;
	
	
	
	public String doBet (String acronym1, String acronym2){
		String basedBet;
		bet1 = source+acronym1;
		bet2 = source+acronym2;
		qualsiasi = file.readFile(dateToday);
		value1Today= (Double) qualsiasi.get(bet1);
		value2Today = (Double) qualsiasi.get(bet2);
		basedBet = "Scommessa piazzata su: "+bet1+" con attuale ExchangeRate: "+value1Today;
		if(acronym2 != null) {
		basedBet += "\nScommessa piazzata su: "+bet2+" con attuale ExchangeRate: "+value2Today;
		}
		return basedBet;
	}
	
	public String betResult() {
		 Double value1Tom; 
		 Double value2Tom;
		 String risultato;
		 boolean risultato1 = false;
		 boolean risultato2 = false;
		 qualsiasi = file.readFile("2021-01-01");
		 value1Tom= (Double) qualsiasi.get(bet1);
		 value2Tom = (Double) qualsiasi.get(bet2);
		 if(value1Tom>value1Today) {
			 risultato1 = true;
		 }
		 if(value2Tom>value2Today) {
			 risultato2 = true;
		 }
		 if(risultato1 && risultato2) {
			 risultato = "YEAH";
		 }
		 else if(risultato1 && !risultato2) {
			 risultato = "yeah1";
		 }
		 else if(!risultato1 && risultato2) {
			 risultato = "yeah2";
		 }
		 else 
			 risultato = "sconfitta";
		 
		return risultato+"da fare";
	}
	
	//controllo vincita prendendo bet1

}
