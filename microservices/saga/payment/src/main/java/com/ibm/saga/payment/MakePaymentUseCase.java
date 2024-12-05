package com.ibm.saga.payment;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class MakePaymentUseCase {

    @Inject
    PaymentService paymentService;
    @Inject
    PaymentProducer paymentProducer;
    @Inject
    SeatEventProducer seatEventProducer;

    public Payment makeAPayment(Seat seat) {
        log.info("Create payment  with seat  {}", seat.getId());
        var payment = createPayment(seat);

        try {
            payment.setStatus("PAID");
            paymentService.savePayment(payment);
        } catch (Exception ex) {
            seatEventProducer.sendSeatEvent(payment.getSeat());
            return payment;
        }

        paymentProducer.sendPaymentEvent(payment);

        return payment;
    }

    private Payment createPayment(Seat seat) {

        Payment payment = new Payment();

        payment.setStatus("PAID");
        payment.setAmount(new BigDecimal(10));
        payment.setSeat(seat);
        payment.setUser(seat.getUser());
        payment.setDate(LocalDate.now());

        return payment;
    }
}
