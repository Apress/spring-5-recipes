package com.apress.springrecipes.court;

import org.springframework.web.client.RestTemplate;

import com.apress.springrecipes.court.domain.Members;

/**
 * Created by marten on 16-06-14.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        final String uri = "http://localhost:8080/court/members.xml";
        RestTemplate restTemplate = new RestTemplate();
        Members result = restTemplate.getForObject(uri, Members.class);
        System.out.println(result);
    }
}
