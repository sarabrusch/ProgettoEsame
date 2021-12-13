package com.currencylayer.project.statistics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.currencylayer.project.model.Currency;
import com.currencylayer.project.service.CurrencyLayerServiceImpl;

@Service
public class Statistics {

	private CurrencyLayerServiceImpl currencyService = new CurrencyLayerServiceImpl();
	private final String source = "USD";
	private String name;
	private Double value;
	private Currency currency;
	private Double average;
	private Double variance;
	private HashMap<String,Double> historicals = new HashMap<String,Double>();
	private String date ="2021-12-";
 	
	public Double getAverage(String acronym) {
		Double live = currencyService.getCouple(acronym);
		//Double valueYesterday = currencyService.getHistoricalCouple(acronym,date);
		//JSONObject data;
		//data =  (JSONObject) live.get("quotes");
		//JSONObject yesterday = currencyService.getHistoricalQuotation(acronym, "2021-12-12");
		//data =  (JSONObject) data.get("quotes");
		//Double valueYesterday = (Double) data.get(source+acronym);
		//name = currencyService.createHashMapList(acronym).get(acronym);
		//Double valueToday = currencyService.createHashMapLive(acronym).get(source+acronym);
		//value = currencyService.createHashMapLive(acronym).get(source+acronym);
		//average = (live+valueYesterday);
		return average;
		//name = currencyService.names;
		//name = currencyService.createHashMapList(acronym).get(acronym);
		//value = currencyService.createHashMapLive(acronym).get(source+acronym);
		//currency = new Currency(name,acronym,value);
		//JSONObject obj = new JSONObject();
		//return value;
	}
	
	public String getName(String acronym) {
		//name = currencyService.names;
		name = currencyService.createHashMapList(acronym).get(acronym);
		//value = currencyService.createHashMapLive(acronym).get(source+acronym);
		//currency = new Currency(name,acronym,value);
		//JSONObject obj = new JSONObject();
		return name;
	}
	
	public JSONObject readFile() {
		JSONObject fileRead = new JSONObject();
		String data = "";
		String inline = "";
		try {
			BufferedReader read = new BufferedReader(new FileReader("2021-12-12"));
			try {
				while ((inline = read.readLine()) != null) {
					data += inline;
				}
			}
			finally {
				read.close();
			}
			fileRead = (JSONObject) JSONValue.parseWithException(data);
		} 
		catch (FileNotFoundException e) {
			System.out.println("Errore");
			e.printStackTrace();
		}
		catch (IOException e) {
			System.out.println("Errore");
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (JSONObject) fileRead.get("quotes");
	}
	
	public JSONObject readFile2() {
		JSONObject fileRead = new JSONObject();
		String data = "";
		String inline = "";
		try {
			BufferedReader read = new BufferedReader(new FileReader("2021-12-12"));
			try {
				while ((inline = read.readLine()) != null) {
					data += inline;
				}
			}
			finally {
				read.close();
			}
			fileRead = (JSONObject) JSONValue.parseWithException(data);
		} 
		catch (FileNotFoundException e) {
			System.out.println("Errore");
			e.printStackTrace();
		}
		catch (IOException e) {
			System.out.println("Errore");
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (JSONObject) fileRead.get("quotes");
	}
	
	public JSONObject exchangeRates() {
		JSONObject obj = new JSONObject();
		
		Double e = (Double) readFile().get("USDEUR");
		Double b = (Double) readFile().get("USDBTC");
		Double bi = (Double) readFile().get("USDBIF");
		Double e2 = (Double) readFile2().get("USDEUR");
		Double b2 = (Double) readFile2().get("USDBTC");
		Double bi2 = (Double) readFile2().get("USDBIF");
		
		Double ave = (e+e2)/2;
		Double avb = (b+b2)/2;
		Double avbi = (bi+bi2)/2;
		
		obj.put("average USDEUR", ave);
		obj.put("average USDBTC", avb);
		obj.put("average USDBIF", avbi);
		return obj;
	}
}

