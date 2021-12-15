package com.currencylayer.project.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import org.springframework.stereotype.Service;

import com.currencylayer.project.model.Currency;
import com.currencylayer.project.model.CurrencyCouple;
import com.currencylayer.project.utilis.FileAnalysis;

import org.json.simple.*;

/** Classe implementazione di CurrencyLayerService che va ad implementare i metodi
 * responsabili delle chiamate all'API e della lettura dei JSONObject restituiti
 * da quest'ultima per la rielaborazione dei dati.
 * 
 * @author Marco Di Vita
 * @author Sara Bruschi */

@Service
public class CurrencyLayerServiceImpl implements CurrencyLayerService {

	private String url = "http://api.currencylayer.com/";
	private String key = "74a39b5b1ae2f4bac3f38eaa28bec030";
	private String source = "USD";
	private FileAnalysis file = new FileAnalysis();
	private Currency currency;
	private CurrencyCouple currencyCouple;

	/**Metodo per la chiamata all'API che restituisce la lista di valute
	 * con relativo acronimo e nome.  */
	/**Metodo per la chiamata all'API che restituisce la lista di exchange rates
	 * relativa a coppie di valute, di cui la source è sempre "USD".  */
	@Override
	public JSONObject getData(String word) {
		JSONObject data = new JSONObject();
		try {
			//FileWriter file ;
			//file = new FileWriter("word");
			//BufferedWriter writer = new BufferedWriter(file);
			URLConnection openConnection = new URL(url+word+"?access_key="+key).openConnection();
			InputStream input = openConnection.getInputStream();
			String readData = "";
			String inline = "";
			try {
				InputStreamReader inR = new InputStreamReader(input);
				BufferedReader buf = new BufferedReader(inR);
				//Write all the JSON data into a string using a scanner
				while ((inline = buf.readLine()) != null) {
					readData += inline;
					//writer.write(data);
				}
			}
			finally {
				//Close the scanner
				input.close();
				//writer.flush();
				//writer.close();
			}
			data = (JSONObject) JSONValue.parseWithException(readData);
		}
		catch(IOException e) {
			System.out.println("Errore...");
			System.out.println(e);
		}
		catch(Exception e) {
			System.out.println("Errore...");
			System.out.println(e);
		}
		return (JSONObject) data;
	}
	
    /**Metodo per la chiamata all'API che restituisce la lista di exchange rates
	 * relativa a coppie di valute, di cui la source è sempre "USD", di una
	 * precisa data che deve essere specificata.
	 * @param date di cui si vuole conoscere l'exchange rate  */
    @Override
	public JSONObject getHistoricalQuotation(String date) {
		JSONObject historicalExchangeRate = new JSONObject();
		try {
			//TODO
			//FileWriter file ;
			//file = new FileWriter("2021-12-01");
			//BufferedWriter writer = new BufferedWriter(file);
			URLConnection openConnection = new URL(url+"historical"+"?access_key="+key+"&date="+date).openConnection();
			InputStream input = openConnection.getInputStream();
			String readData = "";
			String inline = "";
			try {
				InputStreamReader inR = new InputStreamReader(input);
				BufferedReader buf = new BufferedReader(inR);
				//Write all the JSON data into a string using a scanner
				while ((inline = buf.readLine()) != null) {
					readData += inline;
					//writer.write(data);
				}
			}
			finally {
				//Close the scanner
				input.close();
				//writer.flush();
				//writer.close();
			}
			historicalExchangeRate = (JSONObject) JSONValue.parseWithException(readData);
		}
		catch(IOException e) {
			System.out.println("Errore...");
			System.out.println(e);
		}
		catch(Exception e) {
			System.out.println("Errore...");
			System.out.println(e);
		}
		return historicalExchangeRate;
	}
	
	@SuppressWarnings("unchecked")
	public String getCurrency(String acronym) {
		String name = "";
		try {
		JSONObject json = getData("list");
	    JSONObject currenciesList = (JSONObject) json.get("currencies");
	    name = (String) currenciesList.get(acronym);
		}
		catch(Exception e) {
			System.out.println("Errore...");
			System.out.println(e);
		}
		currency = new Currency(name,acronym);
		return name;
	}

	@SuppressWarnings("unchecked")
	public Double getCouple(String acronym) {
		Double value = null;
		String couple = source+acronym;
		try {
			JSONObject json = getData("live");
			JSONObject currenciesQuotes = (JSONObject) json.get("quotes");
			value = (Double) currenciesQuotes.get(couple);
		}
		catch(Exception e) {
			System.out.println("Errore...");
		}
		//TODO compilare e collegare con model
		currencyCouple = new CurrencyCouple();
		return value;
	}
}


