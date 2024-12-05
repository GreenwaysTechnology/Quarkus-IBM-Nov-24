package com.ibm.fault.bulkhead;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.Fallback;


@Path("/bulkhead")
public class BulkHeadResource {

    @GET
    @Bulkhead(value = 5, waitingTaskQueue = 3)//concurrent 5 requests +  3 queued
    @Fallback(fallbackMethod = "handleBulkHead")
    public Response dontOverload() {
        System.out.println("Api is called");
        return Response.ok("bulk").build();
    }
    public Response handleBulkHead() {
        System.out.println("fallback response");
        return Response.ok("sorry System busy").build();
    }
}
