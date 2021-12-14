package com.currencylayer.project.statistics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.currencylayer.project.model.Currency;
import com.currencylayer.project.service.CurrencyLayerServiceImpl;
import com.currencylayer.project.utilis.FileAnalysis;

@Service
public class Statistics {

	private CurrencyLayerServiceImpl currencyService = new CurrencyLayerServiceImpl();
	private FileAnalysis file = new FileAnalysis();
	private final String source = "USD";
	private String name;
	private Double value;
	private Currency currency;
	private Double average;
	private Double variance;
	private Double sumVariance = new Double(0);
	Vector<Double> rates = new Vector<Double>();
	
	public Double getAverage(String acronym) {
		
		JSONObject obj = new JSONObject();
		Double somma = new Double(0);
		Double value = new Double(0);
		int contatore = 0;
		String couple = source+acronym;
		for(int i=1; i<=12;i++) {
			value = (Double) file.readFile(getMonth(i)).get(couple);
			rates.add(value);
			somma += value;
			contatore++;
		}
		average = somma/contatore;
		return average;
	}
	
	public Double getVariance() {
		for(int j=0;j<rates.size();j++) {
			sumVariance += Math.pow(rates.get(j)-average, 2);
			}
		variance = sumVariance/rates.size();
		return variance;
	}

	public JSONObject getStatistics(String acronym) {
		JSONObject obj = new JSONObject();
		obj.put("Average",getAverage(acronym));
		obj.put("Variance",getVariance());
		return obj;
	}
	
	private String getMonth(int index) {
		String date ="";
		if(index<10) {
			date = "2021-0"+index+"-01";
		}
		else {
			date = "2021-"+index+"-01";
		}
		return date;
	}
}

