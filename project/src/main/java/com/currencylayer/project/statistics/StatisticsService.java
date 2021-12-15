package com.currencylayer.project.statistics;

import org.json.simple.JSONObject;

public interface StatisticsService {
	
	public Double getAverage(String acronym);
	public Double getVariance();
	public Double getMax();
	public Double getMin();
	public JSONObject getStatistics(String acronym);
	
}


