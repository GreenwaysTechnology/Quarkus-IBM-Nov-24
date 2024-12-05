package com.refactorizando.sample.saga.usecase;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;


@Path("seats")
@Slf4j
public class OrderSeatResource {
    @Inject
    ReservedSeat service;
    @Inject
    UserRepository userRepository;
    @Inject
    SeatRepository seatRepository;
    @Inject
    SeatService seatService;

    @POST
    public Response orderSeat(Seat seat) {
        log.info("New order received ");
        return Response.status(Response.Status.CREATED)
                .entity(service.reservedSeat(seat)).build();
    }

    @GET
    @Path("/{id}")
    public Response orderSeat(@PathParam("id") Long id) {
        log.info("Seat status by id");
        return Response.status(Response.Status.OK)
                .entity(seatService.findById(id)).build();
    }

}