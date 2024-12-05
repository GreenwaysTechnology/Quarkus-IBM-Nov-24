package com.ibm.fault.cb;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Path("cb")
public class CircuteBreakerResource {
    private final AtomicInteger counter = new AtomicInteger(0);

    @GET
    @CircuitBreaker(
            requestVolumeThreshold = 4, // no of requests to monitor
            failureRatio = 0.5,  //Failures to trigger the CB (50%)
            delay = 5000 // Time in ms in the cb remains open
    )
    @Fallback(fallbackMethod = "fallbackResponse")
    public String simulateCb() {
        int attempt = counter.incrementAndGet();
        System.out.println("Attempt : " + attempt + " at " + LocalDateTime.now());
        //simulate failure
        if (attempt % 2 == 0) {
            throw new RuntimeException("Simulated Failure");
        }
        return "Success Full Response on attempt " + attempt;
    }

    public String fallbackResponse() {
        return "Fallback Response:Circuit is Open!";
    }

}
