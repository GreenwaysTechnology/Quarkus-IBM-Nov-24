package com.ibm.cdi.rest.api;

import com.ibm.cdi.services.User;
import com.ibm.cdi.services.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("users")
public class UserResource {

//    private UserService userService = new UserService();

    //Field injection
//    @Inject
//    private UserService userService;

    @Inject
    UserService userService;

//    private UserService userService;

    //setter injection
//    @Inject
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }


//    @Inject
//    public UserResource(UserService userService) {
//        this.userService = userService;
//    }

    @GET
    public List<User> findAll() {
        return userService.findAll();
    }
}
