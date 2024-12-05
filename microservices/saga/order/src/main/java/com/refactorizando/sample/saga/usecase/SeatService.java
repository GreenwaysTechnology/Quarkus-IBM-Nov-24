package com.refactorizando.sample.saga.usecase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class SeatService {

    @Inject
    SeatRepository seatRepository;

    @Transactional
    public Seat lockSeat(Long id) {
        log.info("Update seat with id", id);
        seatRepository.update("status = 'LOCKED' where id = ?1", id);
        return seatRepository.findById(id);
    }

    @Transactional
    public Seat unlockSeat(Long id) {
        log.info("Update seat with id", id);
        seatRepository.update("status = 'FREE' where id = ?1", id);
        return seatRepository.findById(id);
    }

    @Transactional
    public Seat busySeat(Long id) {
        log.info("Update seat with id", id);
        seatRepository.update("status = 'BUSY' where id = ?1", id);
        return seatRepository.findById(id);
    }

    public Seat findById(Long id) {
        return seatRepository.findById(id);
    }

    public Seat findFree() {
        return seatRepository.find("status= 'FREE' ").firstResult();
    }

}
