package ru.panic.lapayment.template.repository;

import org.springframework.stereotype.Repository;
import ru.panic.lapayment.template.entity.Payment;
import ru.panic.lapayment.template.entity.enums.Status;

@Repository
public interface PaymentRepository {
    void save(Payment payment);
    void updateStatusByPaymentId(Integer paymentId, Status status);
    Payment findPaymentByPaymentId(Integer paymentId);
    Long findLastId();
}
