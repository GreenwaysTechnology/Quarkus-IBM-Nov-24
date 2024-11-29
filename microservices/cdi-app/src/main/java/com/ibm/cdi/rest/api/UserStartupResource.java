package com.ibm.cdi.rest.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import com.ibm.cdi.services.lifecycles.UserService;

import java.util.List;

@Path("usersstartup")
public class UserStartupResource {

    @Inject
    UserService userService;

    @GET
    public List<String> findAll() {
        return userService.findAll();
    }

}
