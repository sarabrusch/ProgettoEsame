package com.currencylayer.project.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Service;
import org.json.simple.*;

@Service
public class CurrencyLayerServiceImpl implements CurrencyLayerService {

	private String url = "http://api.currencylayer.com/";
	private String key = "74a39b5b1ae2f4bac3f38eaa28bec030";

	@Override
	public JSONObject getLiveOrList(String word) {

		JSONObject liveExchangeRate = null;

		try {
			URLConnection openConnection = new URL(url+word+"?access_key="+key).openConnection();
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
		return liveExchangeRate;
	}

	/*public JSONObject getCurrencyList(String word) throws ParseException {

		JSONObject liveExchangeRate = null;

		try {
			URLConnection openConnection = new URL(url+word+"?access_key="+key).openConnection();
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
		return liveExchangeRate;
	}*/
	
	@Override
	public JSONObject getHistoricalQuotation(String word,String date) {

		JSONObject liveExchangeRate = null;

		try {
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
		return liveExchangeRate;
	}

	@Override
	public JSONObject toJSON(Object object) {
		JSONObject obj = null;
		return obj;
	}

}

