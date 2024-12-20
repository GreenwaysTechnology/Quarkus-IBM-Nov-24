package com.refactorizando.sample.saga.usecase;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;


@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class SeatEventConsumer {

    private final SeatService seatService;

    @SneakyThrows
    @Incoming("seats-in")
    public void receive(Record<Long, String> record) {

        log.info("record es: {}", record.key());

        ObjectMapper objectMapper = new ObjectMapper();

        var seat = objectMapper.readValue(record.value(), Seat.class);

        if (null != seat.getType() && seat.getType().equalsIgnoreCase("compensation")) {

            seatService.unlockSeat(seat.getId());

        } else {

            seatService.busySeat(seat.getId());
        }

    }


}
