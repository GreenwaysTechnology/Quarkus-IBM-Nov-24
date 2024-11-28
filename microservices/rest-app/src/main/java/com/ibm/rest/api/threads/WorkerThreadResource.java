package com.ibm.rest.api.threads;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;


@Path("workerThread")
public class WorkerThreadResource {

    @GET
    public String runOnWorker() {
        System.out.println(Thread.currentThread().getName());
        return "Worker Thread";
    }
}
