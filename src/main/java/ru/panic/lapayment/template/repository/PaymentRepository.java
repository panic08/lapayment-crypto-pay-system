package ru.panic.lapayment.template.repository;

import org.springframework.stereotype.Repository;
import ru.panic.lapayment.template.entity.Payment;
import ru.panic.lapayment.template.entity.enums.Status;
import ru.panic.lapayment.template.repository.impl.PaymentRepositoryImpl;

import java.util.List;


public interface PaymentRepository {
    void save(Payment payment);
    void delete(Payment payment);
    void updateStatusByPaymentId(Integer paymentId, Status status);
    Payment findPaymentByPaymentId(Integer paymentId);
    List<Payment> findAllByStatus(Status status);
    Long findLastId();
}
