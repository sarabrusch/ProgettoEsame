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
	private Currency currencyObject;
	private CurrencyCouple currencyCouple;
	private ArrayList<Currency> currencyList = new ArrayList<Currency>();
	private HashMap<String,String> currencies = new HashMap<String,String>();
	private HashMap<String,Double> rates = new HashMap<String,Double>();

	/** Riempie l'hashmap "rates" con la coppia di acronimi di cui si vuole conoscere
	 * l'exchange rate e relativo exchange rate letto da chiamata all'API.
	 * @param acronym indica l'acronimo della currency della quale si vuole
	 * conoscere l'exchange rate relativo al @value source sempre uguale a "USD".
	 * 
	 * */
	public HashMap<String, Double> createHashMapLive (String acronym) {
		//live
		JSONObject live = getLive();
		JSONObject data;
		data =  (JSONObject) live.get("quotes");
		rates.put(source+acronym, (Double) data.get(source+acronym));
		return rates;
	}
	
	/** Riempie l'hashmap "currencies" con la coppia acronimo e nome letti da chiamata 
	 * all'API.
	 * @param acronym indica l'acronimo della currency della quale si vuole
	 * prendere il nome.
	 * 
	 * */
	public HashMap<String, String> createHashMapList (String acronym) {
		//list
		JSONObject list = getList();
		JSONObject data;
		data =  (JSONObject) list.get("currencies");
		currencies.put(acronym, (String) data.get(acronym));
		return currencies;
	}
	
	/*public String getCurrency() {
		name = (String) getCurrency(getAcronym());
		currency = new Currency(acronym,name);
		return currency.toString();
    } */
	
	/**Metodo per la chiamata all'API che restituisce la lista di exchange rates
	 * relativa a coppie di valute, di cui la source è sempre "USD".  */
	@Override
    public JSONObject getLive() {
		JSONObject liveExchangeRate = new JSONObject();
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
	
	/**Metodo per la chiamata all'API che restituisce la lista di valute
	 * con relativo acronimo e nome.  */
    @Override
	public JSONObject getList() {
    	JSONObject listCurrencies = new JSONObject();
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
			String data = "";
			String inline = "";
			try {
				InputStreamReader inR = new InputStreamReader(input);
				BufferedReader buf = new BufferedReader(inR);
				//Write all the JSON data into a string using a scanner
				while ((inline = buf.readLine()) != null) {
					data += inline;
					//writer.write(data);
				}
			}
			finally {
				//Close the scanner
				input.close();
				//writer.flush();
				//writer.close();
			}
			historicalExchangeRate = (JSONObject) JSONValue.parseWithException(data);
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

	/*@Override
	public JSONObject toJSON(Object object) {
		JSONObject obj = null;
		return obj;
	}*/
	
	@SuppressWarnings("unchecked")
	public JSONObject getCurrency(String acronym) {
		JSONObject obj = new JSONObject();
		JSONObject curr = new JSONObject();
		String name = "";
		try {
		JSONObject json = getList();
	    JSONObject currencies = (JSONObject) json.get("currencies");
	    name = (String) currencies.get(acronym);
		}
		catch(Exception e) {
			System.out.println("Errore...");
			System.out.println(e);
		}
		currencyObject = new Currency(name,acronym);
		curr.put("currency", obj);
		obj.put(acronym, name);
		return curr;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getCouple(String acronym) {
		JSONObject curr = new JSONObject();
		JSONObject obj = new JSONObject();
		Double value = new Double(0);
		String couple = source+acronym;
		try {
			JSONObject json = getLive();
			JSONObject currencies = (JSONObject) json.get("quotes");
			value = (Double) currencies.get(couple);
		}
		catch(Exception e) {
			System.out.println("Errore...");
		}
		//TODO compilare e collegare con model
		currencyCouple = new CurrencyCouple();
	    curr.put("infos", obj);
		JSONObject list = file.readFile("List.txt", "currencies");
		obj.put(source, list.get(source));
		obj.put(acronym,list.get(acronym));
		obj.put("rate", value);
		return curr;
	}
}


