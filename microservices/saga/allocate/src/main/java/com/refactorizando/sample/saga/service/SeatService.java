package com.refactorizando.sample.saga.service;

import com.refactorizando.sample.saga.model.Seat;
import com.refactorizando.sample.saga.repository.SeatRepository;
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
    public void updateSeat(Seat seat) {

        log.info("Block a  seat {}", seat.toString());

        seatRepository.update("status = 'OCCUPIED' where id = ?1", seat.getId());

    }


    public Seat findById(Long id) {

        return seatRepository.findById(id);
    }

}
