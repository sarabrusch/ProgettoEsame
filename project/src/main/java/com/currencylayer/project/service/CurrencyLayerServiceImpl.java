package com.currencylayer.project.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Service;

import com.currencylayer.project.exceptions.CurrencyNotFoundException;
import com.currencylayer.project.exceptions.InvalidFormatDateException;
import com.currencylayer.project.model.Currency;
import com.currencylayer.project.model.CurrencyCouple;
import com.currencylayer.project.model.CurrencyCoupleExchange;
import com.currencylayer.project.model.OurDate;
import com.currencylayer.project.model.Source;

import org.json.simple.*;

/** Classe implementazione di CurrencyLayerService che va ad implementare i metodi
 * responsabili delle chiamate all'API e della lettura dei JSONObject restituiti
 * da quest'ultima per la rielaborazione dei dati.
 * @author Marco Di Vita
 * @author Sara Bruschi 
 * */

@Service
public class CurrencyLayerServiceImpl implements CurrencyLayerService {

	private String url = "http://api.currencylayer.com/";
	private String key = "65af5bc6c7290d6e3cca43151a5d5a50";
	private Source source = new Source();
	private String src = source.getAcronym();
	private OurDate date;
	private Currency currency;
	private CurrencyCouple currencyCouple;
	private CurrencyCoupleExchange currencyCoupleExchange;

	/** Metodo per la chiamata all'API, a seconda della word inserita e' possibile
	 * ottenere la lista di valute presenti nel programma o il live di tutti
	 * gli exchange rate.
	 * @param word che deve essere "live" o "list" a seconda delle necessita'
	 * @return data JSONObject che rappresenta il live o la list.
	 * */
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

	/** Metodo per la chiamata all'API che restituisce la lista di exchange rates
	 * relativa a coppie di valute, di cui la source e' sempre "USD", di una
	 * precisa data che deve essere specificata.
	 * @param date di cui si vuole conoscere l'exchange rate in formato YYYY-MM-DD
	 * @return historicalExchangeRate JSONObject  
	 * @throws InvalidFormatDateException 
	 * */
	@Override
	public JSONObject getHistoricalQuotation(String date) throws InvalidFormatDateException {
		JSONObject historicalExchangeRate = new JSONObject();
		try {
			//FileWriter file ;
			//file = new FileWriter("2021-12-01");
			//BufferedWriter writer = new BufferedWriter(file);
			this.date = new OurDate(date);
			date = this.date.toString();
			if(!this.date.isRight()) {
				throw new InvalidFormatDateException ("Invalid format date");
			}
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

	/** Metodo che permette di ottenere il nome completo di una valuta inserendone
	 * il suo acronimo in ingresso.
	 * @param acronym acronimo della valuta della quale si vuole ottenere il
	 * nome completo
	 * @return String name, nome completo della valuta
	 * @throws CurrencyNotFoundException
	 * */
	public String getCurrency(String acronym) throws CurrencyNotFoundException {
		String name = "";
		try {
			JSONObject json = getData("list");
			JSONObject currenciesList = (JSONObject) json.get("currencies");
			name = (String) currenciesList.get(acronym);
			if(name == null) {
				throw new CurrencyNotFoundException("This currency: "+acronym+" doesn't exist");
			}
		}
		catch(Exception e) {
			System.out.println("Errore...");
		}
		currency = new Currency(name,acronym);
		return name;
	}

	/** Metodo che permette di ottenere il tasso di cambio di una valuta relativo a USD
	 * inserendone l'acronimo in ingresso.
	 * @param acronym acronimo della valuta della quale si vuole ottenere il
	 * tasso di cambio rispetto alla source.
	 * @return Double value, tasso di cambio della coppia
	 * @throws CurrencyNotFoundException
	 * */
	public Double getCouple(String acronym) throws CurrencyNotFoundException {
		Double value = new Double(0);
		currencyCouple = new CurrencyCouple(src,acronym);
		try {
			JSONObject json = (JSONObject) getData("live").get("quotes");
			value = (Double) json.get(currencyCouple.getCouple());
			if(value == null) {
				throw new CurrencyNotFoundException("This currency: "+acronym+" doesn't exist");
			}
		}
		catch(Exception e) {
			System.out.println("Errore...");
			System.out.println(e);
		}
		currencyCoupleExchange = new CurrencyCoupleExchange(src,acronym,value);
		return currencyCoupleExchange.getExchangeRate();
	}
}


