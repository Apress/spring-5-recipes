package com.apress.springrecipes.court;

import org.springframework.web.client.RestTemplate;

/**
 * Created by marten on 16-06-14.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        final String uri = "http://localhost:8080/court/members.xml";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        System.out.println(result);
    }
}
