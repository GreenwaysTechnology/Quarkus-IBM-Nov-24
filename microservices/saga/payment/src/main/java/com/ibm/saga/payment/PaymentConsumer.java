package com.ibm.saga.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;



/**
 * This class receive a message with the seat that has been selected.
 * After recive the event this class is going to save and send a message to update the status of the seat.
 */
@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class PaymentConsumer {

    @Inject
    MakePaymentUseCase makePaymentUseCase;

    @SneakyThrows
    @Incoming("payments-in")
    public void receive(Record<Long, String> record) {

        log.info("record es: {}", record.key());

        ObjectMapper objectMapper = new ObjectMapper();

        var seat = objectMapper.readValue(record.value(), Seat.class);

        makePaymentUseCase.makeAPayment(seat);

    }

}
