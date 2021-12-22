package com.currencylayer.project.model;

/** Classe che modella il tipo di data necessario ai nostri scopi con
 * relativi metodi che ci permettono di verificare che le date siano inserite
 * nel giusto formato richiesto (YYYY-MM-DD).
 * @author Marco Di Vita
 * @author Sara Bruschi
 * */
public class OurDate {

	private String date;
	private String year;
	private String month;
	private String day;
	private int j;
	private int i;

	/** Costruttore di default
	 * */
	public OurDate() {
	}

	/** Costruttore di OurDate 
	 * @param date stringa che viene inserita dall'utente in input 
	 * */
	public OurDate(String date) {
		this.date = date;
		i = 0;
		j = 0;
		year = getSub();
		month = getMonth(getSub());
		day = getDay(date.substring(j,date.length()));
		this.date = year+"-"+month+"-"+day; 
	}

	/** Metodo che permette di suddividere la data in anno e mese sotto forma
	 * di subString utilizzando degli indici che successivamente serviranno
	 * per ottenere la subString day
	 * @return subString contenente mese o anno
	 * */
	public String getSub() {
		boolean control = true;
		char character;
		String subString = "";
		while(j<date.length() && control) {
			character = date.charAt(j);
			if(character == '-') {
				subString = date.substring(i,j);
				control = false;
				i = j+1;
			}
			j++;
		}
		return subString;
	}

	/** Metodo per controllare che la data inserita sia effettivamente
	 * una data scritta nel modo corretto e che sia esistente
	 * @return control che e' true se la data e' corretta e false altrimenti
	 * */
	public boolean isRight() {
		boolean control = true;
		int MM = Integer.parseInt(month);
		int DD = Integer.parseInt(day);
		if(MM>12 || MM<=0 || DD>31 || DD<=0) {
			control = false;
		}
		if(year.contains("-") || month.contains("-") || day.contains("-")) {
			control = false;
		}
		if(year.length()>4 || month.length()>3 || day.length()>3) {
			control = false;
		}
		return control;
	}

	/** Metodo che restituisce il mese nel formato di scrittura corretto
	 * @param month da controllare
	 * @return month nel formato corretto
	 * */
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

	/** Metodo che restituisce il giorno nel formato di scrittura corretto
	 * @param day da controllare
	 * @return day nel formato corretto
	 * */
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
