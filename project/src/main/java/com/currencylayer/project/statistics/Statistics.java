package com.currencylayer.project.statistics;

import java.util.Vector;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.currencylayer.project.model.Currency;
import com.currencylayer.project.service.CurrencyLayerServiceImpl;
import com.currencylayer.project.utilis.FileAnalysis;

/** Classe per il calcolo delle statistiche (media e varianza) relative 
 * al tasso di cambio di coppie di variabili che possono essere chieste in
 * ingresso dall'utent (filtro) o stampate tutte insieme TODO.
 * @author Sara Bruschi
 * @author Marco Di Vita
 * */

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
	
	/** Metodo per il calcolo della media del tasso di cambio di una coppia
	 * di valute, di cui una è sempre USD e l'altra è definita in ingresso
	 * dall'utente.
	 * @param String acronym della seconda valuta
	 * */
	public Double getAverage(String acronym) {
		JSONObject obj = new JSONObject();
		Double sum = new Double(0);
		Double value = new Double(0);
		int cont = 0;
		String couple = source+acronym;
		for(int i=1; i<=12;i++) {
			value = (Double) file.readFile(getMonth(i)).get(couple);
			rates.add(value);
			sum += value;
			cont++;
		}
		average = sum/cont;
		return average;
	}
	
	/** Metodo per il calcolo della varianza del tasso di cambio di una coppia
	 * di valute, di cui una è sempre USD e l'altra è definita in ingresso
	 * dall'utente nel calcolo della media.
	 * */
	public Double getVariance() {
		for(int j=0;j<rates.size();j++) {
			sumVariance += Math.pow(rates.get(j)-average, 2);
			}
		variance = sumVariance/rates.size();
		return variance;
	}

	/** Metodo per la stampa delle statistiche, ovvero media e varianza
	 *  del tasso di cambio di una coppia di valute, di cui una è sempre USD 
	 *  e l'altra è definita in ingresso dall'utente.
	 * @param String acronym della seconda valuta
	 * */
	@SuppressWarnings("unchecked")
	public JSONObject getStatistics(String acronym) {
		JSONObject obj = new JSONObject();
		obj.put("Average",getAverage(acronym));
		obj.put("Variance",getVariance());
		return obj;
	}
	
	/** Metodo per definire i mesi dell'anno che rappresentano i file di
	 * riferimento per il calcolo delle statistiche.
	 *TODO analisi con Calendar*/
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
	
	//TODO MAX E MIN NEL PERIODO CONTRASSEGNATO
}

