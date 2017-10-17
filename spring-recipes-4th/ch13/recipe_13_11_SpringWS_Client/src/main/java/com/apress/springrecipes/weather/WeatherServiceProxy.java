package com.apress.springrecipes.weather;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.springframework.ws.client.core.WebServiceTemplate;


public class WeatherServiceProxy implements WeatherService {

    private static final String namespaceUri = "http://springrecipes.apress.com/weather/schemas";
    private final WebServiceTemplate webServiceTemplate;

    public WeatherServiceProxy(WebServiceTemplate webServiceTemplate) throws Exception {
        this.webServiceTemplate = webServiceTemplate;
    }

    public List<TemperatureInfo> getTemperatures(String city, List<Date> dates) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Build the request document from the method arguments
        Document requestDocument = DocumentHelper.createDocument();
        Element requestElement = requestDocument.addElement(
                "GetTemperaturesRequest", namespaceUri);
        requestElement.addElement("city").setText(city);
        for (Date date : dates) {
            requestElement.addElement("date").setText(dateFormat.format(date));
        }

        // Invoke the remote web service
        DocumentSource source = new DocumentSource(requestDocument);
        DocumentResult result = new DocumentResult();
        webServiceTemplate.sendSourceAndReceiveToResult(source, result);

        // Extract the result from the response document
        Document responsetDocument = result.getDocument();
        Element responseElement = responsetDocument.getRootElement();
        List<TemperatureInfo> temperatures = new ArrayList<>();
        for (Object node : responseElement.elements("TemperatureInfo")) {
            Element element = (Element) node;
            try {
                Date date = dateFormat.parse(element.attributeValue("date"));
                double min = Double.parseDouble(element.elementText("min"));
                double max = Double.parseDouble(element.elementText("max"));
                double average = Double.parseDouble(
                        element.elementText("average"));
                temperatures.add(
                        new TemperatureInfo(city, date, min, max, average));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return temperatures;
    }
}