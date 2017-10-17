package com.apress.springrecipes.weather;

import java.util.List;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class TemperatureMarshallingEndpoint {

    private static final String namespaceUri = "http://springrecipes.apress.com/weather/schemas";

    private final WeatherService weatherService;

    public TemperatureMarshallingEndpoint(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PayloadRoot(localPart = "GetTemperaturesRequest", namespace = namespaceUri)
    public @ResponsePayload GetTemperaturesResponse getTemperature(@RequestPayload  GetTemperaturesRequest request) {
        List<TemperatureInfo> temperatures = weatherService.getTemperatures(request.getCity(), request.getDates());
        return new GetTemperaturesResponse(temperatures);
    }
}