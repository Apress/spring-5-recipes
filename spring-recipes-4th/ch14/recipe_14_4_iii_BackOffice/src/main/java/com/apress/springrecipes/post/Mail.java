package com.apress.springrecipes.post;
public class Mail {

    private String mailId;
    private String country;
    private double weight;
    
    public Mail() { 
    }

    public Mail(String mailId, String country, double weight) { 
	this.mailId = mailId;
	this.country = country; 
	this.weight = weight;
    }

    public String getMailId() { 
	return mailId;
    }

    public String getCountry() { 
	return country;
    }

    public double getWeight() { 
	return weight;
    }

    public void setMailId(String mailId) { 
	this.mailId = mailId;
    }

    public void setCountry(String country) { 
	this.country = country;
    }

    public void setWeight(double weight) { 
	this.weight = weight;
    }

}
