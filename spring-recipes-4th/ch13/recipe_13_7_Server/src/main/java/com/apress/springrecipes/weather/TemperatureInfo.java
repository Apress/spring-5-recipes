package com.apress.springrecipes.weather;

import java.util.Date;


public class TemperatureInfo {

    private String city;
    private Date date;
    private double min;
    private double max;
    private double average;

    public TemperatureInfo() { } 

    public TemperatureInfo(String city, Date date, double min, double max, double average) { 
	this.city = city; 
	this.date = date; 
	this.min = min;
	this.max = max;
	this.average = average;
    }

    public String getCity() { 
	return city;
    }

    public Date getDate() { 
	return date;
    }

    public double getMin() { 
	return min;
    }

    public double getMax() { 
	return max;
    }

    public double getAverage() { 
	return average;
    }

    public void setCity(String city) { 
	this.city = city;
    }

    public void setDate(Date date) { 
	this.date = date;
    }

    public void setMin(double min) { 
	this.min = min;
    }

    public void setMax(double max) { 
	this.max = max;
    }

    public void setAverage(double average) { 
	this.average = average;
    }
}
