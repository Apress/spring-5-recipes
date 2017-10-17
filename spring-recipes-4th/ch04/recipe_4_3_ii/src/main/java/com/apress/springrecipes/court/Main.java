package com.apress.springrecipes.court;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

/**
 * Created by marten on 16-06-14.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        final String uri = "http://localhost:8080/court/member/{memberId}";
        Map<String, String> params = new HashMap<>();
        params.put("memberId", "1");
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class, params );
        System.out.println(result);
    }
}
