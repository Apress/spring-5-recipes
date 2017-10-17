package com.apress.springrecipes.weather;

import java.util.Date;
import java.util.List;

public class GetTemperaturesRequest {

    private String city;
    private List<Date> dates;


    public GetTemperaturesRequest() { } 

    public GetTemperaturesRequest(String city, List<Date> dates) { 
	this.city = city;
	this.dates = dates;
    }

    public void setCity(String city) { 
	this.city = city;
    }
    
    public void setDates(List<Date> dates) { 
	this.dates = dates;
    }

    public String getCity() { 
	return city;
    }

    public List<Date> getDates() { 
	return dates;
    }

}

