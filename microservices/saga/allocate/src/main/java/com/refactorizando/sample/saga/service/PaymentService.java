package com.refactorizando.sample.saga.service;

import com.refactorizando.sample.saga.events.compensation.PaymentProducerCompensation;
import com.refactorizando.sample.saga.model.Payment;
import com.refactorizando.sample.saga.repository.PaymentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    @Inject
    PaymentRepository paymentRepository;

    @Inject
    PaymentProducerCompensation paymentProducerCompensation;

    @Transactional
    public Payment savePayment(Payment payment) {

        try {

            paymentRepository.persistAndFlush(payment);

        } catch (Exception ex) {

            paymentProducerCompensation.sendPaymentEvent(payment);

        }

        return payment;
    }

    @Transactional
    public void deletePayment(Long paymentId) {

        try {
            paymentRepository.deleteById(paymentId);

        } catch (Exception ex) {

            paymentProducerCompensation.sendPaymentEvent(paymentRepository.findById(paymentId));

        }
        //Refund money to user

    }


}
