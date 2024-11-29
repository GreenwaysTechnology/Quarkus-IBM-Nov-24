package com.ibm.config.core;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("hello")
public class HelloResource {

    @ConfigProperty(name = "greeting.message", defaultValue = "Welcome")
    String message;

//    @ConfigProperty(name="app.user.name",defaultValue = "Ram")
//    String userName;

//    @GET
//    public  String sayHello(){
//        return  message + userName;
//    }

    @GET
    public String sayHello() {
        return message + ConfigProvider.
                getConfig()
                .getOptionalValue("app.user.name", String.class)
                .orElse("Sweety");
    }

}
