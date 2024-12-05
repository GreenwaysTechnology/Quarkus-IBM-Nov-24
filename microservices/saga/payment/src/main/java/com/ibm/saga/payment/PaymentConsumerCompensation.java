package com.ibm.saga.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;


@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class PaymentConsumerCompensation {

    @Inject
    DeletePaymentUseCase deletePaymentUseCase;

    @Inject
    SeatEventProducer seatEventProducer;

    @SneakyThrows
    @Incoming("allocate-in")
    public void receive(Record<Long, String> record) {

        log.info("Payment compensation with key {}", record.key());

        ObjectMapper objectMapper = new ObjectMapper();

        var payment = objectMapper.readValue(record.value(), Payment.class);

        deletePaymentUseCase.deletePayment(record.key());

        seatEventProducer.sendSeatEvent(payment.getSeat());

    }
}