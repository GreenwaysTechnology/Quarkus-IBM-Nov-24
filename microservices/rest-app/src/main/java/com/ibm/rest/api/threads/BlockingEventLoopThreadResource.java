package com.ibm.rest.api.threads;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("blocking")
public class BlockingEventLoopThreadResource {

    @GET
    @Blocking
    public Uni<String> hello() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        //blocking code
//        Thread.sleep(1000);
        Thread.sleep(5000);
        return Uni.createFrom().item("Hello");
    }
}
