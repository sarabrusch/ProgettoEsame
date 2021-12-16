package com.currencylayer.project.model;

import org.springframework.stereotype.Service;

import com.currencylayer.project.exceptions.InvalidFormatDateException;

/** Classe che modella il tipo di data necessario ai nostri scopi con
 * relativi metodi che ci permettono di verificare che le date siano inserite
 * nel giusto formato richiesto (YYYY-MM-DD).
 * @author Marco Di Vita
 * @author Sara Bruschi
 * */
@Service
public class OurDate {

	private String date;
	private String anno;
	private String mese;
	private String giorno;
	private int j;
	private int i;
	
	/** Costruttore di default
	 * */
	public OurDate() {
	}
	
	/** Costruttore di OurDate 
	 * @param date stringa che viene inserita dall'utente in input 
	 * */
	public OurDate (String date) {
		this.date = date;
		i = 0;
		j = 0;
		anno = getSub();
		mese = getMonth(getSub());
		giorno = getDay(date.substring(j,date.length()));
		this.date = anno+"-"+mese+"-"+giorno; 
	}
	
	//TODO commento
	public String getSub() {
		boolean controllo = true;
		char carattere;
		String subString = "";
		while(j<date.length() && controllo) {
			carattere = date.charAt(j);
		if(carattere == '-') {
			subString = date.substring(i,j);
			controllo = false;
			i = j+1;
		}
		j++;
		}
		return subString;
	}
	
	/** Metodo per controllare che la data inserita sia effettivamente
	 * una data scritta nel modo corretto e che sia esistente
	 * @return control che è true se la data è corretta e false altrimenti
	 * */
	public boolean isRight() {
		boolean control = true;
		int MM = Integer.parseInt(mese);
		int DD = Integer.parseInt(giorno);
		if(MM>12 || MM<=0 || DD>31 || DD<=0) {
			control = false;
		}
		if(anno.contains("-") || mese.contains("-") || giorno.contains("-")) {
			control = false;
			}
		if(anno.length()>4 || mese.length()>3 || giorno.length()>3) {
			control = false;
		}
		return control;
	}
	
	//TODO commentare
	public String getMonth(String month) {
		try {
			int MM = Integer.parseInt(month);
			if(MM<10) {
				month = "0"+MM;
			}
			else {
				month = ""+MM;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return month;
	}
	
	//TODO commentare
	public String getDay(String day) {
		try {
			int DD = Integer.parseInt(day);
			if(DD<10) {
				day = "0"+DD;
			}
			else {
				day = ""+DD;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return day;
	}	
	
	/** Metodo override di toString 
	 * @return date scritta nel giusto formato
	 * */
	@Override
	public String toString() {
		return date;
	}
}
