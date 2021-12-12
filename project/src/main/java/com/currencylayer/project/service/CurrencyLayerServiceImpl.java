package com.currencylayer.project.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.json.simple.*;

/**  @author Marco Di Vita
 *   @author Sara Bruschi */

@Service
public class CurrencyLayerServiceImpl implements CurrencyLayerService {

	private String url = "http://api.currencylayer.com/";
	private String key = "74a39b5b1ae2f4bac3f38eaa28bec030";
	private String source = "USD";
	private String acronym = "";
	private JSONObject liveExchangeRate = new JSONObject();
	private JSONObject listCurrencies = new JSONObject();
	private HashMap<String,String> currencies = new HashMap<String,String>();
	private HashMap<String,Double> rates = new HashMap<String,Double>();

    public HashMap<String, String> getCurrencies() {
		return currencies;
	}
	
	public HashMap<String, Double> getRates() {
		return rates;
	}
	
	public String getAcroym() {
		return acronym;
	}
	
    @Override
	public JSONObject getLive() {
		try {
			URLConnection openConnection = new URL(url+"live"+"?access_key="+key).openConnection();
			InputStream input = openConnection.getInputStream();
			String data = "";
			String inline = "";
			try {
				InputStreamReader inR = new InputStreamReader(input);
				BufferedReader buf = new BufferedReader(inR);
				//Write all the JSON data into a string using a scanner
				while ((inline = buf.readLine()) != null) {
					data += inline;
				}
			}
			finally {
				//Close the scanner
				input.close();
			}
			liveExchangeRate = (JSONObject) JSONValue.parseWithException(data);
		}
		catch(IOException e) {
			System.out.println("Errore...");
			System.out.println(e);
		}
		catch(Exception e) {
			System.out.println("Errore...");
			System.out.println(e);
		}
		return (JSONObject) liveExchangeRate;
	}
	
	@Override
	public JSONObject getList() {
		try {
			URLConnection openConnection = new URL(url+"list"+"?access_key="+key).openConnection();
			InputStream input = openConnection.getInputStream();
			String data = "";
			String inline = "";
			try {
				InputStreamReader inR = new InputStreamReader(input);
				BufferedReader buf = new BufferedReader(inR);
				//Write all the JSON data into a string using a scanner
				while ((inline = buf.readLine()) != null) {
					data += inline;
				}
			}
			finally {
				//Close the scanner
				input.close();
			}
			listCurrencies = (JSONObject) JSONValue.parseWithException(data);
		}
		catch(IOException e) {
			System.out.println("Errore...");
			System.out.println(e);
		}
		catch(Exception e) {
			System.out.println("Errore...");
			System.out.println(e);
		}
		return (JSONObject) listCurrencies;
	}


	@Override
	public JSONObject getHistoricalQuotation(String word,String date) {

		JSONObject liveExchangeRate = null;

		try {
			//TODO
			FileWriter file ;
			file = new FileWriter("Data.txt");
			BufferedWriter writer;
			writer = new BufferedWriter (file);
			URLConnection openConnection = new URL(url+word+"?access_key="+key+"&date="+date).openConnection();
			InputStream input = openConnection.getInputStream();
			String data = "";
			String inline = "";
			try {
				InputStreamReader inR = new InputStreamReader(input);
				BufferedReader buf = new BufferedReader(inR);
				//Write all the JSON data into a string using a scanner
				while ((inline = buf.readLine()) != null) {
					data += inline;
					writer.write(data);
				}
			}
			finally {
				//Close the scanner
				input.close();
				writer.flush();
				writer.close();
			}
			liveExchangeRate = (JSONObject) JSONValue.parseWithException(data);
		}
		catch(IOException e) {
			System.out.println("Errore...");
			System.out.println(e);
		}
		catch(Exception e) {
			System.out.println("Errore...");
			System.out.println(e);
		}
		return liveExchangeRate;
	}

	@Override
	public JSONObject toJSON(Object object) {
		JSONObject obj = null;
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getCurrency(String acronym) {
		this.acronym = acronym;
		JSONObject obj = new JSONObject();
		JSONObject curr = new JSONObject();
		String currency = "";
		try {
		JSONObject json = getList();
	    JSONObject currenciess = (JSONObject) json.get("currencies");
	    currency = (String) currenciess.get(acronym);
		}
		catch(Exception e) {
			System.out.println("Errore...");
		}
		currencies.put(acronym, currency);
		curr.put("currency", obj);
		obj.put(acronym, currency);
		return curr;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getCouple(String acronym) {
		this.acronym = acronym;
		String currency = "";
		JSONObject obj = new JSONObject();
		JSONObject curr = new JSONObject();
		JSONObject objj = new JSONObject();
		Double value = new Double(0);
		String couple = source+acronym;
		try {
			JSONObject json = getLive();
			JSONObject currencies = (JSONObject) json.get("quotes");
			currency = (String) currencies.get(couple);
		}
		catch(Exception e) {
			System.out.println("Errore...");
		}
		rates.put(couple, value);
		curr.put("currencies", obj);
		obj.put("quote", objj);
		objj.put("base currency", this.source);
		objj.put("quote currency", acronym);
		obj.put("rate", currency);
		return curr;
	}
	
}


