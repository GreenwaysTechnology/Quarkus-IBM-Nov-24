package com.ibm.cdi.rest.api;

import com.ibm.cdi.services.HaiService;
import com.ibm.cdi.services.HelloService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("greet")
public class GreetingResource {

    @Inject
    HelloService helloService;
    @Inject
    HaiService haiService;

    @Path("hello")
    @GET
    public String sayHello() {
        return helloService.sayHello();
    }

    @Path("hai")
    @GET
    public String sayHai() {
        return haiService.sayHai();
    }
}
