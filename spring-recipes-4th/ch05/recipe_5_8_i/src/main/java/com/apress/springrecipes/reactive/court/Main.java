package com.apress.springrecipes.reactive.court;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

/**
 * Created by marten on 02-07-17.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        final String url = "http://localhost:8080/court-rx";

        WebClient.create(url)
                    .get()
                    .uri("/reservations")
                    .accept(MediaType.APPLICATION_STREAM_JSON)
                    .exchange()
                    .flatMapMany(cr -> cr.bodyToFlux(String.class)).subscribe(System.out::println);

        System.in.read();
    }
}
