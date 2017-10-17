package com.apress.springrecipes.weather;

import java.util.List;

public class GetTemperaturesResponse {

    private List<TemperatureInfo> temperatures;

    public GetTemperaturesResponse() { }
    
    public GetTemperaturesResponse(List<TemperatureInfo> temperatures) { 
	this.temperatures = temperatures;
    }

    public void setTemperatures(List<TemperatureInfo> temperatures) { 
	this.temperatures = temperatures;
    }

    public List<TemperatureInfo> getTemperatures() { 
	return temperatures;
    }
}