package com.ibm.saga.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;


@ApplicationScoped
@Slf4j
@RequiredArgsConstructor
public class PaymentProducer {

    @Inject
    @Channel("payments-out")
    Emitter<Record<Long, String>> emitter;
    @Inject
    SeatEventProducer seatEventProducer;

    @Inject
    DeletePaymentUseCase deletePaymentUseCase;


    @SneakyThrows
    public void sendPaymentEvent(Payment payment) {
        log.info("Event sent {}", payment.getId());

        ObjectMapper objectMapper = new ObjectMapper();

        var paymentJson = objectMapper.writeValueAsString(payment);

        emitter.send(Record.of(payment.getId(), paymentJson))
                .whenComplete((success, failure) -> {
                    if (failure != null) {
                        log.error("D'oh! " + failure.getMessage());

                        seatEventProducer.sendSeatEvent(payment.getSeat());

                        deletePaymentUseCase.deletePayment(payment.getId());

                    } else {

                        log.info("Message processed successfully");

                    }
                });
    }

}