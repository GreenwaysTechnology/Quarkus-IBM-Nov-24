package com.refactorizando.sample.saga.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class ReservedSeat {

    @Inject
    SeatService seatService;

    @Inject
    SeatEventProducer seatEventProducer;

    @Inject
    UserRepository userRepository;

    public Seat reservedSeat(Seat seat) {
        log.info("Update seat {}", seat.getId());
        var seatToSave = seatService.lockSeat(seat.getId());
        seatEventProducer.sendOrder(seatToSave);
        return seatToSave;
    }

}
