package com.ibm.rest.caller;

import io.smallrye.mutiny.Uni;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.client.HttpResponse;
import io.vertx.mutiny.ext.web.client.WebClient;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("webclient")
public class CustomerWebClientResource {
    @Inject
    Vertx vertx;
    WebClient webClient;

    //create webclient object
    @PostConstruct
    public void init() {
        webClient = WebClient.create(vertx, new WebClientOptions().setDefaultHost("localhost").setDefaultPort(8081));
    }


    @GET
    public Uni<String> findAll() {
        return webClient.get("/customers").send().onItem().transform(HttpResponse::bodyAsString);
    }
}
