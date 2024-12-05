package com.refactorizando.sample.saga;

import com.refactorizando.sample.saga.service.SeatService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Path("/allocates")
@RequiredArgsConstructor
@Slf4j
public class AllocateResource {

    @Inject
    SeatService seatService;

    @GET
    @Path("/{id}")
    public Response allocateSeat(@PathParam("id") Long id) {

        log.info("Seat status by id");

        return Response.status(Response.Status.OK)
                .entity(seatService.findById(id)).build();
    }


}