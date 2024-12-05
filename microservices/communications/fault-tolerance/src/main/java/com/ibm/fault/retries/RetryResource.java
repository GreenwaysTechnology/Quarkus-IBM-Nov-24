package com.ibm.fault.retries;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Path("retry")
public class RetryResource {

    private AtomicLong counter = new AtomicLong();
    private float failureRatio = 0.5f;
    AtomicInteger noOfAttempt = new AtomicInteger();

    @GET
    @Retry(maxRetries = 3, retryOn = RuntimeException.class)
    @Fallback(fallbackMethod = "fallbackResponse")
    public List<String> getProducts() {
        int attempt = noOfAttempt.incrementAndGet();
        final Long invocationNumber = counter.getAndIncrement();
        System.out.println("Attempt : " + attempt + " at" + LocalDateTime.now());
        maybeFail(String.format("Retry Resource#getProducts invocation %d failed", invocationNumber));
        return List.of("Product1", "Product2");
    }

    private void maybeFail(String failureMessage) {
        if (new Random().nextFloat() < failureRatio) {
            throw new RuntimeException("Resource failed");
        }
    }

    public List<String> fallbackResponse() {
        return List.of("FallbackResponse:retries exhausted");
    }
}

