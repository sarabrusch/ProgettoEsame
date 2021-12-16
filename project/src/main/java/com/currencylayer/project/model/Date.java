package com.currencylayer.project.model;

public class Date {
	
	private int YYYY;
	private int MM;
	private int DD;
	
	
	public Date() {
	}
	
	public Date(int YYYY, int MM, int DD) {
		this.YYYY = YYYY;
		this.MM = MM;
		this.DD = DD;
	}
	
	public boolean isRight(int YYYY, int MM, int DD) {
		boolean control = true;
		if(MM>12 || MM<=0 || DD>31 || DD<=0) {
			control = false;
		}
		return control;
	}
	
	public int getYYYY() {
		return YYYY;
	}

	public void setYYYY(int YYYY) {
		this.YYYY = YYYY;
	}

	public int getMM() {
		return MM;
	}

	public void setMM(int MM) {
		this.MM = MM;
	}

	public int getDD() {
		return DD;
	}

	public void setDD(int DD) {
		this.DD = DD;
	}
	
	public String getMonth(int MM) {
		String month ="";
		if(MM<10) {
			month = "0"+MM;
		}
		else {
			month = ""+MM;
		}
		return month;
	}
	
	public String getDay(int DD) {
		String day ="";
		if(DD<10) {
			day = "0"+DD;
		}
		else {
			day = ""+DD;
		}
		return day;
	}

	@Override
	public String toString() {
		return YYYY+"-"+getMonth(MM)+"-"+getDay(DD);
	}

}
