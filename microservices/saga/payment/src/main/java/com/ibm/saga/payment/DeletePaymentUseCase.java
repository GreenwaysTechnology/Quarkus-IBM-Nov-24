package com.ibm.saga.payment;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@ApplicationScoped
public class DeletePaymentUseCase {

    @Inject
    PaymentService paymentService;

    @Inject
    SeatEventProducer seatEventProducer;

    public void deletePayment(Long paymentId) {

        try {
            paymentService.deletePayment(paymentId);
        } catch (Exception ex) {
            seatEventProducer.sendSeatEvent(paymentService.findById(paymentId).getSeat());
        }
        //Refund money to user

    }
}