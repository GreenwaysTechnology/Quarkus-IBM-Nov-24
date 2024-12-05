package com.ibm.saga.payment;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Path("/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentResource {

    @Inject
    PaymentService paymentService;
    @GET
    public Response getPayments() {
        log.info("Find All payments");
        return Response.status(Response.Status.OK)
                .entity(paymentService.findAll()).build();
    }

}