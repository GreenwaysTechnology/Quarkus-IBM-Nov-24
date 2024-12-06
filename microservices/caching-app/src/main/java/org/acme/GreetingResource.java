package org.acme;

import io.quarkus.cache.CacheInvalidate;
import io.quarkus.cache.CacheResult;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @CacheResult(cacheName = "hello")
    public String hello() {
        System.out.println("Hello is executed");
        return "Hello from Quarkus REST";
    }
    @DELETE
    @CacheInvalidate(cacheName ="hello")
    public void invalidCache(){

    }
}
