package com.currencylayer.project.statistics;

import java.util.Vector;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.currencylayer.project.exceptions.CurrencyNotFoundException;
import com.currencylayer.project.model.Currency;
import com.currencylayer.project.model.OurDate;
import com.currencylayer.project.model.Source;
import com.currencylayer.project.service.CurrencyLayerServiceImpl;
import com.currencylayer.project.utilis.FileAnalysis;

/** Classe per il calcolo delle statistiche (media e varianza) relative 
 * al tasso di cambio di coppie di variabili che possono essere chieste in
 * ingresso dall'utent (filtro) o stampate tutte insieme.
 * @author Sara Bruschi
 * @author Marco Di Vita
 * */

@Service
public class Statistics implements StatisticsService {

	private FileAnalysis file = new FileAnalysis();
	private Source source = new Source();
	private String src = source.getAcronym();
	private OurDate date = new OurDate();
	private Double average;
	private Double variance;
	private Double sumVariance = new Double(0);
	Vector<Double> rates = new Vector<Double>();
	
	/** Metodo per il calcolo della media del tasso di cambio di una coppia
	 * di valute, di cui una è sempre USD e l'altra è definita in ingresso
	 * dall'utente.
	 * @param String acronym della seconda valuta
	 * @return la media della coppia di valute calcolata nel periodo 
	 * di riferimento.
	 * */
	public Double getAverage(String acronym) {
		JSONObject obj = new JSONObject();
		Double sum = new Double(0);
		Double value = new Double(0);
		String d = "";
		int cont = 0;
		String couple = src+acronym;
		for(int i=1; i<=12;i++) {
			String month = date.getMonth(String.valueOf(i));
			d = "2021-"+month+"-01";
			date = new OurDate(d);
			value = (Double) file.readFile(date.toString(),"quotes").get(couple);
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
	 * @return la varianza della coppia di valute calcolata nel periodo 
	 * di riferimento.
	 * */
	public Double getVariance() {
		for(int j=0;j<rates.size();j++) {
			sumVariance += Math.pow(rates.get(j)-average, 2);
			}
		variance = sumVariance/rates.size();
		return variance;
	}

	/** Metodo per la restituzione del massimo valore del tasso di cambio di 
	 * una coppia di valute, di cui una è sempre USD e l'altra è definita in ingresso
	 * dall'utente nel periodo predefinito.
	 * @return il valore massimo nel periodo da 01-01-2021 a 01-12-2021.
	 * */
	public Double getMax() {
	      Double max = rates.get(0);
		  for(int i = 0; i<rates.size();i++) {
			  if(max<=rates.get(i))
				  max=rates.get(i);
		  }
		  return max;
	}
    
	/** Metodo per la restituzione del minimo valore del tasso di cambio di 
	 * una coppia di valute, di cui una è sempre USD e l'altra è definita in ingresso
	 * dall'utente nel periodo predefinito.
	 * @return il valore minimo nel periodo da 01-01-2021 a 01-12-2021.
	 * */
    public Double getMin() {
    	Double min = rates.get(0);
		  for(int i = 0; i<rates.size();i++) {
			  if(min>=rates.get(i))
				  min=rates.get(i);
		  }
		  return min;
	}
	
    /** Metodo per la stampa delle statistiche, ovvero media e varianza e max e min
	 *  del tasso di cambio di una coppia di valute, di cui una è sempre USD 
	 *  e l'altra è definita in ingresso dall'utente.
	 * @param String acronym della seconda valuta
	 * @return JSONObject contenente tutte le statistiche
     * @throws CurrencyNotFoundException 
	 * */
	@SuppressWarnings("unchecked")
	public JSONObject getStatistics(String acronym) throws CurrencyNotFoundException {
		JSONObject obj = new JSONObject();
		JSONObject objj = new JSONObject();
		JSONObject curr = new JSONObject();
		curr = file.readFile("List.txt","currencies");
		if(curr.get(acronym) == null) {
			throw new CurrencyNotFoundException("This currency: "+acronym+" doesn't exist");
		}
		objj.put("Statistics",obj);
		obj.put("Couple", src+acronym);
		obj.put("Average",getAverage(acronym));
		obj.put("Variance",getVariance());
		obj.put("Max",getMax());
		obj.put("Min",getMin());
		obj.put("Period", "2021");
		return obj;
	}
	
	/** Metodo per definire i mesi dell'anno che rappresentano i file di
	 * riferimento per il calcolo delle statistiche.
	 * */
}

