package com.ibm.saga.payment;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    @Inject
    PaymentRepository paymentRepository;

    @Inject
    SeatEventProducer seatEventProducer;

    @Inject
    SeatRepository seatRepository;

    @Transactional
    public Payment savePayment(Payment payment) {
        paymentRepository.persist(payment);
        return payment;
    }

    public void deletePayment(Long paymentId) {
        paymentRepository.deleteById(paymentId);
        //Refund money to user
    }

    public Payment findById(Long id) {
        return paymentRepository.findById(id);
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll().stream().collect(Collectors.toList());
    }

}
