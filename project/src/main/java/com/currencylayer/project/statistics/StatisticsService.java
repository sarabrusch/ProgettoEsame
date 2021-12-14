package com.currencylayer.project.statistics;

import org.json.simple.JSONObject;

public interface StatisticsService {
	
	public Double getAverage(String acronym);
	public Double getVariance(String acronym);
	public JSONObject getStatistics(String acronym);
		
	}


