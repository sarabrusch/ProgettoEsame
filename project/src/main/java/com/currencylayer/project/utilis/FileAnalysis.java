package com.currencylayer.project.utilis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

/** Classe che contiene metodi utili allo sviluppo del programma, tra cui l'analisi
 * di file necessaria all'implementazione delle statistiche e dei filtri.*/
public class FileAnalysis {
	
	public JSONObject readFile(String date) {
		JSONObject fileRead = new JSONObject();
		String data = "";
		String inline = "";
		try {
			BufferedReader read = new BufferedReader(new FileReader(date));
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

}
